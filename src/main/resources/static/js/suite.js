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

// Midlertidig løsning
const ansattInfo = document.getElementById("ansattInfo");
function setAnsatt(btn) {
    ansattInfo.innerHTML = btn.value;
}
/*
let ansattSelected = document.querySelector("select").value;
ansattInfo.innerHTML = ansattSelected;
let searchResults = document.querySelectorAll(".searchResult");
searchResults.forEach(s => s.addEventListener("click",(evt) => {
    ansattInfo.innerHTML = evt.target.innerHTML;
}))
ansattSok.addEventListener("change", (evt) => {
    ansattSelected = document.querySelector("select").value;
    ansattInfo.innerHTML = ansattSelected;
});
*/