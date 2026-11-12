package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Address7;
import model.Customer7;
import model.Order7;
import model.OrderDetail7;
import model.Product7;

public class OrderDAO7 extends DAO7 {

    private static final String SELECT_PENDING_ORDERS_SQL =
        "SELECT o.id, o.orderCode, o.orderDate, o.status, o.note, " +
        "m.id as cid, m.name as customerName, m.phone, " +
        "a.id as aid, a.houseName, a.building, a.street, a.ward, a.district, a.city, " +
        "(SELECT COALESCE(SUM(od.quantity * od.unitPrice), 0) " +
        " FROM tblOrderDetail7 od WHERE od.tblOrder7id = o.id) as totalAmount " +
        "FROM tblOrder7 o " +
        "INNER JOIN tblCustomer7 c ON o.tblCustomer7tblMember7id = c.tblMember7id " +
        "INNER JOIN tblMember7 m ON c.tblMember7id = m.id " +
        "LEFT JOIN tblAddress7 a ON m.tblAddress7id = a.id " +
        "WHERE o.status = 'Chưa xuất' ORDER BY o.orderDate ASC";

    private static final String SELECT_ORDER_BY_ID_SQL =
        "SELECT o.*, c.customerCode, m.name as customerName, m.phone as customerPhone, " +
        "a.id as aid, a.houseName, a.building, a.street, a.ward, a.district, a.city " +
        "FROM tblOrder7 o " +
        "INNER JOIN tblCustomer7 c ON o.tblCustomer7tblMember7id = c.tblMember7id " +
        "INNER JOIN tblMember7 m ON c.tblMember7id = m.id " +
        "LEFT JOIN tblAddress7 a ON m.tblAddress7id = a.id " +
        "WHERE o.id = ?";

    private static final String SELECT_ORDER_DETAILS_SQL =
        "SELECT od.id as odid, od.quantity, od.unitPrice, " +
        "p.id as pid, p.code, p.name, p.unit, p.price, p.stockQuantity " +
        "FROM tblOrderDetail7 od " +
        "INNER JOIN tblProduct7 p ON od.tblProduct7id = p.id " +
        "WHERE od.tblOrder7id = ?";

    private static final String UPDATE_EXPORT_ORDER_SQL =
        "UPDATE tblOrder7 SET exportDate = ?, status = ?, " +
        "tblSalesStaff7tblStaff7tblMember7id = ?, tblShipper7tblStaff7tblMember7id = ? " +
        "WHERE id = ?";

    private static final String DEDUCT_PRODUCT_STOCK_SQL =
        "UPDATE tblProduct7 SET stockQuantity = stockQuantity - ? " +
        "WHERE id = ? AND stockQuantity >= ?";

    public OrderDAO7() {
        super();
    }

    /**
     * Lấy danh sách các đơn hàng đang ở trạng thái "Chưa xuất" (Hình 17, 21)
     */
    public List<Order7> getPendingOrders() {
        List<Order7> orders = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PENDING_ORDERS_SQL)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Order7 o = new Order7();
                o.setId(rs.getInt("id"));
                o.setOrderCode(rs.getString("orderCode"));
                o.setOrderDate(rs.getDate("orderDate"));
                o.setStatus(rs.getString("status"));
                o.setNote(rs.getString("note"));
                o.setTotalAmount(rs.getFloat("totalAmount"));

                Address7 addr = new Address7(
                    rs.getInt("aid"),
                    rs.getString("houseName"),
                    rs.getString("building"),
                    rs.getString("street"),
                    rs.getString("ward"),
                    rs.getString("district"),
                    rs.getString("city")
                );
                Customer7 cust = new Customer7();
                cust.setId(rs.getInt("cid"));
                cust.setName(rs.getString("customerName"));
                cust.setPhone(rs.getString("phone"));
                cust.setAddress(addr);
                o.setCustomer(cust);

