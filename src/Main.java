import solution.Node;
import solution.Solver;

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

        Solver solver = new Solver(numOfSquares,numOfAttacks);

        for (int i = 0; i < numOfStreets; i++){
            String[] currentLine = br.readLine().split(" ");
            int current = Integer.parseInt(currentLine[0]);
            int next = Integer.parseInt(currentLine[1]);
            int cost = Integer.parseInt(currentLine[2]);
            boolean fastTrack = currentLine[3].equals("1");
            solver.addState(current, next, cost, fastTrack);
        }


        //int[][] testCases = new int[numOfTestCases][2];
        for (int i = 0; i < numOfTestCases; i++){
            line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            //testCases[i][0] = start;
            //testCases[i][1] = end;
            System.out.println(solver.solve(start, end));
        }

        br.close();
    }
}