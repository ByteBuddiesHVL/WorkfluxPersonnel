import type {Ansatt} from "../types";

type Row = (ansatt: Ansatt | null) => void

declare const ansattListe: Ansatt[];

const numAnsatte = ansattListe.length;
const lowerCased: Ansatt[] = Array(numAnsatte);
const searchInput = document.querySelector("input")!;
const rowTemplate = document.createElement("tr");
const rows: Row[] = [];
const table = document.querySelector("table")!;
const tbody = table.tBodies[0];
const headerRow = table.querySelector("tr")!;
const btns = [...headerRow.getElementsByTagName("button")]

const sort = () => {
    sortOrder = Array(numAnsatte);
    for (let i = 0; i < numAnsatte; i++) {
        sortOrder[i] = [ansattListe[i][sortId], i];
    }
    sortOrder.sort(([a], [b]) => {
        return a > b ? order : a == b ? 0 : -order
    })
    updateTable();
}

const updateTable = () => {
    for (let i = 0; i < numAnsatte; i++) {
        const index = sortOrder[i][1];
        rows[i](excluded[index] ? null : ansattListe[index]);
    }
}

headerRow.onclick = e => {
    const newId = btns.indexOf(e.target as HTMLButtonElement);
    if (newId + 1) {
        if (newId == sortId) order = order < 0 ? 1 : -1;
        else {
            btns[sortId].parentElement!.removeAttribute('aria-sort');
            order = 1;
        }
        btns[sortId = newId].parentElement!.setAttribute('aria-sort', order > 0 ? 'ascending' : 'descending');
        sort();
    }
};

rowTemplate.innerHTML = "<td> </td>".repeat(8) + "<td><button>Endre</button></td>";

for (let i = 0; i < numAnsatte; i++) {
    let hidden = false
    let old: Ansatt
    let tr = rowTemplate.cloneNode(true) as HTMLTableRowElement
    let cells = tr.children;
    let textNodes: Text[] = [];
    let update: Row = ansatt => {
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
    let lowerCase = lowerCased[i] = Array(8) as Ansatt;

    (cells[8].firstChild as HTMLButtonElement).onclick = () => {
        setAnsatt(old)
    }

    for (let j = 0; j < 8; j++) {
        textNodes[j] = cells[j].firstChild as Text;
        lowerCase[j] = j == 6 || j == 8 ? ansattListe[i][j] : (ansattListe[i][j] as string).toLowerCase();
    }

    rows[i] = update;
    tbody.append(tr);
}

let sortId = 0;
let order = 1;
let sortOrder: [string | number, number][];
let excluded: boolean[] = [];

searchInput.oninput = () => {
    excluded = [];
    const searchTerm = searchInput.value.toLowerCase();
    const l = searchTerm.length;
    for (let i = 0; i < numAnsatte; i++) {
        let name = lowerCased[i][1];
        let j = 0, pos = 0;
        while (j < l && (pos = name.indexOf(searchTerm[j], pos) + 1)) j++;
        excluded[i] = j < l;
    }
    updateTable();
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
    Rtimelonn,
    Rstillingsprosent,
    Rstillingstype,
] = <HTMLInputElement[]><any>document.querySelector("form")

const setAnsatt = (ansatt: Ansatt) => {
    dialog.showModal();

    Rbrukernavn.value = ansatt[0];
    [Rfornavn.value, Retternavn.value] = ansatt[1].split(', ');
    Rtelefonnummer.value = ansatt[2];
    Repost.value = ansatt[3];
    ;[Rgatenavn.value, Rgatenummer.value] = ansatt[4].split(/ (?=\w+$)/ as any);
    Rpostnummer.value = ansatt[5].split(' ')[0];
    Rtimelonn.value = <any>ansatt[8];
    Rstillingsprosent.value = <any>ansatt[6];
    // @ts-expect-error
    Rstillingstype.value = [].find.call(Rstillingstype.children, option => option.textContent == ansatt[7]).value;
}

sort();
