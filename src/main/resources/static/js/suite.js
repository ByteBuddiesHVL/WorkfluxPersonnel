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

function filtrerAnsatte() {
    const wrapper = document.getElementById("searchResultWrapper");
    const buttons = wrapper.getElementsByTagName("button");
    const input = document.getElementById("searchInput");
    const filter = input.value.toUpperCase();

    for (let i = 0; i < buttons.length; i++) {
        let searchText = buttons[i].textContent;
        if (searchText.toUpperCase().indexOf(filter) > -1) {
            buttons[i].style.display = "";
        } else {
            buttons[i].style.display = "none";
        }
    }
}

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
        if (iterator > 2) return;
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
            '<h4>'+e[1]+' '+e[2] + ' - ' + e[0]+'</h4></button>'
    })
    searchResults.innerHTML = html;
}


// Midlertidig løsning
const ansattInfo = document.getElementById("ansattInfo");
function setAnsatt(btn) {
    searchElems = [];
    searchResults.style.display = 'none';
    searchInput.value = '';
    ansattInfo.innerHTML = btn.value;
}