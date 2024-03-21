function setActive(button) {
    let buttons = document.querySelectorAll('.button');
    buttons.forEach(function (btn) {
        btn.classList.remove('active');
    })
    button.classList.add('active');
}

const nyAnsatt = document.getElementById("nyAnsatt");
const nyAnsattWindow = document.getElementById("nyAnsattWindow");
const lag = document.getElementById("lag");

nyAnsatt.addEventListener("click", (event) => {
    event.preventDefault();
    if (nyAnsattWindow.style.display === "block") {
        nyAnsattWindow.style.display = "none"
    } else {
        nyAnsattWindow.style.display = "block"
    }
})

lag.addEventListener("click", () => {
    nyAnsattWindow.style.display = "none";
})