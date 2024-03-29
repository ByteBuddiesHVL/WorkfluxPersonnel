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
<section style="text-align: center">
    <h2>Lagre ny dev</h2>
    <form action="/lagreDev" method="post">
        <label>
            Brukernavn:
            <input name="brukernavn" required>
        </label>
        <label>
            Passord:
            <input name="passord" type="password" required>
        </label>
        <input type="submit" value="OK">
    </form>

    <h2>Lagre ny bedrift</h2>
    <form action="/lagreBedrift" method="post">
        <label>
            Navn:
            <input name="navn" required>
        </label>
        <label>
            Forkortelse:
            <input name="forkortelse" required>
        </label>
        <input type="submit" value="OK">
    </form>

    <h2>Lagre ny admin</h2>
    <form action="/lagreAdmin" method="post">
        <label>
            Brukernavn:
            <input name="brukernavn" required>
        </label>
        <label>
            Bedrift forkortelse:
            <input name="forkortelse" required>
        </label>
        <label>
            Passord:
            <input name="passord" required>
        </label>
        <input type="submit" value="OK">
    </form>

    <h2>Lagre ny ansatt</h2>
    <form action="/lagreAnsatt" method="post">
        <label>
            Bedrift forkortelse:
            <input name="forkortelse" required>
        </label>
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
            Stillingstype ID:
            <input type="text" name="stillingstype">
        </label>
        <label>
            Passord:
            <input name="passord" type="password" required>
        </label>

        <input type="submit" value="OK">
    </form>
</section>
</body>
</html>