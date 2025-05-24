package solution;

import java.util.*;

public class Solver {

    private List<Node>[] nodesAdjencyList;
    private int maxNumOfAttacks;

    @SuppressWarnings("unchecked")
    public Solver(int numOfSquares, int numOfAttacks) {
        this.nodesAdjencyList = new LinkedList[numOfSquares];
        this.maxNumOfAttacks = numOfAttacks;
        for (int i = 0; i < numOfSquares; i++) {
            nodesAdjencyList[i] = new LinkedList<>();
        }
    }

    public int solve(int start, int end) {
        return dijkstra(start, end);
    }

    public void addState(int current, int next, int cost, boolean fastTrack) {
        nodesAdjencyList[current].add(new Node(current, next, cost, fastTrack));
    }

    private int dijkstra(int start, int end) {

        boolean[][] visited = new boolean[maxNumOfAttacks + 1][nodesAdjencyList.length];

        int[][] length = new int[maxNumOfAttacks + 1][nodesAdjencyList.length];

        PriorityQueue<State> connected = new PriorityQueue<>();

        for (int i = 0; i <= maxNumOfAttacks; i++) {
            Arrays.fill(length[i], Integer.MAX_VALUE);
        }

        length[0][start] = 0;
        connected.add(new State(start, 0, 0));

        while (!connected.isEmpty()) {
            State current = connected.poll();
            int currentNode = current.getNode();
            int attacksUsed = current.getAttacksUsed();


            if (visited[attacksUsed][currentNode]) {
                continue;
            }

            visited[attacksUsed][currentNode] = true;

            exploreNode(current,connected,length,attacksUsed);

        }

        int result = Integer.MAX_VALUE;
        for (int attacks = 0; attacks <= maxNumOfAttacks; attacks++) {
            result = Math.min(result, length[attacks][end]);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void exploreNode(State current, PriorityQueue<State> connected, int[][] length, int attacksUsed){
        int currentNode = current.getNode();
        int currentDist = current.getTotalDistance();

        for (Node edge : nodesAdjencyList[currentNode]) {
            int nextNode = edge.getNext();
            int edgeCost = edge.getCost();

            int newDist1 = currentDist + edgeCost;
            if (newDist1 < length[attacksUsed][nextNode]) {
                length[attacksUsed][nextNode] = newDist1;
                connected.add(new State(nextNode, attacksUsed, newDist1));
            }

            if (edge.isFastTrack() && attacksUsed < maxNumOfAttacks) {
                int newDist2 = currentDist + edgeCost / 2;
                if (newDist2 < length[attacksUsed + 1][nextNode]) {
                    length[attacksUsed + 1][nextNode] = newDist2;
                    connected.add(new State(nextNode, attacksUsed + 1, newDist2));
                }
            }
        }

    }
}


