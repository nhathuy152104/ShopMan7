<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="dao.*,model.*"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin chi tiết mặt hàng - ShopMan7</title>
</head>
<%
    String idParam = request.getParameter("idProduct");
    if (idParam != null && request.getAttribute("product") == null) {
        ProductDAO7 productDAO = new ProductDAO7();
        Product7 product = productDAO.getProductDetail(Integer.parseInt(idParam));
        request.setAttribute("product", product);
    }
%>
<body>
    <%@ include file="header.jsp" %>
    <div class="container col-md-7">
        <div class="card">
            <div class="card-body">
                <h3 class="text-center">Thông tin chi tiết mặt hàng (GDProductDetail7.jsp)</h3>
                <hr>
                <c:if test="${product != null}">
                    <table class="table table-bordered">
                        <tr><th width="220">Mã mặt hàng:</th><td><c:out value="${product.productCode}" /></td></tr>
                        <tr><th>Tên mặt hàng:</th><td><c:out value="${product.productName}" /></td></tr>
                        <tr><th>Phân loại:</th><td><c:out value="${product.category}" /></td></tr>
                        <tr><th>Đơn vị tính:</th><td><c:out value="${product.unit}" /></td></tr>
                        <tr><th>Đơn giá bán niêm yết:</th><td><c:out value="${product.price}" /> VND</td></tr>
                        <tr><th>Số lượng tồn kho hiện tại:</th><td><c:out value="${product.stockQuantity}" /></td></tr>
                        <tr><th>Mô tả chi tiết:</th><td><c:out value="${product.description}" /></td></tr>
                        <tr><th>Ảnh minh họa:</th><td><c:out value="${product.image}" /></td></tr>
                    </table>
                    <div class="text-center mt-3">
                        <button type="button" id="subBack" class="btn btn-secondary mr-2" onclick="history.back();">Quay lại</button>
                        <a href="gdCustomerMain7.jsp" class="btn btn-primary">Về trang chủ</a>
                    </div>
                </c:if>
                <c:if test="${product == null}">
                    <div class="alert alert-danger">Mặt hàng không còn tồn tại!</div>
                    <a href="gdSearchProduct7.jsp" class="btn btn-secondary">Quay lại</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>