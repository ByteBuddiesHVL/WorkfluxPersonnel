const ansatt = document.getElementById("ansatt");
const personal = document.getElementById("personal");
function setActive(button, id) {
    let buttons = document.querySelectorAll('.button');
    buttons.forEach(function (btn) {
        btn.classList.remove('active');
    })
    button.classList.add('active');
    personal.style.display = "none";
    ansatt.style.display = "none";

    id.style.display = "grid";
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