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
                <th>Наименование</th>
                <th>Описание</th>
                <th>Цена</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${requestScope.products}">
                    <tr>
                        <td>
                            <c:url var="productUrl" value="/shop/product">
                                <c:param name="id" value="${product.id}" />
                            </c:url>
                            <a href="${productUrl}"><c:out value="${product.title}" /></a>
                        </td>
                        <td>
                            <c:out value="${product.description}" />
                        </td>
                        <td>
                            <c:out value="${product.price}" /> руб.
                        </td>
                        <td>
                            <c:url value="/shop/cart/add" var="addToCartUrl">
                                <c:param name="id" value="${product.id}" />
                            </c:url>
                            <a href="${addToCartUrl}"><i class="fas fa-cart-plus fa-lg"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
