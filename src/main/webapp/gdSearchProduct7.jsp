<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,dao.*,model.*"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm mặt hàng - ShopMan7</title>
    <script type="text/javascript">
        function validateSearchForm() {
            var kw = document.getElementById("inKeyWord").value.trim();
            if (kw === "") {
                alert("Vui lòng nhập từ khóa tên mặt hàng cần tìm!");
                document.getElementById("inKeyWord").focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<%
    request.setCharacterEncoding("UTF-8");
    String key = request.getParameter("inKeyWord");
    if (key != null && !key.trim().isEmpty() && request.getAttribute("listProduct") == null) {
        ProductDAO7 productDAO = new ProductDAO7();
        List<Product7> listProduct = productDAO.searchProduct(key);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("inKeyWord", key);
    }
%>
<body>
    <%@ include file="header.jsp" %>
    <div class="container">
        <h3 class="text-center">Tìm kiếm mặt hàng (GDSearchProduct7.jsp)</h3>
        <hr>
        <form action="gdSearchProduct7.jsp" method="get" class="form-inline mb-3" onsubmit="return validateSearchForm();">
            <label class="mr-2 font-weight-bold">Tên MH cần tìm:</label>
            <input type="text" id="inKeyWord" name="inKeyWord" value="<c:out value='${inKeyWord}' />"
                   class="form-control mr-2" placeholder="Ví dụ: Sữa tươi" style="width: 280px;">
            <button type="submit" id="subSearch" name="subSearch" class="btn btn-success mr-2">Tìm kiếm</button>
            <a id="subBack" href="gdCustomerMain7.jsp" class="btn btn-secondary">Quay lại</a>
        </form>

        <c:if test="${inKeyWord != null}">
            <c:if test="${not empty listProduct}">
                <table id="tblProduct" class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>TT</th>
                            <th>Mã MH</th>
                            <th>Tên mặt hàng</th>
                            <th>Phân loại</th>
                            <th>Đơn vị tính</th>
                            <th>Đơn giá bán</th>
                            <th>Tồn kho</th>
                            <th>Ảnh minh họa</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${listProduct}" varStatus="loop">
                            <tr>
                                <td class="text-center"><c:out value="${loop.index + 1}" /></td>
                                <td class="text-center"><c:out value="${p.productCode}" /></td>
                                <td>
                                    <a id="subViewDetail" href="gdProductDetail7.jsp?idProduct=<c:out value='${p.id}' />">
                                        <c:out value="${p.productName}" />
                                    </a>
                                </td>
                                <td><c:out value="${p.category}" /></td>
                                <td class="text-center"><c:out value="${p.unit}" /></td>
                                <td class="text-right"><c:out value="${p.price}" /> VND</td>
                                <td class="text-center"><c:out value="${p.stockQuantity}" /></td>
                                <td class="text-center"><c:out value="${p.image}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty listProduct}">
                <div class="alert alert-warning">
                    Không tìm thấy mặt hàng nào phù hợp với từ khóa vừa nhập!
                </div>
            </c:if>
        </c:if>
    </div>
</body>
</html>