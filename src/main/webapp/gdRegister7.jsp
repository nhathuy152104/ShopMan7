<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký thành viên - ShopMan7</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container col-md-6">
        <div class="card">
            <div class="card-body">
                <h3 class="text-center">Đăng ký Thành viên Khách hàng</h3>
                <hr>
                <c:if test="${error != null}">
                    <div class="alert alert-danger"><c:out value="${error}" /></div>
                </c:if>
                <form action="<%=request.getContextPath()%>/register" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Tên đăng nhập (*)</label>
                            <input type="text" class="form-control" name="username" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Mật khẩu (*)</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Họ và tên (*)</label>
                            <input type="text" class="form-control" name="name" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Ngày sinh (*)</label>
                            <input type="date" class="form-control" name="dob" value="2002-01-01" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Số điện thoại (*)</label>
                            <input type="text" class="form-control" name="phone" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Email</label>
                            <input type="email" class="form-control" name="email">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label>Số nhà</label>
                            <input type="text" class="form-control" name="houseName" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label>Đường/Phố</label>
                            <input type="text" class="form-control" name="street" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label>Phường/Xã</label>
                            <input type="text" class="form-control" name="ward" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Quận/Huyện</label>
                            <input type="text" class="form-control" name="district" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label>Tỉnh/Thành phố</label>
                            <input type="text" class="form-control" name="city" required>
                        </div>
                    </div>
                    <input type="hidden" name="building" value="">
                    <button type="submit" class="btn btn-success">Đăng ký</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>