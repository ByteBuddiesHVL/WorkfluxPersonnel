<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Workflux</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header class="header">
        <img src="images/white_transparent_small_ns.png" alt="">
    </header>
    <section class="inputSection">
        <div class="stemple">
            <h2>Klar for en ny arbeidsdag?</h2>
            <div>
                <form action="/hentstempling" method="get">
                    <input name="brukernavn" pattern="[a-z]{2}\d{6}" required>
                    <input type="submit" value="OK">
                </form>
            </div>
        </div>
    </section>
    <section class="stempleSection" <c:choose><c:when test="${popup eq true}">style="display: flex;"</c:when></c:choose>>
        <div class="stemple-pop_up">
            <form action="/sendstempling" method="post">
                <input type="hidden" name="time" value="${time}">
                <input type="hidden" name="brukernavn" value="${brukernavn}">

                <input name="type" type="submit" value="Inn">
                <input name="type" type="submit" value="Ut">
                <input name="type" type="submit" value="Lunsj">
                <input name="type" type="submit" value="Tilbake">
            </form>
        </div>
    </section>
<script src="js/test.ts"></script>
</body>
</html>