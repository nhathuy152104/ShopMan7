package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Shipper7;

public class ShipperDAO7 extends DAO7 {

    private static final String SELECT_ALL_SHIPPERS_SQL =
        "SELECT m.id, m.name, m.phone, m.email, st.staffCode, st.position, sh.deliveryArea " +
        "FROM tblShipper7 sh " +
        "INNER JOIN tblStaff7 st ON sh.tblStaff7tblMember7id = st.tblMember7id " +
        "INNER JOIN tblMember7 m ON st.tblMember7id = m.id";

    public ShipperDAO7() {
        super();
    }

    /**
     * Lấy danh sách nhân viên giao hàng khả dụng của cửa hàng (Hình 17, 21)
     */
    public List<Shipper7> getShipperList() {
        List<Shipper7> shippers = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SHIPPERS_SQL)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Shipper7 sh = new Shipper7();
                sh.setId(rs.getInt("id"));
                sh.setStaffCode(rs.getString("staffCode"));
                sh.setName(rs.getString("name"));
                sh.setPhone(rs.getString("phone"));
                sh.setEmail(rs.getString("email"));
                sh.setPosition(rs.getString("position"));
                sh.setDeliveryArea(rs.getString("deliveryArea"));
                shippers.add(sh);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return shippers;
    }
}