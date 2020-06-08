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
        <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec velit enim, consequat eget rhoncus non, venenatis nec tellus.
            Nunc egestas consequat blandit. Vivamus consequat libero a sollicitudin facilisis. Maecenas sit amet feugiat sapien.
            Pellentesque rhoncus lorem purus, eu fringilla leo vehicula vitae. Donec convallis urna nibh, ut maximus quam accumsan sed.
            Nunc pellentesque, metus sit amet posuere blandit, metus purus mollis massa, eget ultrices lorem arcu sit amet augue.
            Quisque vitae condimentum tellus, et pretium felis. Nulla sed cursus tortor. Praesent consectetur placerat ipsum, sit amet condimentum nisl luctus non.
            Phasellus sit amet molestie magna. Suspendisse maximus nulla vel tempor egestas. Nullam in lobortis leo, iaculis euismod lectus.
        </p>
    </div>
</body>
</html>
