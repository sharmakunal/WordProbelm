package wordprobelm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import wordprobelm.Trie.Trie;

public class LongestWordByTrie implements Comparator<Map.Entry<String, StringBuffer>> {

    public int compare(Map.Entry<String, StringBuffer> e1, Map.Entry<String, StringBuffer> e2) {
        if (e1.getKey().length() < e2.getKey().length()) {
            return 1;
        } else if (e1.getKey().length() > e2.getKey().length()) {
            return -1;
        } else {
            return 0;
        }
    }

    private static Trie trie = new Trie();

    public static void main(String[] args) {

        try {

            String[] sortedWords = null;
            String[] longestWords = null;
            List<String> wordArray = new ArrayList<>();

            FileInputStream file = new FileInputStream("C:\\Users\\kunal\\Desktop\\words1.txt");

            Scanner sc = null;
            sc = new Scanner(file, "UTF-8");

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                wordArray.add(line);
            }

            if (file != null) {
                file.close();
            }
            if (sc != null) {
                sc.close();
            }

            // Print the number of words in the file
            System.out.println("Total number of words in file :   " + wordArray.size());

            // Convert array list to an array of string
            sortedWords = (String[]) wordArray.toArray(new String[wordArray.size()]);

            // Populate trie ADT that we created
            for (String word : sortedWords) {
                trie.insert(word);
            }

            Set<Map.Entry<String, StringBuffer>> entries = trie.m.entrySet();
            List<Map.Entry<String, StringBuffer>> listOfEntries = new ArrayList<Map.Entry<String, StringBuffer>>(entries);

            //sort the array according to length of words in descending order
            Collections.sort(listOfEntries, new LongestWordByTrie());
            LinkedHashMap<String, StringBuffer> sortedByValue = new LinkedHashMap<String, StringBuffer>(listOfEntries.size());

            // copying entries from List to Map 
            for (Map.Entry<String, StringBuffer> entry : listOfEntries) {
                sortedByValue.put(entry.getKey(), entry.getValue());
            }
            long startTime = System.nanoTime();

            LongestWordsContainingOtherWords(sortedByValue);

            long endTime = System.nanoTime();
            System.out.println("Took " + (endTime - startTime) + " ns");
        } catch (FileNotFoundException e) {
            System.out.println("Please enter a correct filename!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void LongestWordsContainingOtherWords(LinkedHashMap<String, StringBuffer> listOfEntries) {
        int i=0;
        for (Map.Entry<String, StringBuffer> entry : listOfEntries.entrySet()) {
            String word = new String(entry.getValue());
             
            if (isRequiredWord(word)) {
                i++;
                System.out.println("word : " + entry.getKey());
                if(i==2)
                 break;   
            }
        }

    }

    public static boolean isRequiredWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (trie.search(word.substring(0, i + 1))) {
                if (i + 1 == word.length() || isRequiredWord(word.substring(i + 1, word.length()))) {
                    return true;
                }
            }
        }
        return false;
    }
}