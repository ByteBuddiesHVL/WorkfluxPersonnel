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

// Midlertidig løsning
const ansattInfo = document.getElementById("ansattInfo");
let ansattSelected = document.querySelector("select").value;
ansattInfo.innerHTML = ansattSelected;
ansattSok.addEventListener("change", (evt) => {
    ansattSelected = document.querySelector("select").value;
    ansattInfo.innerHTML = ansattSelected;
});