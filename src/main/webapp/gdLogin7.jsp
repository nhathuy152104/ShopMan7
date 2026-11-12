<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - ShopMan7</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container col-md-4">
        <div class="card">
            <div class="card-body">
                <h3 class="text-center">Đăng nhập ShopMan7</h3>
                <hr>
                <c:if test="${error != null}">
                    <div class="alert alert-danger"><c:out value="${error}" /></div>
                </c:if>
                <form action="<%=request.getContextPath()%>/login" method="post">
                    <fieldset class="form-group">
                        <label>Tên đăng nhập (Username)</label>
                        <input type="text" class="form-control" name="username" value="nvbh" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Mật khẩu (Password)</label>
                        <input type="password" class="form-control" name="password" value="123456" required="required">
                    </fieldset>
                    <button type="submit" class="btn btn-success btn-block">Đăng nhập</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>