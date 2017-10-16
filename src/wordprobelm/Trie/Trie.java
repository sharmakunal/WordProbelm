
package wordprobelm.Trie;

import java.util.*;

public class Trie {

    Node head;
    StringBuffer buffer = new StringBuffer();
    public TreeMap<String, StringBuffer> m = new TreeMap<>();

    public Trie() {
        head = new Node('#');
    }

    // Function to insert the word in a Trie
    public void insert(String s) {
        Node current = head;
        if (s.length() == 0) // For string which is empty
        {
            current.endmarker = true;
        }
        //Loop over the length of string
        for (int i = 0; i < s.length(); i++) {
            Node child = current.subNode(s.charAt(i));
            // if character already present just increment its prefix 
            if (child != null) {
                current = child;
                current.prefix++;
            } else {   // if character not present add a new character node 
                current.childNode.add(new Node(s.charAt(i)));
                buffer.append(s.charAt(i));
                current = current.subNode(s.charAt(i));
            }
            // Set marker to indicate end of the word
            if (i == s.length() - 1) {
                current.endmarker = true;
            }
        }
        if (!m.containsKey(s) && !s.equals(buffer.toString())) {
            m.put(s, buffer);
        }

        buffer = new StringBuffer();
    }

    public boolean search(String s) {
        Node current = head;
        while (current != null) {
            //Loop over the length of string
            for (int i = 0; i < s.length(); i++) {
                if (current.subNode(s.charAt(i)) == null) {
                    return false;
                } else {
                    current = current.subNode(s.charAt(i));
                }
            }

            // It means that string is in the Trie but is it really a word?
            if (current.endmarker == true) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
