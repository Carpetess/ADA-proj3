// @author André Filipe 65371 Guilherme Fialho 65581
package solution;

/**
 * Stores information about one possible state of the race.
 */
public class State implements Comparable<State> {
    private int node;
    private int totalDistance;
    private int attacksUsed;

    public State(int node, int attacksUsed, int totalDistance) {
        this.node = node;
        this.totalDistance = totalDistance;
        this.attacksUsed = attacksUsed;
    }

    public int getAttacksUsed() {
        return attacksUsed;
    }

    public int getNode() {
        return node;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(this.totalDistance, o.totalDistance);
    }
}