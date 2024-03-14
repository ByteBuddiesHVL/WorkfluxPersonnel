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
    <img src="images/white_transparent_smal_ns.png" alt="">
</header>
<section>
    <div class="stemple">
        <h2>Klar for en ny arbeidsdag?</h2>
        <div>
            <form action="/hentstempling" method="get">
                <input type="text" name="" id="" pattern="/[a-z]{2}[1-9]{6}/">
                <input type="submit" value="OK">
            </form>
        </div>
    </div>
    <div class="stemple-pop_up"></div>
</section>
</body>
</html>