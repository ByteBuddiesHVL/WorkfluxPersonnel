<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Workflux Suite</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="module" src="../js/suite/${delside == null ? "hjem" : delside}.js"></script>
</head>
<body>
    <header class="header">
        <div class="headerLeftSide">
            <img src="../images/white_transparent_small_ns.png" alt="">
            <h3>- Suite</h3>
        </div>
        <div class="headerRightSide">
            <a href="/logout" class="logout">Logg ut</a>
        </div>
    </header>
    <c:if test="${error != null}">
        <p style="color: red;">${error}</p>
    </c:if>
    <section class="suiteWrapper">
        <div class="suiteChooser">
            <a href="/suite" class="button${delside == null ? " active" : ""}" style="margin-right: 1em;">Hjem</a>
            <a href="/suite/personal" class="button${delside eq "personal" ? " active" : ""}">Personal</a>
            <a href="/suite/ansatt" class="button${delside eq "ansatt" ? " active" : ""}">Ansatt</a>
            <a href="/suite/rapporter" class="button${delside eq "rapporter" ? " active" : ""}">Rapporter</a>
        </div>
        <div class="suite">
            <c:if test="${empty delside}">
                <div id="hjem"></div>
            </c:if>
            <c:if test="${delside eq 'personal'}">
            <div id="personal">
                <button type="button" id="nyAnsatt">Ny Ansatt</button>
                <dialog id="nyAnsattWindow">
                    <h2>Lag ansatt</h2>
                    <form id="navn" method="post" action="/nyAnsatt">
                        <label>
                            Fornavn:
                            <input name="fornavn">
                        </label>
                        <label>
                            Etternavn:
                            <input name="etternavn">
                        </label>
                        <label>
                            Telefonnummer:
                            <input name="telefonnummer">
                        </label>
                        <label>
                            Epost:
                            <input type="email" name="epost">
                        </label>
                        <label>
                            Gatenavn:
                            <input name="gatenavn">
                        </label>
                        <label>
                            Gatenummer:
                            <input name="gatenummer">
                        </label>
                        <label>
                            Postnummer:
                            <input name="postnummer">
                        </label>
                        <label>
                            Stillingsprosent:
                            <input type="number" name="stillingsprosent">
                        </label>
                        <label>
                            Stillingstype:
                            <div class="select">
                                <select name="stillingstype">
                                    <c:forEach items="${stillingstyper}" var="s">
                                        <option value="${s.stillingstypeId}">${s.stillingstype}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </label>
                    </form>
                    <div>
                        <input type="submit" id="lag" value="Lag ansatt" form="navn">
                        <form method="dialog">
                            <button class="button">Lukk</button>
                        </form>
                    </div>
                </dialog>
            </div>
            </c:if>
            <c:if test="${delside eq 'ansatt'}">
            <div id="ansatt">
                <div id="ansattSok">
                    <div id="searchBarAnsatt">
                        <input id="searchInput" type="text"  placeholder="Search..">
                        <svg id="searchLogo" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M416 208c0 45.9-14.9 88.3-40 122.7L502.6 457.4c12.5 12.5 12.5 32.8 0 45.3s-32.8 12.5-45.3 0L330.7 376c-34.4 25.2-76.8 40-122.7 40C93.1 416 0 322.9 0 208S93.1 0 208 0S416 93.1 416 208zM208 352a144 144 0 1 0 0-288 144 144 0 1 0 0 288z"/></svg>
                    </div>
                    <div id="searchResultWrapper" style="display: none"></div>
                </div>
                <dialog id="ansattInfo">
                    <form id="redigerAnsattForm" method="post" action="/redigerAnsatt">
                        <input type="hidden" id="Rbrukernavn" name="brukernavn">

                        <label>
                            Fornavn:
                            <input name="fornavn">
                        </label>
                        <label>
                            Etternavn:
                            <input name="etternavn">
                        </label>
                        <label>
                            Telefonnummer:
                            <input name="telefonnummer">
                        </label>
                        <label>
                            Epost:
                            <input type="email" name="epost">
                        </label>
                        <label>
                            Gatenavn:
                            <input name="gatenavn">
                        </label>
                        <label>
                            Gatenummer:
                            <input name="gatenummer">
                        </label>
                        <label>
                            Postnummer:
                            <input name="postnummer">
                        </label>
                        <label>
                            Stillingsprosent:
                            <input type="number" name="stillingsprosent">
                        </label>
                        <label>
                            Stillingstype:
                            <div class="select">
                                <select name="stillingstype">
                                    <c:forEach items="${stillingstyper}" var="s">
                                        <option value="${s.stillingstypeId}">${s.stillingstype}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </label>
                    </form>
                    <div>
                        <input type="submit" value="Lagre" form="redigerAnsattForm">
                        <label>
                            <input type="checkbox" name="slettAnsatt" form="redigerAnsattForm">
                            SLETT
                        </label>
                        <form method="dialog">
                            <button class="button">Lukk</button>
                        </form>
                    </div>
                </dialog>
            </div>
            </c:if>
            <c:if test="${delside eq 'rapporter'}">
            <div id="rapporter"></div>
            </c:if>
        </div>
    </section>
    <c:if test="${not empty ansatte}">
        <script>
            let ansattListe = ${ansatte}
        </script>
    </c:if>
</body>
</html>