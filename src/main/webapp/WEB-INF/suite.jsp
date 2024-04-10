<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Workflux Suite</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/suite/style.css">
    <link rel="stylesheet" href="../css/suite/${delside == null ? "hjem" : delside}.css">
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
            <a href="/suite/kalender" class="button${delside eq "kalender" ? " active" : ""}">Kalender</a>
            <a href="/suite/ansatt" class="button${delside eq "ansatt" ? " active" : ""}">Ansatt</a>
            <a href="/suite/rapporter" class="button${delside eq "rapporter" ? " active" : ""}">Rapporter</a>
        </div>
        <div class="suite">
            <c:if test="${empty delside}">
                <div id="hjem"></div>
            </c:if>
            <c:if test="${delside eq 'personal'}">
            <div id="personal">
                <section>
                    <div id="lonnslippDiv">
                        <button type="button" id="lagLonnsslipper">Lag Lønnsslipper</button>
                        <dialog id="lonnsslippWindow">
                            <h2>Generer lønnsslipper</h2>
                            <form id="lonnsslippForm" method="post" action="/genererLonnsslipper">
                                <label>
                                    Måned for generering:
                                    <input type="month" name="month" required>
                                </label>
                                <div>
                                    <input type="radio" id="radio1" name="for" value="all" required checked>
                                    <label for="radio1">Alle</label>
                                </div>
                                <div>
                                    <input type="radio" id="radio2" name="for" value="one" required>
                                    <label for="radio2">
                                        Enkel:
                                        <div class="select">
                                            <select name="brukernavn">
                                                <c:forEach items="${ansattListe}" var="a">
                                                    <option value="${a.brukernavn}">${a.brukernavn} - ${a.fornavn} ${a.etternavn}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </label>
                                </div>
                            </form>
                            <div>
                                <input type="submit" id="lagSlipper" value="Generer" form="lonnsslippForm">
                                <form method="dialog">
                                    <button class="button">Lukk</button>
                                </form>
                            </div>
                        </dialog>
                    </div>
                    <div id="nyAnsattDiv">
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
                                    Timelønn:
                                    <input type="number" step=".01" min="0.00" name="timelonn">
                                </label>
                                <label>
                                    Stillingsprosent:
                                    <input type="number" step=".01" min="0.00" name="stillingsprosent">
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
                </section>
            </div>
            </c:if>
            <c:if test="${delside eq 'kalender'}">
            <div id="kalender">
                <section class="datoVelger">
                    <label>
                        Dag:
                        <input type="date" value="${dag}">
                    </label>
                    <button class="chevron"></button>
                    <button class="chevron right"></button>
                </section>
                <section class="raskInfo">
                    <button id="leggTilAnsatt">Legg til nytt skift</button>
                    <dialog id="timeAnsatt">
                        <form action="/timeAnsatt">
                            <input type="hidden" name="date">

                            <label>
                                Ansatt:
                                <div class="select">
                                    <select name="brukernavn" required>
                                        <c:forEach items="${ansattListe}" var="a">
                                            <option value="${a.brukernavn}">${a.brukernavn} - ${a.fornavn} ${a.etternavn}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </label>
                            <label>
                                Starttid:
                                <input type="time" name="starttid" required>
                            </label>
                            <label>
                                Sluttid:
                                <input type="time" name="sluttid" required>
                            </label>
                            <label>
                                Tidsplantype:
                                <div class="select">
                                    <select name="tidsplantype" required>
                                        <c:forEach items="${tidsplantyper}" var="t">
                                            <option value="${t.tidsplantypeId}">${t.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </label>
                            <button id="avbrytTimeAnsatt" type="button">Avbryt</button>
                            <input type="submit" value="Legg til">
                        </form>
                    </dialog>
                </section>
                <section class="tidsRedigering">
                    <div id="skiftVisning">
                        <div id="dato"></div>
                        <div id="timer">
                            <span style="justify-content: left">0</span>
                            <span>1</span>
                            <span>2</span>
                            <span>3</span>
                            <span>4</span>
                            <span>5</span>
                            <span>6</span>
                            <span>7</span>
                            <span>8</span>
                            <span>9</span>
                            <span>10</span>
                            <span>11</span>
                            <span>12</span>
                            <span>13</span>
                            <span>14</span>
                            <span>15</span>
                            <span>16</span>
                            <span>17</span>
                            <span>18</span>
                            <span>19</span>
                            <span>20</span>
                            <span>21</span>
                            <span>22</span>
                            <span>23</span>
                        </div>
                        <div id="ansatte"></div>
                        <div id="tidsplan"></div>
                        <dialog id="timeEndring">
                            <form action="/endreTime">
                                <input type="hidden" name="tidsplanId">
                                <input type="hidden" name="date">

                                <label>
                                    Starttid:
                                    <input type="time" name="starttid" required>
                                </label>
                                <label>
                                    Sluttid:
                                    <input type="time" name="sluttid" required>
                                </label>
                                <label>
                                    Tidsplantype:
                                    <div class="select">
                                        <select name="tidsplantype" required>
                                            <c:forEach items="${tidsplantyper}" var="t">
                                                <option value="${t.tidsplantypeId}">${t.type}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </label>
                                <button id="avbrytTimeEndring" type="button">Avbryt</button>
                                <input type="submit" value="Lagre">
                            </form>
                        </dialog>
                    </div>
                </section>
            </div>
            </c:if>
            <c:if test="${delside eq 'ansatt'}">
            <div id="ansatt">
                <div id="ansattSok">
                    <div>
                        <input placeholder="Search..">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32"><circle cx="13" cy="13" r="11"></circle><path d="m22 22l8 8"></path></svg>
                    </div>
                </div>
                <div class="table">
                    <table>
                        <thead>
                            <tr>
                                <th aria-sort="ascending"><button>Brukernavn</button></th>
                                <th><button>Navn</button></th>
                                <th><button>Tlf&nbsp;nummer</button></th>
                                <th><button>Epost</button></th>
                                <th><button>Adresse</button></th>
                                <th><button>Poststed</button></th>
                                <th><button>Stilling</button></th>
                                <th><button>Rolle</button></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
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
                            Timelønn:
                            <input type="number" step=".01" min="0.00" name="timelonn">
                        </label>
                        <label>
                            Stillingsprosent:
                            <input type="number" step=".01" min="0.00"  name="stillingsprosent">
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
            <div id="rapporter">
                <section class="lonnsRapporter">
                    <div id="lonnHeader"></div>
                    <div id="lonnBody">
                        <c:forEach items="${lonnsslipper}" var="slipp">
                            <div class="resourceDiv">
                                <div class="rapportAnsattinfo">
                                    <p>${slipp.ansattId.brukernavn}</p>
                                    <p>${slipp.ansattId.fornavn} ${slipp.ansattId.etternavn} : Lønnsslipp ${slipp.dato}</p>
                                </div>
                                <a class="rapportLastned" href="/getLonnsslipp?filnavn=${slipp.dato}_${slipp.ansattId.brukernavn}">Last ned</a>
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </div>
            </c:if>
        </div>
    </section>
    <c:if test="${not empty ansatte}">
        <script>
            let ansattListe = ${ansatte};
            <c:if test="${not empty tidsplan}">
            let tidsplanListe = ${tidsplan};
            </c:if>
        </script>
    </c:if>
</body>
</html>