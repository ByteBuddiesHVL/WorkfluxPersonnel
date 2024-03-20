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
    </header>
    <section class="inputSection">
        <c:if test="${error != null}">
            <p style="color: red;">${error}</p>
        </c:if>
        <div class="login">
            <div>
                <form action="/login" method="post">
                    <label for="brukernavn">Brukernavn:</label>
                    <input name="brukernavn" id="brukernavn" required>
                    <label for="passord">Passord:</label>
                    <input name="passord" id="passord" type="password" required>
                    <input type="submit" value="Login">
                </form>
            </div>
        </div>
    </section>
</body>
</html>