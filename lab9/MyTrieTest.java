import static org.junit.Assert.fail;

import org.junit.Test;

public class MyTrieTest {

	// Tests add, contains, isEmpty, and size methods
	@Test
	public void bigTest() {
    MyTrie triey = new MyTrie();
    String[] wordList = { "test", "tea", "teeth", "wow", "UCBerkeleyMemesforEdgyTeens" };

    if (!triey.isEmpty()) {
    	fail("isEmpty not working");
    }

    if (triey.containsPrefix("a")) {
    	fail("Contains prefix not working!");
    }

    for (String word : wordList) {
    	triey.add(word);
    	System.out.println(triey.size());
    }

    for (String word : wordList) {
    	if (!triey.contains(word)) {
   	 fail("Something broke!");
    	}
    }

    if (triey.add("test")) {
    	fail("Add function not returning correct value for duplicate words");
    }

    if (triey.contains("OberlinMemes")) {
    	fail("Contains not checking for strings properly");
    }

    if (triey.isEmpty()) {
    	fail("isEmpty not working");
    }

    if (!triey.containsPrefix("te")) {
    	fail("Contains prefix not working!");
    }
	}

	@Test
	public void toStringTest() {
    MyTrie triey = new MyTrie();
    String[] wordList = { "test", "tea", "teeth", "wow", "UCBerkeleyMemesforEdgyTeens" };

    for (String word : wordList) {
    	triey.add(word);
    }

    System.out.println(triey.toString());
	}

}

