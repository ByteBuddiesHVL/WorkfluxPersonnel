package bytebuddies;

import bytebuddies.entities.Tidsplan;

import java.util.List;

public class TidsplanResult {
    private List<Tidsplan> tidsplaner;
    private float timer;

    public TidsplanResult(List<Tidsplan> tidsplaner, float timer) {
        this.tidsplaner = tidsplaner;
        this.timer = timer;
    }

    public TidsplanResult() {
    }

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
