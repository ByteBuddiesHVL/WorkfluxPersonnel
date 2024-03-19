<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Workflux Suite</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header class="header">
        <img src="images/white_transparent_small_ns.png" alt="">
        <h3>- Suite</h3>
    </header>
    <c:if test="${error != null}">
        <p style="color: red;">${error}</p>
    </c:if>
    <section class="suiteWrapper">
        <div class="suiteChooser">
            <div class="button active">Personal</div>
            <div class="button">Lønn</div>
            <div class="button">Rapporter</div>
        </div>
        <div class="suite"></div>
    </section>
</body>
</html>