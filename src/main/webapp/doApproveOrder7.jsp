<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,dao.*,model.*"%>
<%
    request.setCharacterEncoding("UTF-8");
    SalesStaff7 salesStaff = (SalesStaff7) session.getAttribute("salesStaff");
    Order7 order = (Order7) session.getAttribute("order");
    List<Shipper7> listShipper = (List<Shipper7>) session.getAttribute("listShipper");

    int idShipper = Integer.parseInt(request.getParameter("slShipper"));
    String status = request.getParameter("slStatus");

    Shipper7 selectedShipper = null;
    if (listShipper != null) {
        for (Shipper7 sh : listShipper) {
            if (sh.getId() == idShipper) {
                selectedShipper = sh;
                break;
            }
        }
    }

    // Gọi setter() trên đối tượng Order7 (Bước 27-29 Biểu đồ tuần tự Hình 21)
    order.setSalesStaff(salesStaff);
    order.setShipper(selectedShipper);
    order.setStatus(status);

    OrderDAO7 orderDAO = new OrderDAO7();
    boolean ok = orderDAO.updateExportOrder(order);
    if (ok) {
        session.setAttribute("order", order);
        response.sendRedirect("gdPrintInvoice7.jsp");
    } else {
%>
        <script type="text/javascript">
            alert("Không thể xuất kho do mặt hàng không đủ số lượng tồn kho!");
            history.back();
        </script>
<%
    }
%>