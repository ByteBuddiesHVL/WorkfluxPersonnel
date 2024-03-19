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
<section>
    <label>Lagre ny dev</label>
    <form action="/lagreDev" method="post">
        <label>Brukernavn</label>
        <input name="brukernavn" required>
        <label>Passord</label>
        <input name="passord" required>
        <input type="submit" value="OK">
    </form>

    <label>Lagre ny bedrift</label>
    <form action="/lagreBedrift" method="post">
        <label>Navn</label>
        <input name="navn" required>
        <label>Forkortelse</label>
        <input name="forkortelse" required>
        <input type="submit" value="OK">
    </form>

    <label>Lagre ny admin</label>
    <form action="/lagreAdmin" method="post">
        <label>Brukernavn</label>
        <input name="brukernavn" required>
        <label>Bedrift forkortelse</label>
        <input name="forkortelse" required>
        <label>Passord</label>
        <input name="passord" required>
        <input type="submit" value="OK">
    </form>

    <label>Lagre ny ansatt</label>
    <form action="/lagreAnsatt" method="post">
        <label>Bedriftforkortelse</label>
        <input name="forkortelse" required>

        <label>Fornavn</label>
        <input name="fornavn" required>
        <label>Etternavn</label>
        <input name="etternavn" required>

        <label>Telefonnummer</label>
        <input name="telefonnummer">

        <label>Epost</label>
        <input name="epost">

        <label>Gatenavn</label>
        <input name="gatenavn" required>

        <label>Gatenummer</label>
        <input name="gatenummer" required>

        <label>Postnummer</label>
        <input name="postnummer" required>

        <label>Stillingsprosent</label>
        <input name="stillingsprosent" required>

        <label>Stillingstype</label>
        <input name="stillingstype" required>

        <label>Passord</label>
        <input name="passord" required>

        <input type="submit" value="OK">
    </form>
</section>
</body>
</html>