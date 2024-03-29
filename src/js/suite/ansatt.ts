import type {Ansatt} from "../types";

declare const ansattListe: Ansatt[]

const searchInput = <HTMLInputElement>document.getElementById("searchInput")!;
const searchResults = document.getElementById("searchResultWrapper")!;
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

    for (let i = 0, j = 0; i < ansattListe.length && j < 4; i++) {
        if (ansattListe[i][0].includes(searchTag)) {
            searchElems[j++] = ansattListe[i];
        }
    }

    if (searchElems.length) updateSearchResult();
    else searchResults.style.display = 'none';
}

searchResults.onclick = e => {
    const btn = (<Element>e.target).closest("button");
    if (btn) setAnsatt(btn);
}

function updateSearchResult() {
    let html = '';
    searchElems.forEach(e => {
        html += `<button class="searchResult" value="${e[0]}"><strong>${e[1]} - ${e[0]}</strong></button>`
    })
    searchResults.innerHTML = html;
}

const ansattInfo = document.getElementById("ansattInfo")!
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

function setAnsatt(btn: HTMLButtonElement) {
    searchElems = [];
    searchResults.style.display = 'none';
    searchInput.value = '';
    let ele = ansattListe.find(a => a[0] == btn.value)!;

    ansattInfo.style.display = 'flex';

    Rbrukernavn.value = ele[0];
    [Rfornavn.value, Retternavn.value] = ele[1].split(', ');
    Rtelefonnummer.value = ele[2];
    Repost.value = ele[3];
    Rgatenavn.value = ele[4];
    Rgatenummer.value = ele[5];
    Rpostnummer.value = ele[6].split(' ')[0];
    Rstillingsprosent.value = <any>ele[7];
    // @ts-expect-error
    Rstillingstype.value = [].find.call(Rstillingstype.children, option => option.textContent == ele[8]).value;
}
