<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,dao.*,model.*"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách đơn hàng chưa xuất - ShopMan7</title>
</head>
<%
    if (request.getAttribute("listPendingOrder") == null) {
        OrderDAO7 orderDAO = new OrderDAO7();
        List<Order7> listPendingOrder = orderDAO.getPendingOrders();
        request.setAttribute("listPendingOrder", listPendingOrder);
    }
%>
<body>
    <%@ include file="header.jsp" %>
    <div class="container">
        <h3 class="text-center">Danh sách đơn hàng chưa xuất (GDApproveOrder7.jsp)</h3>
        <p>NV bán hàng: <b><c:out value="${sessionScope.salesStaff.name}" /></b></p>
        <hr>
        <c:if test="${not empty listPendingOrder}">
            <table id="tblPendingOrder" class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>TT</th>
                        <th>Mã đơn</th>
                        <th>Khách hàng</th>
                        <th>Ngày đặt</th>
                        <th>Địa chỉ nhận hàng</th>
                        <th>Tổng tiền</th>
                        <th>Trạng thái</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${listPendingOrder}" varStatus="loop">
                        <tr>
                            <td class="text-center"><c:out value="${loop.index + 1}" /></td>
                            <td class="text-center">
                                <a id="subSelectOrder" href="gdOrderDetail7.jsp?idOrder=<c:out value='${o.id}' />">
                                    <c:out value="${o.orderCode}" />
                                </a>
                            </td>
                            <td><c:out value="${o.customer.name}" /></td>
                            <td class="text-center"><c:out value="${o.orderDate}" /></td>
                            <td><c:out value="${o.customer.address.fullAddress}" /></td>
                            <td class="text-right"><c:out value="${o.totalAmount}" /> VND</td>
                            <td class="text-center"><c:out value="${o.status}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty listPendingOrder}">
            <div class="alert alert-info">Hiện không có đơn hàng nào đang chờ duyệt xuất kho!</div>
        </c:if>
        <a id="subBack" href="gdSalesStaffMain7.jsp" class="btn btn-secondary">Quay lại</a>
    </div>
</body>
</html>