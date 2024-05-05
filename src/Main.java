import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "words_alpha.txt";
        Set<String> words = null;
        try {
            words = WordReader.readWordsFromFile(filePath);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        SolutionFinder a = new SolutionFinder();
        System.out.print("Start word: ");
        String start = scanner.nextLine();
        while (!words.contains(start.toLowerCase())){
            System.out.println("The word is not in the list");
            System.out.print("Start word: ");
            start = scanner.nextLine();
        }
        System.out.print("End word: ");
        String end = scanner.nextLine();
        while (!words.contains(end.toLowerCase())){
            System.out.println("The word is not in the list");
            System.out.print("End word: ");
            end = scanner.nextLine();
        }
        while (start.length()!=end.length()){
            System.out.println("The length of the words are not the same");
            System.out.print("End word: ");
            end = scanner.nextLine();
        }
        System.out.print("Enter the algorithm (UCS/Astar/GBFS): ");
        String algo = scanner.nextLine();
        if (algo.toLowerCase().equals("UCS")){
            a.UCS(start.toLowerCase(), end.toLowerCase(), words);
        } else if (algo.toLowerCase().equals("Astar")){
            a.Astar(start.toLowerCase(), end.toLowerCase(), words);
        } else if (algo.toLowerCase().equals("GBFS")){
            a.GreedyBestFirstSearch(start.toLowerCase(), end.toLowerCase(), words);
        } else {
            System.out.println("Invalid algorithm");
            algo = scanner.nextLine();
        }
        a.printSolution();
        scanner.close();
    }
}