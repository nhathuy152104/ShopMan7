<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.*"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>In hóa đơn xuất kho - ShopMan7</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container col-md-8">
        <div class="card border-primary">
            <div class="card-body">
                <h3 class="text-center">In hóa đơn xuất kho (GDPrintInvoice7.jsp)</h3>
                <h5 class="text-center text-muted">Hóa đơn bán hàng và phiếu xuất kho ShopMan</h5>
                <hr>
                <c:if test="${sessionScope.order != null}">
                    <div class="row mb-2">
                        <div class="col-md-6"><b>Mã hóa đơn:</b> <c:out value="${sessionScope.order.orderCode}" /></div>
                        <div class="col-md-6"><b>Ngày đặt:</b> <c:out value="${sessionScope.order.orderDate}" /></div>
                    </div>
                    <div class="row mb-2">
                        <div class="col-md-6"><b>Ngày xuất kho:</b> <c:out value="${sessionScope.order.exportDate}" /></div>
                        <div class="col-md-6"><b>Trạng thái:</b> <c:out value="${sessionScope.order.status}" /></div>
                    </div>
                    <div class="row mb-2">
                        <div class="col-md-6"><b>Khách hàng:</b> <c:out value="${sessionScope.order.customer.name}" /></div>
                        <div class="col-md-6"><b>SĐT:</b> <c:out value="${sessionScope.order.customer.phone}" /></div>
                    </div>
                    <div class="row mb-2">
                        <div class="col-md-12"><b>Địa chỉ giao:</b> <c:out value="${sessionScope.order.customer.address.fullAddress}" /></div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6"><b>NV bán hàng:</b> <c:out value="${sessionScope.order.salesStaff.name}" /></div>
                        <div class="col-md-6"><b>NV giao hàng:</b> <c:out value="${sessionScope.order.shipper.name}" /></div>
                    </div>

                    <table class="table table-bordered">
                        <thead class="thead-light">
                            <tr>
                                <th>TT</th>
                                <th>Mã MH</th>
                                <th>Tên mặt hàng</th>
                                <th>ĐVT</th>
                                <th>SL</th>
                                <th>Đơn giá</th>
                                <th>Thành tiền</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="od" items="${sessionScope.order.orderDetails}" varStatus="loop">
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
                    <h5 class="text-right">Tổng cộng thanh toán: <c:out value="${sessionScope.order.totalAmount}" /> VND</h5>
                    <div class="text-center mt-4">
                        <button type="button" id="subPrint" class="btn btn-primary mr-2" onclick="window.print();">In hóa đơn</button>
                        <a id="subFinish" href="gdSalesStaffMain7.jsp" class="btn btn-success">Hoàn tất</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>