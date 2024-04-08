const nyAnsattDiv = document.getElementById('nyAnsattDiv')!;
const nyAnsattWindow = nyAnsattDiv.querySelector("dialog")!;

const lonnslippDiv = document.getElementById('lonnslippDiv')!;
const lonnslippWindow = lonnslippDiv.querySelector('dialog')!;

document.getElementById('nyAnsatt')!.onclick = () => {
    nyAnsattWindow.showModal();
}

document.getElementById('lagLonnsslipper')!.onclick = () => {
    lonnslippWindow.showModal();
}