                orders.add(o);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return orders;
    }

    /**
     * Lấy thông tin chi tiết của đơn hàng theo idOrder (Hình 17, 21)
     */
    public Order7 getOrderDetail(int idOrder) {
        Order7 o = null;
        try (Connection connection = getConnection();
             PreparedStatement psOrder = connection.prepareStatement(SELECT_ORDER_BY_ID_SQL)) {
            psOrder.setInt(1, idOrder);
            System.out.println(psOrder);
            ResultSet rs = psOrder.executeQuery();

            if (rs.next()) {
                o = new Order7();
                o.setId(rs.getInt("id"));
                o.setOrderCode(rs.getString("orderCode"));
                o.setOrderDate(rs.getDate("orderDate"));
                o.setExportDate(rs.getDate("exportDate"));
                o.setStatus(rs.getString("status"));
                o.setNote(rs.getString("note"));

                Address7 addr = new Address7(
                    rs.getInt("aid"),
                    rs.getString("houseName"),
                    rs.getString("building"),
                    rs.getString("street"),
                    rs.getString("ward"),
                    rs.getString("district"),
                    rs.getString("city")
                );
                Customer7 cust = new Customer7();
                cust.setId(rs.getInt("tblCustomer7tblMember7id"));
                cust.setCustomerCode(rs.getString("customerCode"));
                cust.setName(rs.getString("customerName"));
                cust.setPhone(rs.getString("customerPhone"));
                cust.setAddress(addr);
                o.setCustomer(cust);

                try (PreparedStatement psDet = connection.prepareStatement(SELECT_ORDER_DETAILS_SQL)) {
                    psDet.setInt(1, idOrder);
                    ResultSet rsDet = psDet.executeQuery();
                    List<OrderDetail7> details = new ArrayList<>();
                    float sum = 0;
                    while (rsDet.next()) {
                        Product7 p = new Product7();
                        p.setId(rsDet.getInt("pid"));
                        p.setProductCode(rsDet.getString("code"));
                        p.setProductName(rsDet.getString("name"));
                        p.setUnit(rsDet.getString("unit"));
                        p.setPrice(rsDet.getFloat("price"));
                        p.setStockQuantity(rsDet.getInt("stockQuantity"));

                        OrderDetail7 od = new OrderDetail7(
                            rsDet.getInt("odid"),
                            rsDet.getInt("quantity"),
                            rsDet.getFloat("unitPrice"),
                            p
                        );
                        sum += od.getSubTotal();
                        details.add(od);
                    }
                    o.setOrderDetails(details);
                    o.setTotalAmount(sum);
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return o;
    }

    /**
     * Cập nhật duyệt đơn xuất kho & trừ số lượng tồn kho mặt hàng (Hình 17, 21)
     */
    public boolean updateExportOrder(Order7 order) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            Date today = new Date(System.currentTimeMillis());
            order.setExportDate(today);

            try (PreparedStatement psOrder = connection.prepareStatement(UPDATE_EXPORT_ORDER_SQL)) {
                psOrder.setDate(1, today);
                psOrder.setString(2, order.getStatus());
                psOrder.setInt(3, order.getSalesStaff().getId());
                psOrder.setInt(4, order.getShipper().getId());
                psOrder.setInt(5, order.getId());
                psOrder.executeUpdate();
            }

            for (OrderDetail7 od : order.getOrderDetails()) {
                try (PreparedStatement psStock = connection.prepareStatement(DEDUCT_PRODUCT_STOCK_SQL)) {
                    psStock.setInt(1, od.getQuantity());
                    psStock.setInt(2, od.getProduct().getId());
                    psStock.setInt(3, od.getQuantity());
                    int rowUpdated = psStock.executeUpdate();
                    if (rowUpdated == 0) {
                        connection.rollback();
                        return false;
                    }
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { printSQLException(ex); }
            printSQLException(e);
            return false;
        }
    }
}