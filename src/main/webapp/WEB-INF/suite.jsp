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
            <div class="button <c:if test="${empty delside}">active</c:if>" style="margin-right: 1em;" onclick="setActive(this);location.href='/suite'">Hjem</div>
            <div class="button <c:if test="${delside eq 'personal'}">active</c:if>" onclick="setActive(this);location.href='/suite/personal'">Personal</div>
            <div class="button <c:if test="${delside eq 'ansatt'}">active</c:if>" onclick="setActive(this);location.href='/suite/ansatt'">Ansatt</div>
            <div class="button <c:if test="${delside eq 'rapporter'}">active</c:if>" onclick="setActive(this);location.href='/suite/rapporter'">Rapporter</div>
        </div>
        <div class="suite">
            <div id="hjem"></div>
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
                    <div id="searchBarAnsatt">
                        <input id="searchInput" type="text"  placeholder="Search..">
                        <svg id="searchLogo" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"/></svg>
                    </div>
                    <div id="searchResultWrapper" style="display: none"></div>
                </div>
                <div id="ansattInfo" style="display:none;">
                    <form id="redigerAnsattForm" method="post" action="/redigerAnsatt">
                        <input type="hidden" id="Rbrukernavn" name="brukernavn">

                        <label for="fornavn">Fornavn:</label>
                        <input type="text" id="Rfornavn" name="fornavn"><br><br>
                        <label for="etternavn">Etternavn:</label>
                        <input type="text" id="Retternavn" name="etternavn"><br><br>
                        <label for="telefonnummer">Telefonnummer:</label>
                        <input type="text" id="Rtelefonnummer" name="telefonnummer"><br><br>
                        <label for="epost">Epost:</label>
                        <input type="text" id="Repost" name="epost"><br><br>
                        <label for="gatenavn">Gatenavn:</label>
                        <input type="text" id="Rgatenavn" name="gatenavn"><br><br>
                        <label for="gatenummer">Gatenummer:</label>
                        <input type="text" id="Rgatenummer" name="gatenummer"><br><br>
                        <label for="postnummer">Postnummer:</label>
                        <input type="text" id="Rpostnummer" name="postnummer"><br><br>
                        <label for="stillingsprosent">Stillingsprosent:</label>
                        <input type="text" id="Rstillingsprosent" name="stillingsprosent"><br><br>
                        <label for="stillingstype">Stillingstype:</label>
                        <input type="text" id="Rstillingstype" name="stillingstype"><br><br>
                        <input type="submit" id="rediger" value="Lagre">
                    </form>
                </div>
            </div>
            <div id="rapporter"></div>
        </div>
    </section>

<script>
    <c:if test="${not empty ansatte}">
        let ansattListe = ${ansatte};
    </c:if>
</script>
<script src="../js/suite.js"></script>
</body>
</html>