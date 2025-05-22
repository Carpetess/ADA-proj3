package solution;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {
    private List<Node>[] nodesAdjencyList;
    private int maxNumOfAttacks;

    public Solver(List<Node>[] nodesAdjencyList, int numOfAttacks) {
        this.nodesAdjencyList= nodesAdjencyList;
        this.maxNumOfAttacks= numOfAttacks + 1;
    }

    public int solve(int start, int end){

        //TODO
        return 0;
    }

    private int dijkstraAdapted(int start, int end){
        boolean[][] selected = new boolean[maxNumOfAttacks][nodesAdjencyList.length];
        int[][] length = new int[maxNumOfAttacks][nodesAdjencyList.length];
        int[] via = new int[nodesAdjencyList.length];
        PriorityQueue<State> connected =
                new PriorityQueue<>(nodesAdjencyList.length);
        Arrays.fill(length, Integer.MAX_VALUE);
        length[0][start] = 0;
        via[start] = start;

        Iterator<Node> nodesIterator = nodesAdjencyList[start].iterator();
        while (nodesIterator.hasNext())
            connected.add(new State(nodesIterator.next(), 0));
        State currentState;
        Node currentNode;
        do {
            currentState = connected.poll();
            currentNode = currentState.getNode();
            selected[currentState.getAttacksUsed()][currentNode.getNext()] = true;
            exploreNode(currentState, selected, length, via, connected);
        } while (!connected.isEmpty() && currentNode.getNext() != end);
        int solution = Integer.MAX_VALUE;
        for (int i = 0; i < length.length; i++) {
            if(length[i][end] != 0){
                solution = Math.min(solution, length[i][end]);
            }
        }
        return solution;
    }

    private void exploreNode(State source, boolean[][] selected,
                             int[][] length, int[] via, PriorityQueue<State> connected) {
        Node sourceNode = source.getNode();
        Iterator<Node> adjacentNodes = nodesAdjencyList[sourceNode.getCurrent()].iterator();


        while (adjacentNodes.hasNext()){
            Node node = adjacentNodes.next();
            int oppositeNode = node.getNext();
            int currentAttacks = source.getAttacksUsed();
            if(node.isFastTrack()){
                if(!selected[currentAttacks + 1][oppositeNode] && currentAttacks + 1 < maxNumOfAttacks){
                    int newLength = length[currentAttacks][sourceNode.getCurrent()] + sourceNode.getCost()/2;
                    if (newLength < length[currentAttacks + 1][oppositeNode]) {
                        State newState = new State(node, currentAttacks + 1);
                        length[currentAttacks + 1][oppositeNode] = newLength;
                        connected.add(newState);
                    }
                }
            }
            if(!selected[currentAttacks][oppositeNode]){
                int newLength = length[currentAttacks][sourceNode.getCurrent()] + sourceNode.getCost();
                if (newLength < length[currentAttacks][oppositeNode]) {
                    State newState = new State(node, currentAttacks);
                    length[currentAttacks][oppositeNode] = newLength;
                    connected.add(newState);
                }
            }

        }

        }

}
