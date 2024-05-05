import java.util.ArrayList;
import java.time.Instant;
// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;
import java.time.Duration;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Comparator;

public class SolutionFinder {
    private ArrayList<String> solution;
    private int visitednode;
    Duration duration;
    public SolutionFinder(){
        solution = new ArrayList<>();
        visitednode = 0;
        duration = Duration.ZERO;
    }
    public void UCS(String start, String end, Set<String> wordSet){
        Instant start_time = Instant.now();
        Map<String, String> edgeMap = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        for (String str : wordSet) {
            visited.put(str, false);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.put(start, true);
        boolean found = false;
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
        while (!queue.isEmpty()) {
            String current = queue.poll();

            this.visitednode++;
            if (current.equals(end)) {
                found = true;
                break;
            }
            Set<String> paths = WordFinder.findWordsWithOneCharDifference(current, wordSet);
            // writer.write(current + ": " + paths + "\n"); 
            for (String word : paths) {
                if (!visited.get(word)) {
                    visited.put(word, true);
                    queue.add(word);
                    edgeMap.put(word, current);
                }
            }
        }
        if (found) {
            String current = end;
            while (!current.equals(start)) {
                this.solution.add(0, current);
                current = edgeMap.get(current);
            }
            this.solution.add(0, start);
        }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        Instant end_time = Instant.now();
        duration = Duration.between(start_time, end_time);
    } 
    public void GreedyBestFirstSearch(String start, String end, Set<String> wordSet){
        Instant start_time = Instant.now();
        Map<String, String> edgeMap = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        for (String str : wordSet) {
            visited.put(str, false);
        }
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(s -> WordFinder.getDistance(s, end)));
        queue.add(start);
        visited.put(start, true);
        boolean found = false;
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
        while (!queue.isEmpty()) {
            String current = queue.poll();

            this.visitednode++;
            if (current.equals(end)) {
                found = true;
                break;
            }
            Set<String> paths = WordFinder.findWordsWithOneCharDifference(current, wordSet);
            // writer.write(current + ": " + paths + "\n"); // Write to file instead of printing
            for (String word : paths) {
                if (!visited.get(word)) {
                    visited.put(word, true);
                    queue.add(word);
                    edgeMap.put(word, current);
                }
            }
        }
        if (found) {
            String current = end;
            while (!current.equals(start)) {
                this.solution.add(0, current);
                current = edgeMap.get(current);
            }
            this.solution.add(0, start);
        }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        Instant end_time = Instant.now();
        duration = Duration.between(start_time, end_time);
    }
    public void Astar(String start, String end, Set<String> wordSet){
        Instant start_time = Instant.now();
        Map<String, String> edgeMap = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        for (String str : wordSet) {
            visited.put(str, false);
        }
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(s -> WordFinder.getAstarHeuristic(s, start, end)));
        queue.add(start);
        visited.put(start, true);
        boolean found = false;
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
        while (!queue.isEmpty()) {
            String current = queue.poll();

            this.visitednode++;
            if (current.equals(end)) {
                found = true;
                break;
            }
            Set<String> paths = WordFinder.findWordsWithOneCharDifference(current, wordSet);
            // writer.write(current + ": " + paths + "\n");
            for (String word : paths) {
                if (!visited.get(word)) {
                    visited.put(word, true);
                    queue.add(word);
                    edgeMap.put(word, current);
                }
            }
        }
        if (found) {
            String current = end;
            while (!current.equals(start)) {
                this.solution.add(0, current);
                current = edgeMap.get(current);
            }
            this.solution.add(0, start);
        }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        Instant end_time = Instant.now();
        duration = Duration.between(start_time, end_time);
    }
    public void printSolution(){
        if (solution.isEmpty()){
            System.out.println("No solution found");
        } else {
            System.out.println("Solution found:");
            for (String word : solution){
                System.out.println(word);
            }
        }
        System.out.println("Visited nodes: " + visitednode);
        System.out.println("Time taken: " + duration.toMillis() + "ms");
    }
}
