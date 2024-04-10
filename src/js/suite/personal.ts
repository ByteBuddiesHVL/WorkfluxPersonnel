const lonnslippDiv = document.getElementById('lonnslippDiv')!;
const lonnslippWindow = lonnslippDiv.querySelector('dialog')!;

document.getElementById('lagLonnsslipper')!.onclick = () => {
    lonnslippWindow.showModal();
}
