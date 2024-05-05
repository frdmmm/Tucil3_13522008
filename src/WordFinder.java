import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordFinder {
    public static Set<String> findWordsWithOneCharDifference(String inputWord, Set<String> wordSet) {
        Set<String> result = new HashSet<>();
        
        for (String word : wordSet) {
            if (isOneCharDifference(inputWord, word)) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    private static boolean isOneCharDifference(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        
        return diffCount == 1; 
    }
    public static int getDistance(String word1, String word2){
        int distance = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }
    public static int getAstarHeuristic(String word, String start, String end){
        return getDistance(word, end) + getDistance(word, start);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the text file: ");
        String filePath = scanner.nextLine();
        scanner.close();
        
        try {
            Set<String> words = WordReader.readWordsFromFile(filePath);
            System.out.println("Words read from file:");
            Set<String> swords = WordFinder.findWordsWithOneCharDifference("rat".toLowerCase(), words);
            int i = 0;
            for (String word : swords) {
                System.out.println(word);
                i++;
                if (i == 100){
                    System.out.println(words.size());
                    break;
                }
            }
            System.out.println("Panjang nya : " +swords.size());
            String a = "kata";
            if (words.contains(a.toLowerCase())){
                System.out.println(a + " is in the list");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

