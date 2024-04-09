package bytebuddies;

import bytebuddies.entities.Tidsplan;

import java.util.List;

/**
 * En klasse som representerer resultatet av en tidsplanforespørsel.
 */
public class TidsplanResult {
    private List<Tidsplan> tidsplaner;
    private float timer;

    /**
     * Konstruktør for å opprette et TidsplanResult-objekt med en liste over tidsplaner og totalt antall timer.
     *
     * @param tidsplaner Liste over tidsplaner
     * @param timer      Totalt antall timer
     */
    public TidsplanResult(List<Tidsplan> tidsplaner, float timer) {
        this.tidsplaner = tidsplaner;
        this.timer = timer;
    }

    /**
     * Standardkonstruktør.
     */
    public TidsplanResult() {
    }

    //Getter- og setter-metoder

    public List<Tidsplan> getTidsplaner() {
        return tidsplaner;
    }

    public void setTidsplaner(List<Tidsplan> tidsplaner) {
        this.tidsplaner = tidsplaner;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }
}
