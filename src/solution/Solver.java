package solution;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {
    private List<Node>[] nodesAdjencyList;
    private int maxNumOfAttacks;

    public Solver(List<Node>[] nodesAdjencyList, int numOfAttacks) {
        this.nodesAdjencyList = nodesAdjencyList;
        this.maxNumOfAttacks = numOfAttacks;
    }

    public int solve(int start, int end) {
        return dijkstraAdapted(start, end);
    }

    private int dijkstraAdapted(int start, int end) {
        boolean[][] selected = new boolean[maxNumOfAttacks + 1][nodesAdjencyList.length];
        int[][] length = new int[maxNumOfAttacks + 1][nodesAdjencyList.length];
        PriorityQueue<State> connected = new PriorityQueue<>(nodesAdjencyList.length);
        for (int i = 0; i <= maxNumOfAttacks; i++) {
            Arrays.fill(length[i], Integer.MAX_VALUE);
        }
        length[0][start] = 0;

        connected.add(new State(start, 0, 0));
        while (!connected.isEmpty()) {
            State currentState = connected.poll();
            int currentNode = currentState.getNode();
            if (currentNode == end) {
                return length[currentState.getAttacksUsed()][end];
            }
            if (!selected[currentState.getAttacksUsed()][currentNode]) {
                selected[currentState.getAttacksUsed()][currentNode] = true;
                exploreNode(currentState, length, connected);
            }
        }
        return -1; // not found
    }

    private void exploreNode(State source, int[][] length, PriorityQueue<State> connected) {
        for (Node node : nodesAdjencyList[source.getNode()]) {
            int oppositeNode = node.getNext();
            int currentAttacks = source.getAttacksUsed();
            if (node.isFastTrack() && currentAttacks < maxNumOfAttacks) {
                int newLength = length[currentAttacks][node.getCurrent()] + node.getCost() / 2;
                if (newLength < length[currentAttacks][oppositeNode]) {
                    State newState = new State(node.getNext(), currentAttacks + 1, newLength);
                    length[currentAttacks + 1][oppositeNode] = newLength;
                    connected.add(newState);
                }
            }
            int newLength = length[currentAttacks][node.getCurrent()] + node.getCost();
            if (newLength < length[currentAttacks][oppositeNode]) {
                State newState = new State(node.getNext(), currentAttacks, newLength);
                length[currentAttacks][oppositeNode] = newLength;
                connected.add(newState);
            }
        }
    }


}
