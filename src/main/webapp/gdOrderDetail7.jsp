<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,dao.*,model.*"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Duyệt đơn chi tiết - ShopMan7</title>
    <script type="text/javascript">
        function validateExportForm() {
            var slShipper = document.getElementById("slShipper");
            if (slShipper.value === "-1") {
                alert("Vui lòng chọn nhân viên giao hàng trước khi cập nhật xuất kho!");
                slShipper.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<%
    String idParam = request.getParameter("idOrder");
    if (idParam != null) {
        int idOrder = Integer.parseInt(idParam);
        OrderDAO7 orderDAO = new OrderDAO7();
        ShipperDAO7 shipperDAO = new ShipperDAO7();
        Order7 order = orderDAO.getOrderDetail(idOrder);
        List<Shipper7> listShipper = shipperDAO.getShipperList();
        session.setAttribute("order", order);
        session.setAttribute("listShipper", listShipper);
        request.setAttribute("order", order);
        request.setAttribute("listShipper", listShipper);
    }
%>
<body>
    <%@ include file="header.jsp" %>
    <div class="container">
        <h3 class="text-center">Duyệt đơn chi tiết (GDOrderDetail7.jsp)</h3>
        <hr>
        <c:if test="${order != null}">
            <div class="mb-3">
                <p><b>Mã hóa đơn:</b> <c:out value="${order.orderCode}" />  | 
                   <b>Ngày đặt:</b> <c:out value="${order.orderDate}" />  | 
                   <b>Trạng thái hiện tại:</b> <c:out value="${order.status}" /></p>
                <p><b>Khách hàng:</b> <c:out value="${order.customer.name}" />  | 
                   <b>SĐT:</b> <c:out value="${order.customer.phone}" /></p>
                <p><b>Địa chỉ nhận hàng:</b> <c:out value="${order.customer.address.fullAddress}" /></p>
            </div>

            <table id="tblOrderDetail" class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>TT</th>
                        <th>Mã MH</th>
                        <th>Tên mặt hàng</th>
                        <th>Đơn vị tính</th>
                        <th>Số lượng</th>
                        <th>Đơn giá bán</th>
                        <th>Thành tiền</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="od" items="${order.orderDetails}" varStatus="loop">
                        <tr>
                            <td class="text-center"><c:out value="${loop.index + 1}" /></td>
                            <td class="text-center"><c:out value="${od.product.productCode}" /></td>
                            <td><c:out value="${od.product.productName}" /></td>
                            <td class="text-center"><c:out value="${od.product.unit}" /></td>
                            <td class="text-center"><c:out value="${od.quantity}" /></td>
                            <td class="text-right"><c:out value="${od.unitPrice}" /> VND</td>
                            <td class="text-right"><c:out value="${od.subTotal}" /> VND</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <h5 class="text-right">Tổng tiền hóa đơn: <c:out value="${order.totalAmount}" /> VND</h5>

            <form action="doApproveOrder7.jsp" method="post" onsubmit="return validateExportForm();" class="mt-3">
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label font-weight-bold">Chọn NV giao hàng:</label>
                    <div class="col-sm-6">
                        <select id="slShipper" name="slShipper" class="form-control">
                            <option value="-1">-- Chọn nhân viên giao hàng --</option>
                            <c:forEach var="sh" items="${listShipper}">
                                <option value="<c:out value='${sh.id}' />">
                                    <c:out value="${sh.staffCode}" /> - <c:out value="${sh.name}" />
                                    (SĐT: <c:out value="${sh.phone}" /> - KV: <c:out value="${sh.deliveryArea}" />)
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label font-weight-bold">Trạng thái cập nhật:</label>
                    <div class="col-sm-3">
                        <select id="slStatus" name="slStatus" class="form-control">
                            <option value="Đã xuất" selected>Đã xuất</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-9 offset-sm-3">
                        <a id="subBack" href="gdApproveOrder7.jsp" class="btn btn-secondary mr-2">Quay lại</a>
                        <button type="submit" id="subConfirmExport" name="subConfirmExport" class="btn btn-success">
                            Xác nhận xuất kho
                        </button>
                    </div>
                </div>
            </form>
        </c:if>
    </div>
</body>
</html>