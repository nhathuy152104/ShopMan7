<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="dao.*,model.*"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ Nhân viên bán hàng - ShopMan7</title>
</head>
<%
    if (session.getAttribute("salesStaff") == null) {
        MemberDAO7 memberDAO = new MemberDAO7();
        SalesStaff7 defaultStaff = memberDAO.getSalesStaffById(4);
        session.setAttribute("salesStaff", defaultStaff);
    }
%>
<body>
    <%@ include file="header.jsp" %>
    <div class="container text-center">
        <h3>Trang chủ Nhân viên bán hàng (GDSalesStaffMain7.jsp)</h3>
        <hr>
        <p>Nhân viên đang đăng nhập: <b><c:out value="${sessionScope.salesStaff.name}" /></b>
           (Mã NV: <b><c:out value="${sessionScope.salesStaff.staffCode}" /></b>)</p>
        <a id="subApproveOrder" href="gdApproveOrder7.jsp" class="btn btn-success btn-lg">
            Duyệt đơn
        </a>
    </div>
</body>
</html>