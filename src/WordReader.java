import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordReader {
    public static Set<String> readWordsFromFile(String filePath) throws IOException {
        Set<String> words = new HashSet<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim().toLowerCase()); 
            }
        }
        
        return words;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the text file: ");
        String filePath = scanner.nextLine();
        scanner.close();
        
        try {
            Set<String> words = WordReader.readWordsFromFile(filePath);
            System.out.println("Words read from file:");
            int i = 0;
            for (String word : words) {
                System.out.println(word);
                i++;
                if (i == 100){
                    System.out.println(words.size());
                    break;
                }
            }
            String a = "test";
            if (words.contains(a.toLowerCase())){
                System.out.println(a + " is in the list");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
