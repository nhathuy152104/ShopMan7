<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ Khách hàng - ShopMan7</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container text-center">
        <h3>Trang chủ Khách hàng (GDCustomerMain7.jsp)</h3>
        <hr>
        <p>Chào mừng quý khách đến với Hệ thống Quản lý Bán hàng Bách hóa Trực tuyến ShopMan!</p>
        <a id="subSearchProduct" href="<%=request.getContextPath()%>/gdSearchProduct7.jsp" class="btn btn-success btn-lg">
            Tìm mặt hàng
        </a>
    </div>
</body>
</html>