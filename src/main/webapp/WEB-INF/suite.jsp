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
        <div class="headerLeftSide">
            <img src="images/white_transparent_small_ns.png" alt="">
            <h3>- Suite</h3>
        </div>
        <div class="headerRightSide">
            <h4 class="logout" onclick="location.href='/logout'">Logg ut</h4>
        </div>
    </header>
    <c:if test="${error != null}">
        <p style="color: red;">${error}</p>
    </c:if>
    <section class="suiteWrapper">
        <div class="suiteChooser">
            <div class="button active" onclick="setActive(this)">Personal</div>
            <div class="button" onclick="setActive(this)">Lønn</div>
            <div class="button" onclick="setActive(this)">Rapporter</div>
        </div>
        <div class="suite">
            <div class="personal"></div>
            <div class="lonn"></div>
            <div class="rapporter"></div>
        </div>
    </section>


<script>
    function setActive(button) {
        let buttons = document.querySelectorAll('.button');
        buttons.forEach(function (btn){
            btn.classList.remove('active');
        });
        button.classList.add('active');
    }
</script>
</body>
</html>