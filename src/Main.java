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

        List<State>[] states = new LinkedList[numOfSquares];

        for (int i = 0; i < numOfSquares; i++){
            String[] currentLine = br.readLine().split(" ");
            int start = Integer.parseInt(currentLine[0]);
            int end = Integer.parseInt(currentLine[1]);
            int cost = Integer.parseInt(currentLine[2]);
            boolean fastTrack = Boolean.parseBoolean(currentLine[3]);
            if( states[start] == null)
                states[start] = new LinkedList<>();
                
        }



    }
}