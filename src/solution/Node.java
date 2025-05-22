package solution;

public class Node {
    private int current;
    private int next;
    private int cost;
    private boolean fastTrack;

    public Node(int current, int next, int cost, boolean fastTrack) {
        this.current = current;
        this.next = next;
        this.cost = cost;
        this.fastTrack = fastTrack;
    }

    public int getCurrent() {
        return current;
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
