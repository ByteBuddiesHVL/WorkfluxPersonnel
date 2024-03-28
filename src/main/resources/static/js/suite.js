const nyAnsatt = document.getElementById("nyAnsatt");
const nyAnsattWindow = document.getElementById("nyAnsattWindow");
const lag = document.getElementById("lag");

nyAnsatt?.addEventListener("click", (event) => {
    event.preventDefault();
    console.log(123);
    if (nyAnsattWindow.style.display === "block") {
        nyAnsattWindow.style.display = "none"
    } else {
        nyAnsattWindow.style.display = "block"
    }
})

lag?.addEventListener("click", () => {
    nyAnsattWindow.style.display = "none";
})

const searchInput = document.getElementById("searchInput");
const searchResults = document.getElementById("searchResultWrapper");
let searchTag = "";
let searchElems = [];

searchInput?.addEventListener("keyup", (event) => {
    searchResults.style.display = 'flex';
    searchTag = searchInput.value;
    if (searchTag === '') {
        searchResults.style.display = 'none';
        return;
    }
    searchElems = [];
    let iterator = 0;
    ansattListe.some(a => {
        if (iterator > 3) return true; //for å minske maks antall søke resultater
        if (a[0].includes(searchTag)) {
            searchElems[iterator++] = a;
        }
    })
    if (searchElems.length === 0) {
        searchResults.style.display = 'none';
        return;
    }
    updateSearchResult()
})

function updateSearchResult() {
    let html = '';
    searchElems.forEach(e => {
        html += '<button class="searchResult" value="'+ e[0] +'" onclick="setAnsatt(this)">' +
            '<h4>'+e[1]+' - ' + e[0]+'</h4></button>'
    })
    searchResults.innerHTML = html;
}

const ansattInfo = document.getElementById("ansattInfo")
const [
    Rbrukernavn,
    Rfornavn,
    Retternavn,
    Rtelefonnummer,
    Repost,
    Rgatenavn,
    Rgatenummer,
    Rpostnummer,
    Rstillingsprosent,
    Rstillingstype,
] = document.getElementById("redigerAnsattForm") || []

function setAnsatt(btn) {
    searchElems = [];
    searchResults.style.display = 'none';
    searchInput.value = '';
    let ele = undefined;

    ansattListe.some(a => {
        if (a[0] === btn.value) {
            ele = a;
            return true;
        }
    })

    ansattInfo.style.display = 'flex';

    Rbrukernavn.value = ele[0];
    [Rfornavn.value, Retternavn.value] = ele[1].split(', ');
    Rtelefonnummer.value = ele[2];
    Repost.value = ele[3];
    Rgatenavn.value = ele[4];
    Rgatenummer.value = ele[5];
    Rpostnummer.value = ele[6].split(' ')[0];
    Rstillingsprosent.value = ele[7];
    Rstillingstype.value = ele[8];
}