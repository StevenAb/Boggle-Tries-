import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

public class MyTrie extends AbstractSet<String> {

	boolean isWord; // whether this trie node is the end of a word
	int size; // the number of words represented by this trie
	MyTrie[] children; // the children tries of this node
	static final int ARRAY_LENGTH = 26;

	public MyTrie() {
    isWord = false;
    size = 0;
    children = new MyTrie[ARRAY_LENGTH];
    for (int i = 0; i < ARRAY_LENGTH; i++) {
    	children[i] = null;
    }
	}

	private int lookup(char c) {
    String ch = "" + c;
    ch = ch.toLowerCase();
    int num = -1;

    switch (ch) {
    case "a":
    	num = 0;
    	break;
    case "b":
    	num = 1;
    	break;
    case "c":
    	num = 2;
    	break;
    case "d":
    	num = 3;
    	break;
    case "e":
    	num = 4;
    	break;
    case "f":
    	num = 5;
    	break;
    case "g":
    	num = 6;
    	break;
    case "h":
    	num = 7;
    	break;
    case "i":
    	num = 8;
    	break;
    case "j":
    	num = 9;
    	break;
    case "k":
    	num = 10;
    	break;
    case "l":
    	num = 11;
    	break;
    case "m":
    	num = 12;
    	break;
    case "n":
    	num = 13;
    	break;
    case "o":
    	num = 14;
    	break;
    case "p":
    	num = 15;
    	break;
    case "q":
    	num = 16;
    	break;
    case "r":
    	num = 17;
    	break;
    case "s":
    	num = 18;
    	break;
    case "t":
    	num = 19;
    	break;
    case "u":
    	num = 20;
    	break;
    case "v":
    	num = 21;
    	break;
    case "w":
    	num = 22;
    	break;
    case "x":
    	num = 23;
    	break;
    case "y":
    	num = 24;
    	break;
    case "z":
    	num = 25;
    	break;

    default:
    	break;
    }

    return num;
	}

	/**
 	* Adds a string to the trie
 	*
 	* @param string
 	*        	to be added to the trie
 	* @return true if a change is made to the trie, false if no change is made to
 	*     	the trie
 	*/
	public boolean add(String string) throws IndexOutOfBoundsException {
    if (!Character.isLetter(string.charAt(0))) {
    	throw new IndexOutOfBoundsException("String[0] is not a character!");
    }

    if (contains(string)) {
    	return false;
    }

    size++;
    int addy = lookup(string.charAt(0));
    if (children[addy] == null) {
    	children[addy] = new MyTrie();
    }

    if (string.length() != 1) {
    	return children[addy].add(string.substring(1, string.length()));
    } else {
    	children[addy].isWord = true;
    	return true;
    }
	}

	/**
 	* Checks if a trie contains a string, string does not necessarily have to be
 	* saved
 	*
 	* @param prefix
 	*        	to search for
 	* @return true if the trie contains the string, false if the trie does not
 	*     	contain the string
 	*/
	public boolean containsPrefix(String prefix) {
		if (prefix == "") {
    			return this.containsEmptyString();
		}
		
		int addy = lookup(prefix.charAt(0));
	
	    if (children[addy] != null) {
	    		if (prefix.length() == 1) {
	    			return true;
	    		} else {
	    			return children[addy].containsPrefix(prefix.substring(1, prefix.length()));
	    		}
	    } else {
	    		return false;
	    }
	}

	/**
 	* Checks if a string is saved as a word in the trie
 	*
 	* @param string
 	*        	to search for
 	* @return true if the string is saved in the trie, false if the string is not
 	*     	contained in the trie
 	*/
	public boolean contains(String string) {
    if (string == "") {
    		return this.containsEmptyString();
    }
    
	int addy = lookup(string.charAt(0));

    if (children[addy] != null) {
    	if (string.length() == 1) {
   	 if (children[addy].isWord == true) {
   	 	return true;
   	 } else {
   	 	return false;
   	 }
    	} else {
   	 return children[addy].contains(string.substring(1, string.length()));
    	}
    } else {
    	return false;
    }
	}

	/**
 	* Returns the number of strings stored in the trie
 	*
 	* @return size data member
 	*/
	public int size() {
    return size;
	}

	/**
 	* @return true if no strings are stored in the trie, false if strings are
 	*     	stored in the trie
 	*/
	public boolean isEmpty() {
    if (size == 0) {
    	return true;
    } else {
    	return false;
    }
	}

	/**
 	*
 	* @return isWord value of trie's root
 	*/
	public boolean containsEmptyString() {
		return this.isWord;
	}

	private String indexToChar(int index) {
    switch (index) {
    case 0:
    	return "a";
    case 1:
    	return "b";
    case 2:
    	return "c";
    case 3:
    	return "d";
    case 4:
    	return "e";
    case 5:
    	return "f";
    case 6:
    	return "g";
    case 7:
    	return "h";
    case 8:
    	return "i";
    case 9:
    	return "j";
    case 10:
    	return "k";
    case 11:
    	return "l";
    case 12:
    	return "m";
    case 13:
    	return "n";
    case 14:
    	return "o";
    case 15:
    	return "p";
    case 16:
    	return "q";
    case 17:
    	return "r";
    case 18:
    	return "s";
    case 19:
    	return "t";
    case 20:
    	return "u";
    case 21:
    	return "v";
    case 22:
    	return "w";
    case 23:
    	return "x";
    case 24:
    	return "y";
    case 25:
    	return "z";
    default:
    	return "";
    }
	}

	private String stringCollector(String pre) {
	    String fin = "";
	    ArrayList<String> listy = new ArrayList<String>();
	    if (pre.length() == 1 && this.isWord == true) {
	    		listy.add(pre);
	    }
	
	    for (int i = 0; i < children.length; i++) {
		    	if (children[i] != null) {
			   	 if (children[i].isWord == true) {
			   	 	listy.add(pre + indexToChar(i));
			   	 }
			   	 fin += children[i].stringCollector(pre + indexToChar(i));
		    	}
	    }
	
	    for (String string : listy) {
	    		fin += string + " ";
	    }
	
	    return fin;
	}

	private ArrayList<String> toList() {
	    ArrayList<String> listy = new ArrayList<String>();
	    String smallString = "";
	
	    for (int i = 0; i < children.length; i++) {
		    	if (children[i] != null) {
				 smallString = children[i].stringCollector(indexToChar(i));
				 String[] wordList = smallString.split("\\s+");
				 for (String string : wordList) {
				 	listy.add(string);
				 }
		    	}
	    }
	
	    return listy;
	}

	public String toString() {
    String fin = "";
    ArrayList<String> listy = toList();

    for (String string : listy) {
    	fin += string + " ";
    }

    return fin;
	}

	public Iterator<String> iterator() {
    return toList().iterator();
	}

}