import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


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
                    queue.add(word);
                    edgeMap.put(word, current);
                    visited.put(word, true);
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
            visited.put(current, true);
            this.visitednode++;
            if (current.equals(end)) {
                found = true;
                break;
            }
            Set<String> paths = WordFinder.findWordsWithOneCharDifference(current, wordSet);
            // writer.write(current + ": " + paths + "\n"); // Write to file instead of printing
            for (String word : paths) {
                if (!visited.get(word)) {
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
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(s -> WordFinder.getAstarCost(s, end) + s.cost));
        queue.add(new Node(start, 0));//Astar menggunakan node untuk menyimpan cost saat ini.
        boolean found = false;
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited.put(current.word, true);
            this.visitednode++;
            if (current.word.equals(end)) {
                found = true;
                break;
            }
            Set<String> paths = WordFinder.findWordsWithOneCharDifference(current.word, wordSet);

            for (String word : paths) {
                if (!visited.get(word)) {
                    queue.add(new Node(word, current.cost + 1));
                    if (!edgeMap.containsKey(word)){
                        edgeMap.put(word, current.word);
                    }

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

        Instant end_time = Instant.now();
        duration = Duration.between(start_time, end_time);
    }
    public void printSolution(){
        if (solution.isEmpty()){
            System.out.println("No solution found");
        } else {
            System.out.println("Solution found ("+ solution.size()+") :");
            for (String word : solution){
                System.out.println(word);
            }
        }
        System.out.println("Visited nodes: " + visitednode);
        System.out.println("Time taken: " + duration.toMillis() + "ms");
    }

}
