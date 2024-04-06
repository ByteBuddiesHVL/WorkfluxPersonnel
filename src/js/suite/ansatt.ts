import type {Ansatt} from "../types";

type Row = (ansatt: Ansatt) => void

declare const ansattListe: Ansatt[];

const numAnsatte = ansattListe.length;
const searchInput = <HTMLInputElement>document.getElementById("searchInput")!;
const searchResults = document.getElementById("searchResultWrapper")!;
const rowTemplate = document.createElement("tr");
const rows: Row[] = [];
const table = document.querySelector("table")!;
const tbody = table.tBodies[0];

rowTemplate.innerHTML = "<td> </td>".repeat(9) + "<td><button>Endre</button></td>";

for (let i = 0; i < numAnsatte; i++) {
    let hidden = false
    let old: Ansatt
    let tr = rowTemplate.cloneNode(true) as HTMLTableRowElement
    let cells = tr.children;
    let textNodes: Text[] = [];
    let update = (ansatt?: Ansatt) => {
        if (ansatt) {
            if (old != ansatt) {
                for (let i = 0; i < 8; i++) {
                    if (!old || old[i] != ansatt[i]) {
                        textNodes[i].data = ansatt[i] as string;
                    }
                }
            }
            old = ansatt;
        }
        if (hidden != !ansatt) {
            hidden = !hidden;
            tr.style.display = hidden ? "none" : "";
        }
    }

    (cells[8].firstChild as HTMLButtonElement).onclick = () => {
        setAnsatt(old)
    }

    for (let i = 0; i < 8; i++) {
        textNodes[i] = cells[i].firstChild as Text;
    }

    update(ansattListe[i]);

    rows[i] = update;
    tbody.append(tr);
}


let searchTag = "";
let searchElems: Ansatt[] = [];

searchInput.oninput = () => {
    searchResults.style.display = 'flex';
    searchTag = searchInput.value;
    if (searchTag === '') {
        searchResults.style.display = 'none';
        return;
    }
    searchElems = [];

    for (let i = 0, j = 0; i < numAnsatte && j < 4; i++) {
        if (ansattListe[i][0].toLowerCase().includes(searchTag.toLowerCase()) || ansattListe[i][1].toLowerCase().includes(searchTag.toLowerCase())) {
            searchElems[j++] = ansattListe[i];
        }
    }

    if (searchElems.length) updateSearchResult();
    else searchResults.style.display = 'none';
}

function updateSearchResult() {
    let html = '';
    searchElems.forEach(e => {
        html += `<button class="searchResult" value="${e[0]}"><strong>${e[1]} - ${e[0]}</strong></button>`
    })
    searchResults.innerHTML = html;
}

const dialog = document.querySelector("dialog")!
const [
    Rbrukernavn,
    Rfornavn,
    Retternavn,
    Rtelefonnummer,
    Repost,
    Rgatenavn,
    Rgatenummer,
    Rpostnummer,
    Rstillingsprosent,
    Rstillingstype,
] = <HTMLInputElement[]><any>document.querySelector("form")

function setAnsatt(ansatt: Ansatt) {
    dialog.showModal();

    Rbrukernavn.value = ansatt[0];
    [Rfornavn.value, Retternavn.value] = ansatt[1].split(', ');
    Rtelefonnummer.value = ansatt[2];
    Repost.value = ansatt[3];
    ;[Rgatenavn.value, Rgatenummer.value] = ansatt[4].split(/ (?=\w+$)/ as any);
    Rpostnummer.value = ansatt[5].split(' ')[0];
    Rstillingsprosent.value = <any>ansatt[6];
    // @ts-expect-error
    Rstillingstype.value = [].find.call(Rstillingstype.children, option => option.textContent == ele[7]).value;
}
