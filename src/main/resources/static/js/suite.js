const hjem = document.getElementById("hjem");
const personal = document.getElementById("personal");
const ansatt = document.getElementById("ansatt");
const rapporter = document.getElementById("rapporter");

let allPages = [hjem,personal,ansatt,rapporter]

if (location.pathname.endsWith('/suite')) setSite(hjem);
if (location.pathname.includes('ansatt')) setSite(ansatt);
if (location.pathname.includes('personal')) setSite(personal);
if (location.pathname.includes('rapporter')) setSite(rapporter);

function setSite(page) {
    allPages.forEach(p => p.style.display = "none")
    page.style.display = "grid"
}
function setActive(button) {
    let buttons = document.querySelectorAll('.button');
    buttons.forEach(function (btn) {
        btn.classList.remove('active');
    })
    button.classList.add('active');
    allPages.forEach(p => p.style.display = "none");
}

const nyAnsatt = document.getElementById("nyAnsatt");
const nyAnsattWindow = document.getElementById("nyAnsattWindow");
const lag = document.getElementById("lag");

nyAnsatt.addEventListener("click", (event) => {
    event.preventDefault();
    console.log(123);
    if (nyAnsattWindow.style.display === "block") {
        nyAnsattWindow.style.display = "none"
    } else {
        nyAnsattWindow.style.display = "block"
    }
})

lag.addEventListener("click", () => {
    nyAnsattWindow.style.display = "none";
})

const searchInput = document.getElementById("searchInput");
const searchResults = document.getElementById("searchResultWrapper");
let searchTag = "";
let searchElems = [];

searchInput.addEventListener("keyup", (event) => {
    searchResults.style.display = 'flex';
    searchTag = searchInput.value;
    if (searchTag === '') {
        searchResults.style.display = 'none';
        return;
    }
    searchElems = [];
    let iterator = 0;
    ansattListe.forEach(a => {
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
const Rbrukernavn = document.getElementById("Rbrukernavn")
const Rfornavn = document.getElementById("Rfornavn")
const Retternavn = document.getElementById("Retternavn")
const Rtelefonnummer = document.getElementById("Rtelefonnummer")
const Repost = document.getElementById("Repost")
const Rgatenavn = document.getElementById("Rgatenavn")
const Rgatenummer = document.getElementById("Rgatenummer")
const Rpostnummer = document.getElementById("Rpostnummer")
const Rstillingsprosent = document.getElementById("Rstillingsprosent")
const Rstillingstype = document.getElementById("Rstillingstype")
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
    const navn = ele[1].split(', ')
    Rfornavn.value = navn[1];
    Retternavn.value = navn[0];
    Rtelefonnummer.value = ele[2];
    Repost.value = ele[3];
    Rgatenavn.value = ele[4];
    Rgatenummer.value = ele[5];
    Rpostnummer.value = ele[6].split(' ')[0];
    Rstillingsprosent.value = ele[7];
    Rstillingstype.value = ele[8];
}