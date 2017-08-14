<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<body>
<h2>Spring Boot Example</h2>

<c:if test="${not empty lists}">

    <ul>
        <c:forEach var="listValue" items="${lists}">
            <li>${listValue}</li>
        </c:forEach>
    </ul>

</c:if>

<h2>${bred}</h2>

</body>
</html>