<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="common/head.jsp" />
</head>
<body>
    <div class="container">
        <jsp:include page="common/navbar.jsp" />
        <div class="d-flex justify-content-center">
            <h1><%= request.getAttribute("title")%></h1>
        </div>

        <table class="table table-hover">
            <thead class="thead-dark">
            <tr>
                <th>Товар</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Сумма</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="cartRecord" items="${requestScope.order.cartRecords}">
                    <tr>
                        <td>
                            <c:url var="productUrl" value="/shop/product">
                                <c:param name="id" value="${cartRecord.product.id}" />
                            </c:url>
                            <a href="${productUrl}"><c:out value="${cartRecord.product.title}" /></a>
                        </td>
                        <td>
                            <c:out value="${cartRecord.product.price}" /> руб.
                        </td>
                        <td>
                            <c:out value="${cartRecord.quantity}" />
                        </td>
                        <td>
                            <c:out value="${cartRecord.price}" /> руб.
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="d-flex justify-content-between">
            <a class="btn btn-secondary" href="<c:url value="/shop/orders"/>">К заказам</a>
            <h5>Всего: <c:out value="${requestScope.order.price}" /> руб</h5>
        </div>
    </div>
</body>
</html>
