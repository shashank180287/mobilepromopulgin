<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Spring MVC Multiple File Upload</title>
</head>
<body>
    <h1>Mobile Tool File Upload Page</h1>
    <p>Following files are uploaded successfully.</p>
    <ol>
        <c:forEach items="${files}" var="file">
            <li>${file.key} :: ${file.value}</li>
        </c:forEach>
    </ol>
</body>
</html>