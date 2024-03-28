const nyAnsattWindow = document.getElementById("nyAnsattWindow")!;
let open = false;

document.getElementById("nyAnsatt")!.onclick = () => {
    nyAnsattWindow.style.display = open ? "none" : "block";
    open = !open;
}
