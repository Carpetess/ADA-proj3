package solution;

public class State {
    private int next;
    private int cost;
    private boolean fastTrack;

    public State(int next, int cost, boolean fastTrack) {
        this.next = next;
        this.cost = cost;
        this.fastTrack = fastTrack;
    }

    public int getNext() {
        return next;
    }

    public int getCost() {
        return cost;
    }
    public boolean isFastTrack() {
        return fastTrack;
    }
}
