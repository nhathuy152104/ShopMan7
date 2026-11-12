<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="<%=request.getContextPath()%>/gdCustomerMain7.jsp" class="navbar-brand">
                ShopMan7 - Hệ thống Bách hóa Trực tuyến
            </a>
        </div>
        <ul class="navbar-nav mr-auto">
            <li><a href="<%=request.getContextPath()%>/gdCustomerMain7.jsp" class="nav-link">Trang chủ KH</a></li>
            <li><a href="<%=request.getContextPath()%>/gdSearchProduct7.jsp" class="nav-link">Tìm mặt hàng</a></li>
            <li><a href="<%=request.getContextPath()%>/gdSalesStaffMain7.jsp" class="nav-link">NV Bán hàng (Duyệt đơn)</a></li>
        </ul>
        <ul class="navbar-nav">
            <c:if test="${sessionScope.salesStaff != null}">
                <li class="nav-item">
                    <span class="nav-link text-white">NVBH: <b><c:out value="${sessionScope.salesStaff.name}" /></b></span>
                </li>
                <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Đăng xuất</a></li>
            </c:if>
            <c:if test="${sessionScope.salesStaff == null}">
                <li><a href="<%=request.getContextPath()%>/gdLogin7.jsp" class="nav-link">Đăng nhập</a></li>
                <li><a href="<%=request.getContextPath()%>/gdRegister7.jsp" class="nav-link">Đăng ký</a></li>
            </c:if>
        </ul>
    </nav>
</header>
<br>