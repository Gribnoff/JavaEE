<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/shop/home"/>">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/shop/catalog"/>">Catalog</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/shop/cart"/>">Cart</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/shop/orders"/>">Orders</a>
            </li>
        </ul>
    </div>
</nav>
