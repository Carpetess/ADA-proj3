// @author André Filipe 65371 Guilherme Fialho 65581
package solution;

/**
 * Represents a square in the context of the race.
 */
public class Node {
    private int next;
    private int cost;
    private boolean fastTrack;

    public Node(int next, int cost, boolean fastTrack) {
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
