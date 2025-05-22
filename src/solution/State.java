package solution;

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

    @Override
    public int compareTo(State o) {
        return Integer.compare(this.totalDistance, o.totalDistance);
    }
}
