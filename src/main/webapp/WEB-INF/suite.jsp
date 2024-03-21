<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Workflux Suite</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <header class="header">
        <div class="headerLeftSide">
            <img src="../images/white_transparent_small_ns.png" alt="">
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
            <div class="button active" onclick="setActive(this,personal)">Personal</div>
            <div class="button" onclick="setActive(this,ansatt)">Ansatt</div>
            <div class="button" onclick="setActive(this,'#rapporter')">Rapporter</div>
        </div>
        <div class="suite">
            <div id="personal">
                <button type="button" id="nyAnsatt">Ny Ansatt</button>
                <div id="nyAnsattWindow">
                    <h1>Lag ansatt</h1>
                    <form id="navn" method="post" action="/nyAnsatt">
                        <label for="fornavn">Fornavn:</label>
                        <input type="text" id="fornavn" name="fornavn"><br><br>
                        <label for="etternavn">Etternavn:</label>
                        <input type="text" id="etternavn" name="etternavn"><br><br>
                        <label for="telefonnummer">Telefonnummer:</label>
                        <input type="text" id="telefonnummer" name="telefonnummer"><br><br>
                        <label for="epost">Epost:</label>
                        <input type="text" id="epost" name="epost"><br><br>
                        <label for="gatenavn">Gatenavn:</label>
                        <input type="text" id="gatenavn" name="gatenavn"><br><br>
                        <label for="gatenummer">Gatenummer:</label>
                        <input type="text" id="gatenummer" name="gatenummer"><br><br>
                        <label for="postnummer">Postnummer:</label>
                        <input type="text" id="postnummer" name="postnummer"><br><br>
                        <label for="stillingsprosent">Stillingsprosent:</label>
                        <input type="text" id="stillingsprosent" name="stillingsprosent"><br><br>
                        <label for="stillingstype">Stillingstype:</label>
                        <input type="text" id="stillingstype" name="stillingstype"><br><br>
                        <input type="submit" id="lag" value="Lag ansatt">
                    </form>
                </div>
            </div>
            <div id="ansatt">
                <div id="ansattSok">
                    <select name="ansattsok">
                        <c:if test="${not empty ansattListe}">
                            <c:forEach items="${ansattListe}" var="ansatt">
                                <option id=${ansatt.brukernavn} value="${ansatt.brukernavn},${ansatt.fornavn},${ansatt.etternavn},${ansatt.telefonnummer}">
                                        ${ansatt.brukernavn}
                                </option>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty ansattListe}">
                            <option value="">Ingen ansatte funnet</option>
                        </c:if>
                    </select>
                </div>
                <div class="ansattInfo" id="ansattInfo"></div>
            </div>
            <div id="rapporter"></div>
        </div>
    </section>


<script src="../js/suite.js">
    const hentAnsatte = ${ansatte}
</script>
</body>
</html>