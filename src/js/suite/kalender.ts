import type {Tidsplan} from "../types";
import type {Dag} from "../types";

declare const tidsplanListe: Tidsplan[];
declare const dag: Dag;

let dagArr = dag.split('-')

let date = new Date(Number(dagArr[0]),Number(dagArr[1]) - 1,Number(dagArr[2]));
let selectorDate = date;
let month = date.getMonth();
let year = date.getFullYear();

const dayCalendar = document.querySelector(".calendar-dates")!;

const currDate = document.querySelector(".calendar-current-date");
const currDateSelector = document.querySelector<HTMLParagraphElement>(".selector-current-date")!;
const prevNextIcons = document.querySelectorAll(".calendar-navigation span");
const prevNextIconsSelector = document.querySelectorAll(".selector-navigation span");

// Array of month names
const months = ["Januar", "Februar", "Mars", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Desember"];

const updateSelector = () => {
    currDateSelector.textContent = `${selectorDate.getDate()} ${months[selectorDate.getMonth()]} ${selectorDate.getFullYear()}`
}

updateSelector();


// Function to generate the calendar
const manipulate = () => {
    // Get the first day of the month
    let dayone = new Date(year, month, 1).getDay();
    dayone = (dayone === 0) ? 6 : dayone - 1;

    // Get the last date of the month
    let lastdate = new Date(year, month + 1, 0).getDate();

    // Get the day of the last date of the month
    let dayend = new Date(year, month, lastdate).getDay();
    dayend = (dayend === 0) ? 6 : dayend - 1;

    // Get the last date of the previous month
    let monthlastdate = new Date(year, month, 0).getDate();

    // Variable to store the generated calendar HTML
    let lit = "";

    // Loop to add the last dates of the previous month
    for (let i = dayone; i > 0; i--) {
        let prevYear = (month - 1) === -1 ? year - 1 : year;
        let prevMonth = (month - 1) === -1 ? 11 : (month - 1);

        let lastMonthDate = monthlastdate - i + 1;
        lit += `<li class="inactive"><a href="/hentDagTidsplan?year=${prevYear}&month=${prevMonth}&day=${lastMonthDate}">${lastMonthDate}</a></li>`;
    }

    // Loop to add the dates of the current month
    for (let i = 1; i <= lastdate; i++) {

        // Check if the current date is today
        let isToday = i === selectorDate.getDate()
        && month === selectorDate.getMonth()
        && year === selectorDate.getFullYear()
            ? "active"
            : "";

        lit += `<li id="${i}" class="${isToday}"><a href="/hentDagTidsplan?year=${year}&month=${month}&day=${i}">${i}</a></li>`;
    }

    // Loop to add the first dates of the next month
    for (let i = dayend; i < 6; i++) {
        let nextYear = (month + 1) === 12 ? year + 1 : year;
        let nextMonth = (month + 1) === 12 ? 0 : (month + 1);

        let nextMonthDate = i - dayend + 1;
        lit += `<li class="inactive"><a href="/hentDagTidsplan?year=${nextYear}&month=${nextMonth}&day=${nextMonthDate}">${nextMonthDate}</a></li>`
    }

    // @ts-ignore
    currDate.innerText = `${months[month]} ${year}`;
    dayCalendar.innerHTML = lit;
}

manipulate();

let timeoutFunc = setTimeout(()=>{},1000);
prevNextIconsSelector.forEach(icon => {
    icon.addEventListener("click", () => {
        clearTimeout(timeoutFunc);
        let offset = icon.id === "selector-prev" ? -1 : 1;

        selectorDate.setDate(selectorDate.getDate() + offset);
        year = selectorDate.getFullYear();
        month = selectorDate.getMonth();

        updateSelector();
        timeoutFunc = setTimeout(() => {
            location.href = `/setDagTidsplan?year=${year}&month=${month + 1}&day=${selectorDate.getDate()}`
        },500) // mulighet for å raskt skippe mellom mange datoer uten at siden reloader
    })
})

prevNextIcons.forEach(icon => {
    icon.addEventListener("click", () => {
        month = icon.id === "calendar-prev" ? month - 1 : month + 1;

        if (month < 0 || month > 11) {
            date = new Date(year, month, new Date().getDate());
            year = date.getFullYear();
            month = date.getMonth();
        } else
            date = new Date();

        manipulate();
    });
});

const calendarPopup = document.getElementById("calendarPopup")!;
document.getElementById("monthIcon")!.addEventListener("click", () => {
    if (calendarPopup.style.display === "none") {
        manipulate();
        calendarPopup.style.display = "block";
    }
    else if (calendarPopup.style.display === "block") calendarPopup.style.display = "none";
})

document.addEventListener("click", function(evt) {
    let targetElement = evt.target;
    // @ts-ignore
    if (!calendarPopup.contains(targetElement) && targetElement.id !== "monthIcon") calendarPopup.style.display = "none"
})

const tidsplanDiv = document.getElementById('tidsplan')!;
const ansattDiv = document.getElementById('ansatte')!;

const skiftVisning = document.getElementById('skiftVisning')!;
const timeEndringDialog = skiftVisning.querySelector('dialog')!;
const endringInputs = timeEndringDialog.querySelectorAll('input')!;
const endringSelect = timeEndringDialog.querySelector('select')!;

const rows = new Map<string, number>();

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
        timeEndringDialog.showModal();
    })
}

document.getElementById('avbrytTimeEndring')!.addEventListener('click', () => {
    timeEndringDialog.close();
})

const timeAnsattDialog = document.querySelector('.raskInfo')!.querySelector('dialog')!;
const leggTilInputs = timeAnsattDialog.querySelectorAll('input')!;
document.getElementById('leggTilAnsatt')!.addEventListener('click', () => {
    leggTilInputs[0].value = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2,'0')}-${String(date.getDate()).padStart(2,'0')}`
    timeAnsattDialog.showModal();
})

document.getElementById('avbrytTimeAnsatt')!.addEventListener('click', () => {
    timeAnsattDialog.close();
})
