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
        return dijkstraAdapted(start, end);
    }

    public void addState(int current, int next, int cost, boolean fastTrack) {
        nodesAdjencyList[current].add(new Node(current, next, cost, fastTrack));
    }

    private int dijkstraAdapted(int start, int end) {
        // visited[attacks][node] - controla se já processamos este estado
        boolean[][] visited = new boolean[maxNumOfAttacks + 1][nodesAdjencyList.length];

        // dist[attacks][node] - menor distância para chegar ao nó com exatamente 'attacks' ataques usados
        int[][] dist = new int[maxNumOfAttacks + 1][nodesAdjencyList.length];

        PriorityQueue<State> pq = new PriorityQueue<>();

        // Inicializar distâncias
        for (int i = 0; i <= maxNumOfAttacks; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Estado inicial: no nó start, 0 ataques usados, distância 0
        dist[0][start] = 0;
        pq.add(new State(start, 0, 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            if (current.getNode() == end) {
                return current.getTotalDistance();
            }
            int currentNode = current.getNode();
            int attacksUsed = current.getAttacksUsed();
            int currentDist = current.getTotalDistance();

            // Se já processamos este estado, pular
            if (visited[attacksUsed][currentNode]) {
                continue;
            }

            visited[attacksUsed][currentNode] = true;

            // Explorar vizinhos
            for (Node edge : nodesAdjencyList[currentNode]) {
                int nextNode = edge.getNext();
                int edgeCost = edge.getCost();

                // Opção 1: Não usar ataque (sempre possível)
                int newDist1 = currentDist + edgeCost;
                if (newDist1 < dist[attacksUsed][nextNode]) {
                    dist[attacksUsed][nextNode] = newDist1;
                    pq.add(new State(nextNode, attacksUsed, newDist1));
                }

                // Opção 2: Usar ataque (se possível e permitido)
                if (edge.isFastTrack() && attacksUsed < maxNumOfAttacks) {
                    int newDist2 = currentDist + edgeCost / 2;
                    if (newDist2 < dist[attacksUsed + 1][nextNode]) {
                        dist[attacksUsed + 1][nextNode] = newDist2;
                        pq.add(new State(nextNode, attacksUsed + 1, newDist2));
                    }
                }
            }
        }

        return -1; // Not found

    }
}


