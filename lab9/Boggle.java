import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Boggle {
	
	private MyTrie     lex;	// The dictionary, stored in a Trie
    private Square[][] board;	// The 4x4 board
    private MyTrie     foundWords;  // The dictionary words on the current board
    private MyTrie     guesses;	// The valid words made so far by our one player
    private String[]   dice;	// An array of dice -- explained later!
    private ArrayList<Square> path;
    
    public Boggle(String lexy) {
    		lex = new MyTrie();
    		guesses = new MyTrie();
    		Scanner scanny = null;
    		try {
    			scanny = new Scanner(new File(lexy));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    		
		while (scanny.hasNextLine()) {
    			lex.add(scanny.nextLine());
    		}
		
		this.fillDice();
    }
    
    private void fillDice() {
    		Scanner scanny = null;
    		int county = 0;
    		dice = new String[16];
    		
	    try {
			scanny = new Scanner(new File("dice.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    while (scanny.hasNextLine()) {
	    		dice[county] = scanny.nextLine();
	    		county++;
	    }
    }
    
    private int rando(int size) {
    		return (int) (Math.random() * (size + 1));
    }
    
    private void fillBoardFromDice() {
    		board = new Square[4][4];
    		ArrayList<String> listy = new ArrayList<String>();
    		for (String string : dice) {
			listy.add(string);
		}
    		String current = "";
    		String stringy = "";
    		
    		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				current = listy.remove(rando(listy.size()-1));
				stringy = "" + current.charAt(rando(5));
				if (stringy.equals("Q")) {
					stringy = "QU";
				}
				board[i][j] = new Square(i, j, stringy);
			}
		}
    }
    
    public Square[][] getBoard() {
    		return this.board;
    }
    
    public void newGame() {
    		fillBoardFromDice();
    		fillFoundWords();
    }

	public boolean contains(String word) {
		return foundWords.contains(word);
	}

	public boolean addGuess(String guess) {
		if (foundWords.contains(guess) && !guesses.contains(guess)) {
			guesses.add(guess);
			return true;
		} else {
			return false;
		}
	}

	public int numGuesses() {
		return guesses.size();
	}
    
	public String toString() {
		String boardy = "";
		
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				boardy += board[i][j].toString() + " ";
			}
			boardy += "\n";
		}
		
		return boardy;
	}
	
	private ArrayList<Square> getNeighbors(Square sq) {
		ArrayList<Square> neighbors = new ArrayList<Square>();
		int x = sq.getX();
		int y = sq.getY();
		
		if (y != 0) {
			neighbors.add(board[x][y-1]);
		}
		if (y != 3) {
			neighbors.add(board[x][y+1]);
		}
		if (x != 0) {
			neighbors.add(board[x-1][y]);
		}
		if (x != 3) {
			neighbors.add(board[x+1][y]);
		}
		if (y != 0 && x != 0) {
			neighbors.add(board[x-1][y-1]);
		}
		if (y != 3 && x != 0) {
			neighbors.add(board[x-1][y+1]);
		}
		if (y != 0 && x != 3) {
			neighbors.add(board[x+1][y-1]);
		}
		if (y != 3 && x != 3) {
			neighbors.add(board[x+1][y+1]);
		}
		
		return neighbors;
	}
	
	private void Search(Square sq, String prefix) {
    		ArrayList<Square> neighbors;
    		
		if (lex.contains(prefix)) {
			foundWords.add(prefix);
		}
    		if (lex.containsPrefix(prefix)) {
			neighbors = getNeighbors(sq);
			for (Square square : neighbors) {
				if (!square.isMarked()) {
					square.mark();
					Search(square, prefix + square.toString());
					square.unmark();
				}
			}
		}
    }
    
    private void fillFoundWords() {
    		foundWords = new MyTrie();
    		for (int i = 0; i < board.length; i++) {
    			for (int j = 0; j < board[0].length; j++) {
				Search(board[i][j],board[i][j].toString());
			}
		}
    }
    
    public ArrayList<Square> squaresForWord(String word) {
		ArrayList<Square> p;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				p = squaresForWord(board[i][j], word);
				if (p.size() == word.length()) {
					return p;
				}
			}
		}
		p = new ArrayList<Square>();
		return p;
	}
    
    private Boolean pathFinder(Square sq, String pre, String word) {
    		if (pre.length() > word.length()) {
    			return false;
    		}
    		if (pre.equals(word)) {
    			path.add(sq);
    			return true;
    		} else if (pre.length() == word.length()) {
    			return false;
    		}
    		ArrayList<Square> neighbors = getNeighbors(sq);
    		for (Square square : neighbors) {
			if (square.toString().equals(word.charAt(pre.length()) + "")) {
				if (pathFinder(square, pre + square.toString(), word)) {
					path.add(0, sq);
					return true;
				}
			}
		}
    		return false;
    }
    
    private ArrayList<Square> squaresForWord(Square sq, String w) {
    		path = new ArrayList<Square>();
    		if (lex.contains(w) && (w.charAt(0) + "").equals(sq.toString())) {
			pathFinder(sq, sq.toString(), w);
	    	}
    		
    		return path;
    }

    public static void main(String args[]) {
    		if (args.length != 1) {
    			System.out.println("Usage: java Boggle [lexicon file]");
    			System.exit(0);
    		}
    		
    		Boggle boggle = new Boggle(args[0]);
    		BoggleFrame bFrame = new BoggleFrame(boggle);
    		bFrame.pack();
    		bFrame.setLocationRelativeTo(null);
    		bFrame.setVisible(true);
    }

}
