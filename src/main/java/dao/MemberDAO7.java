package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Address7;
import model.Customer7;
import model.Member7;
import model.SalesStaff7;

public class MemberDAO7 extends DAO7 {

    private static final String CHECK_LOGIN_SQL =
        "SELECT * FROM tblMember7 WHERE username = ? AND password = ?";
    private static final String SELECT_SALES_STAFF_SQL =
        "SELECT m.*, s.staffCode, s.position FROM tblMember7 m " +
        "INNER JOIN tblStaff7 s ON m.id = s.tblMember7id " +
        "INNER JOIN tblSalesStaff7 ss ON s.tblMember7id = ss.tblStaff7tblMember7id " +
        "WHERE m.id = ?";
    private static final String INSERT_ADDRESS_SQL =
        "INSERT INTO tblAddress7 (houseName, building, street, ward, district, city) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_MEMBER_SQL =
        "INSERT INTO tblMember7 (username, password, name, dob, email, phone, role, tblAddress7id) VALUES (?, ?, ?, ?, ?, ?, 'CUSTOMER', ?)";
    private static final String INSERT_CUSTOMER_SQL =
        "INSERT INTO tblCustomer7 (tblMember7id, customerCode, registerDate) VALUES (?, ?, CURRENT_DATE())";

    public MemberDAO7() {
        super();
    }

    public Member7 checkLogin(String username, String password) {
        Member7 member = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                member = new Member7();
                member.setId(rs.getInt("id"));
                member.setUsername(rs.getString("username"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return member;
    }

    public SalesStaff7 getSalesStaffById(int idMember) {
        SalesStaff7 ss = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SALES_STAFF_SQL)) {
            preparedStatement.setInt(1, idMember);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ss = new SalesStaff7();
                ss.setId(rs.getInt("id"));
                ss.setUsername(rs.getString("username"));
                ss.setName(rs.getString("name"));
                ss.setEmail(rs.getString("email"));
                ss.setPhone(rs.getString("phone"));
                ss.setRole(rs.getString("role"));
                ss.setStaffCode(rs.getString("staffCode"));
                ss.setPosition(rs.getString("position"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ss;
    }

    public boolean registerCustomer(Customer7 customer) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            Address7 addr = customer.getAddress();

            PreparedStatement psAddr = connection.prepareStatement(INSERT_ADDRESS_SQL, Statement.RETURN_GENERATED_KEYS);
            psAddr.setString(1, addr.getHouseName());
            psAddr.setString(2, addr.getBuilding());
            psAddr.setString(3, addr.getStreet());
            psAddr.setString(4, addr.getWard());
            psAddr.setString(5, addr.getDistrict());
            psAddr.setString(6, addr.getCity());
            psAddr.executeUpdate();
            ResultSet rsAddr = psAddr.getGeneratedKeys();
            int addrId = rsAddr.next() ? rsAddr.getInt(1) : 1;

            PreparedStatement psMem = connection.prepareStatement(INSERT_MEMBER_SQL, Statement.RETURN_GENERATED_KEYS);
            psMem.setString(1, customer.getUsername());
            psMem.setString(2, customer.getPassword());
            psMem.setString(3, customer.getName());
            psMem.setDate(4, customer.getDob());
            psMem.setString(5, customer.getEmail());
            psMem.setString(6, customer.getPhone());
            psMem.setInt(7, addrId);
            psMem.executeUpdate();
            ResultSet rsMem = psMem.getGeneratedKeys();
            int memId = rsMem.next() ? rsMem.getInt(1) : 0;

            PreparedStatement psCust = connection.prepareStatement(INSERT_CUSTOMER_SQL);
            psCust.setInt(1, memId);
            psCust.setString(2, "KH0" + memId);
            psCust.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { printSQLException(ex); }
            printSQLException(e);
            return false;
        }
    }
}