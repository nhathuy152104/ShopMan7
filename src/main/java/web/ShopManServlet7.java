package web;

import dao.MemberDAO7;
import dao.OrderDAO7;
import dao.ProductDAO7;
import dao.ShipperDAO7;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Address7;
import model.Customer7;
import model.Member7;
import model.Order7;
import model.Product7;
import model.SalesStaff7;
import model.Shipper7;

/**
 * ShopManServlet7.java
 * Controller Servlet xử lý toàn bộ request của hệ thống ShopMan7 theo chuẩn LAB 1.
 */
@WebServlet(urlPatterns = {
    "/login", "/logout", "/register",
    "/customerMain", "/searchProduct", "/productDetail",
    "/salesStaffMain", "/pendingOrders", "/orderDetail", "/approveOrder"
})
public class ShopManServlet7 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberDAO7 memberDAO;
    private ProductDAO7 productDAO;
    private OrderDAO7 orderDAO;
    private ShipperDAO7 shipperDAO;

    public void init() {
        memberDAO = new MemberDAO7();
        productDAO = new ProductDAO7();
        orderDAO = new OrderDAO7();
        shipperDAO = new ShipperDAO7();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/login":
                    handleLogin(request, response);
                    break;
                case "/logout":
                    handleLogout(request, response);
                    break;
                case "/register":
                    handleRegister(request, response);
                    break;
                case "/customerMain":
                    showCustomerMain(request, response);
                    break;
                case "/searchProduct":
                    searchProduct(request, response);
                    break;
                case "/productDetail":
                    showProductDetail(request, response);
                    break;
                case "/salesStaffMain":
                    showSalesStaffMain(request, response);
                    break;
                case "/pendingOrders":
                    listPendingOrders(request, response);
                    break;
                case "/orderDetail":
                    showOrderDetail(request, response);
                    break;
                case "/approveOrder":
                    approveExportOrder(request, response);
                    break;
                default:
                    showCustomerMain(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null) {
            request.getRequestDispatcher("gdLogin7.jsp").forward(request, response);
            return;
        }
        Member7 member = memberDAO.checkLogin(username, password);
        if (member != null) {
            HttpSession session = request.getSession();
            if ("SALESSTAFF".equalsIgnoreCase(member.getRole())) {
                SalesStaff7 salesStaff = memberDAO.getSalesStaffById(member.getId());
                session.setAttribute("salesStaff", salesStaff);
                response.sendRedirect("gdSalesStaffMain7.jsp");
            } else {
                session.setAttribute("customer", member);
                response.sendRedirect("gdCustomerMain7.jsp");
            }
        } else {
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            request.getRequestDispatcher("gdLogin7.jsp").forward(request, response);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("gdLogin7.jsp");
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            request.getRequestDispatcher("gdRegister7.jsp").forward(request, response);
            return;
        }
        Address7 addr = new Address7(0,
            request.getParameter("houseName"),
            request.getParameter("building"),
            request.getParameter("street"),
            request.getParameter("ward"),
            request.getParameter("district"),
            request.getParameter("city")
        );
        Customer7 cust = new Customer7();
        cust.setUsername(request.getParameter("username"));
        cust.setPassword(request.getParameter("password"));
        cust.setName(request.getParameter("name"));
        cust.setDob(Date.valueOf(request.getParameter("dob")));
        cust.setEmail(request.getParameter("email"));
        cust.setPhone(request.getParameter("phone"));
        cust.setAddress(addr);

        if (memberDAO.registerCustomer(cust)) {
            response.sendRedirect("gdLogin7.jsp?msg=registered");
        } else {
            request.setAttribute("error", "Đăng ký thất bại! Tên đăng nhập đã tồn tại.");
            request.getRequestDispatcher("gdRegister7.jsp").forward(request, response);
        }
    }

    private void showCustomerMain(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("gdCustomerMain7.jsp");
        dispatcher.forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String key = request.getParameter("inKeyWord");
        if (key != null && !key.trim().isEmpty()) {
            List<Product7> listProduct = productDAO.searchProduct(key);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("inKeyWord", key);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("gdSearchProduct7.jsp");
        dispatcher.forward(request, response);
    }

    private void showProductDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        Product7 product = productDAO.getProductDetail(idProduct);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("gdProductDetail7.jsp");
        dispatcher.forward(request, response);
    }

    private void showSalesStaffMain(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("gdSalesStaffMain7.jsp");
        dispatcher.forward(request, response);
    }

    private void listPendingOrders(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Order7> listPendingOrder = orderDAO.getPendingOrders();
        request.setAttribute("listPendingOrder", listPendingOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("gdApproveOrder7.jsp");
        dispatcher.forward(request, response);
    }

    private void showOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int idOrder = Integer.parseInt(request.getParameter("idOrder"));
        Order7 order = orderDAO.getOrderDetail(idOrder);
        List<Shipper7> listShipper = shipperDAO.getShipperList();
        request.getSession().setAttribute("order", order);
        request.getSession().setAttribute("listShipper", listShipper);
        request.setAttribute("order", order);
        request.setAttribute("listShipper", listShipper);
        RequestDispatcher dispatcher = request.getRequestDispatcher("gdOrderDetail7.jsp");
        dispatcher.forward(request, response);
    }

    private void approveExportOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("doApproveOrder7.jsp");
        dispatcher.forward(request, response);
    }
}