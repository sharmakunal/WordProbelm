package wordprobelm;

import java.io.*;
import java.util.*;

public class LongestWordByIteration implements Comparator<String> {

    //compare function to be used for sorting the array according to word length
    public int compare(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return 1;
        } else if (s1.length() > s2.length()) {
            return -1;
        } else {
            return 0;
        }
    }

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

            //sort the array according to length of words in descending order
            Arrays.sort(sortedWords, new LongestWordByIteration());

            HashSet<String> dic = new HashSet<String>();

            for (String word : sortedWords) {
                dic.add(word);
            }
            for (String words : sortedWords) {
                if (isMadeOfWords(words, dic)) {
                    System.out.println(words);
                }
            }

        } catch (FileNotFoundException fex) {
            System.out.println("File not found");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isMadeOfWords(String word, HashSet<String> dict) {
        if (word.isEmpty() || word == null) {
            return false;
        }
        if (word.length() == 1) {
            if (dict.contains(word)) {
                return true;
            } else {
                return false;
            }
        }
        for (Map.Entry<String, String> map : generatePairs(word).entrySet()) {
            if (dict.contains(map.getKey())) {
                if (dict.contains(map.getValue())) {
                    return true;
                } else {
                    return isMadeOfWords(map.getValue(), dict);
                }
            }
        }
        return false;
    }

    private static Map<String, String> generatePairs(String word) {
        Map<String, String> output = new HashMap<>();
        for (int i = 1; i < word.length(); i++) {
            output.put(word.substring(0, i), word.substring(i));
        }
        return output;
    }
}
