package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product7;

public class ProductDAO7 extends DAO7 {

    private static final String SEARCH_PRODUCTS_BY_KEY_SQL =
        "SELECT * FROM tblProduct7 WHERE name LIKE ?";
    private static final String SELECT_PRODUCT_BY_ID_SQL =
        "SELECT * FROM tblProduct7 WHERE id = ?";
    private static final String INSERT_PRODUCT_SQL =
        "INSERT INTO tblProduct7 (code, name, category, unit, price, stockQuantity, description, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_SQL =
        "UPDATE tblProduct7 SET code = ?, name = ?, category = ?, unit = ?, price = ?, stockQuantity = ?, description = ?, image = ? WHERE id = ?";
    private static final String DELETE_PRODUCT_SQL =
        "DELETE FROM tblProduct7 WHERE id = ?";

    public ProductDAO7() {
        super();
    }

    /**
     * Tìm kiếm các mặt hàng có tên chứa từ khóa key (Hình 15, 19)
     */
    public List<Product7> searchProduct(String key) {
        List<Product7> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCTS_BY_KEY_SQL)) {
            preparedStatement.setString(1, "%" + (key == null ? "" : key.trim()) + "%");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                products.add(new Product7(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getString("unit"),
                    rs.getFloat("price"),
                    rs.getInt("stockQuantity"),
                    rs.getString("description"),
                    rs.getString("image")
                ));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    /**
     * Lấy thông tin chi tiết của một mặt hàng theo idProduct (Hình 15, 19)
     */
    public Product7 getProductDetail(int idProduct) {
        Product7 product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL)) {
            preparedStatement.setInt(1, idProduct);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                product = new Product7(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getString("unit"),
                    rs.getFloat("price"),
                    rs.getInt("stockQuantity"),
                    rs.getString("description"),
                    rs.getString("image")
                );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public void insertProduct(Product7 p) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getUnit());
            ps.setFloat(5, p.getPrice());
            ps.setInt(6, p.getStockQuantity());
            ps.setString(7, p.getDescription());
            ps.setString(8, p.getImage());
            ps.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean updateProduct(Product7 p) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getCategory());
            ps.setString(4, p.getUnit());
            ps.setFloat(5, p.getPrice());
            ps.setInt(6, p.getStockQuantity());
            ps.setString(7, p.getDescription());
            ps.setString(8, p.getImage());
            ps.setInt(9, p.getId());
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}