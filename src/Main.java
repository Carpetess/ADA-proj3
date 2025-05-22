import solution.Node;
import solution.Solver;
import solution.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int numOfSquares = Integer.parseInt(line[0]);
        int numOfStreets = Integer.parseInt(line[1]);
        int numOfAttacks = Integer.parseInt(line[2]);
        int numOfTestCases = Integer.parseInt(line[3]);

        List<Node>[] nodes = new LinkedList[numOfSquares];

        for (int i = 0; i < numOfStreets; i++){
            String[] currentLine = br.readLine().split(" ");
            int current = Integer.parseInt(currentLine[0]);
            int next = Integer.parseInt(currentLine[1]);
            int cost = Integer.parseInt(currentLine[2]);
            boolean fastTrack = Boolean.parseBoolean(currentLine[3]);
            if( nodes[current] == null)
                nodes[current] = new LinkedList<>();
            nodes[current].add(new Node(current, next, cost, fastTrack));
        }
        Solver solver = new Solver(nodes, numOfAttacks);
        for (int i = 0; i < numOfTestCases; i++){
            line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int result = solver.solve(start, end);
            System.out.println(result);
        }

        br.close();
    }
}