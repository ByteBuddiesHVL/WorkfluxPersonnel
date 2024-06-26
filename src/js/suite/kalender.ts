import type {Tidsplan} from "../types";

declare let tidsplanListe: Tidsplan[];

const dateInput = document.querySelector('input')!;

const tidsplanDiv = document.getElementById('tidsplan')!;
const ansattDiv = document.getElementById('ansatte')!;
const dialogs = document.getElementsByTagName('dialog');

const endringInputs = dialogs[0].querySelectorAll('input')!;
const endringSelect = dialogs[0].querySelector('select')!;

const rows = new Map<string, number>();
const cacheMap = new Map<string, Tidsplan[]>();

const updateDate = () => {
    const newDate = dateInput.value;
    const cache = cacheMap.get(newDate);
    if (cache) {
        tidsplanListe = cache;
        updateTidsplan(true);
    } else {
        controller?.abort();
        controller = new AbortController();
        fetch("/tidsplan?dag=" + newDate, {
            signal: controller.signal
        }).then(res => res.json().then(json => {
            cacheMap.set(newDate, tidsplanListe = json);
            updateTidsplan(true);
        })).catch(() => {});
    }
}

cacheMap.set(dateInput.value, tidsplanListe);

dateInput.oninput = () => {
    date = dateInput.valueAsDate || date;
    updateDate();
}

let controller: AbortController;
let date = dateInput.valueAsDate!;

document.querySelector<HTMLElement>('.datoVelger')!.onclick = e => {
    const target = e.target as HTMLElement
    if (target.matches('.chevron')) {
        date.setDate(date.getDate() + (target.matches('.right') ? 1 : -1));
        dateInput.valueAsDate = date;
        updateDate();
    }
}

const updateTidsplan = (clear?: boolean) => {
    if (clear) {
        rows.clear();
        ansattDiv.textContent = '';
    }
    tidsplanDiv.textContent = '';


    for (let i = 0; i < tidsplanListe.length; i++) {
        const tidsplan = tidsplanListe[i];
        const brukernavn = tidsplan[1];
        const rowStart = rows.get(brukernavn);

        const skift = document.createElement('div');
        const startTidTime = +tidsplan[3].slice(11, 13);
        const startTidMin = +tidsplan[3].slice(14, 16);
        const sluttTidTime = +tidsplan[4].slice(11, 13);
        const sluttTidMin = +tidsplan[4].slice(14, 16);
        skift.style.left = `${(startTidTime + startTidMin / 60) / 0.24}%`;
        skift.style.right = `${(24 - sluttTidTime - sluttTidMin / 60) / 0.24}%`;
        skift.className = 'skift';

        if (rowStart == null) {
            skift.style.top = `${rows.size * 12.5}%`;
            rows.set(brukernavn, rows.size);
            const ansatt = document.createElement('span');
            ansatt.textContent = tidsplan[2] as any;
            ansattDiv.append(ansatt);
        } else skift.style.top = `${rowStart * 12.5}%`;

        tidsplanDiv.append(skift);
        skift.addEventListener('click', () => {
            endringInputs[0].value = tidsplan[0] as any;
            endringInputs[1].value = tidsplan[3].slice(0, 10);
            endringInputs[2].value = tidsplan[3].slice(11, 16);
            endringInputs[3].value = tidsplan[4].slice(11, 16);
            endringSelect.value = tidsplan[5] as any;
            dialogs[0].showModal();
        })
    }

    history.replaceState({}, '', '?dag=' + dateInput.value);
}

updateTidsplan();

const leggTilInputs = dialogs[1].querySelectorAll('input')!;

const submitForm = (e: Event) => {
    const form = e.target as HTMLFormElement;
    if (form.checkValidity()) {
        controller?.abort();
        controller = new AbortController();
        e.preventDefault();
        fetch(form.action, {
            method: "post",
            signal: controller.signal,
            body: new FormData(form)
        }).then(res => res.json().then(json => {
            cacheMap.set(dateInput.value, tidsplanListe = json);
            (form.parentElement as HTMLDialogElement).close();
            updateTidsplan();
        })).catch(() => {});
    }
}

const forms = document.forms;

forms[0].onsubmit = forms[2].onsubmit = submitForm;

document.querySelector<HTMLButtonElement>('.suite .button')!.onclick = () => {
    leggTilInputs[0].value = dateInput.value;
    dialogs[1].showModal();
}
