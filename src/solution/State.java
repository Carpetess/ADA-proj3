package solution;

public class State {
    private Node node;
    private int attacksUsed;

    public State(Node node, int attacksUsed) {
        this.node = node;
        this.attacksUsed = attacksUsed;
    }

    public int getAttacksUsed() {
        return attacksUsed;
    }
    public Node getNode() {
        return node;
    }
}
