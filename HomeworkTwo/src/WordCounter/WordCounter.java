package WordCounter;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordCounter {
    
    private static Scanner scan = new Scanner(System.in);
    private static final String Common_Words_File_Path = "C:\\Users\\Asus\\Documents\\NetBeansProjects\\HomeworkTwo\\src\\commonwords.txt";
    private static final String Tale_Of_Two_Cities = "C:\\Users\\Asus\\Documents\\NetBeansProjects\\HomeworkTwo\\src\\totc.txt";
    private static final String The_Odyssey = "C:\\Users\\Asus\\Documents\\NetBeansProjects\\HomeworkTwo\\src\\dyssy10.txt";
    private static final String The_Adventures_Of_Tom_Sawyer = "C:\\Users\\Asus\\Documents\\NetBeansProjects\\HomeworkTwo\\src\\tomsawyer.txt";
    
    public static void main(String[] args) {
        
        HashSet<String> commonWords = getCommonWords();
        String bookPath = determineBook();
        HashMap<String,Integer> counter = countWords(bookPath,commonWords);
        printResult(counter);
    }

    private static HashSet<String> getCommonWords() {
        HashSet<String> commonWords = new HashSet<>();
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(Common_Words_File_Path));
            String word;
            while((word = lineNumberReader.readLine()) != null)
            {
                commonWords.add(word);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordCounter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commonWords;
    }

    private static String determineBook() {
        System.out.println("""
                           *******************************************************
                           *              Please Select A Bokk                   * 
                           *                                                     *
                           *                                                     *
                           *   1-) "Tale of Two Cities" by Charles Dickens       *
                           *                                                     *
                           *   2-) "The Odyssey" by Homer                        *
                           *                                                     *
                           *   3-) "The Adventures of Tom Sawyer" by Mark Twain  *
                           *                                                     *
                           *                                                     *
                           *******************************************************
                           """);
        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();
        
        return switch (choice) {
            case 1 -> Tale_Of_Two_Cities;
            case 2 -> The_Odyssey;
            case 3 -> The_Adventures_Of_Tom_Sawyer;
            default -> Tale_Of_Two_Cities;
        };
    }

    private static HashMap<String,Integer> countWords(String bookPath, HashSet<String> commonWords) {
        HashMap<String,Integer> counter = new HashMap<>();
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(bookPath));
            String line;
            while((line = lineNumberReader.readLine()) != null)
            {
                line = line.toLowerCase();
                String []words = line.split("[^a-zA-Z’]");
                
                for (String word : words) {
                    if(commonWords.contains(word) || word.length() < 2)
                    {
                        continue;
                    }
                    if(counter.containsKey(word))
                        counter.replace(word, counter.get(word) + 1);
                    else
                        counter.put(word, 1);
                }
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordCounter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordCounter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return counter;
    }

    private static void printResult(HashMap<String, Integer> counter) {
        System.out.print("Enter the threshold value: ");
        int thresholdValue = scan.nextInt();
        
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value > thresholdValue)
                System.out.println("Key: " + key + "\tValue: " + value);
        }
    }
    
}