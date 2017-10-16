package wordprobelm.Trie;

import java.util.Collection;
import java.util.LinkedList;

public class Node {
	char character;  // the character contained in that node
	boolean endmarker;  // which marks the end of the word
	int prefix;  // Number of words having prefix as this character 
	Collection<Node> childNode;  //The child node of given node

	// Constructor which creates a node
	public Node(char c) {
		childNode = new LinkedList<Node>();
		endmarker = false;
		character = c;
		prefix = 1;
	}

	// subNode function returns the child node of a given node having character c
	public Node subNode(char ch) {
		if (childNode != null) {
			for (Node child : childNode) {
				if (child.character == ch) {
					return child;
				}
			}
		}
		return null;
	}

}