/**
 * 
 */
package anagrams;

/**
 * A template for the creation of a Result object that represents user inputs
 * that have been processed. The object contains the User's username, the two
 * words they have used in the anagram checker program and whether or not the
 * words are anagrams of each other.
 * 
 * @author Ciarán Casey
 *
 */
public class Result {

	/**
	 * A user's chosen username.
	 */
	private String username;

	/**
	 * The first word a user would like checked.
	 */
	private String word1;

	/**
	 * The second word a user would like checked.
	 */
	private String word2;

	/**
	 * The result of the anagram checker for the two provided words.
	 */
	private boolean areAnagrams;

	/**
	 * Default Constructor.
	 */
	public Result() {

	}

	/**
	 * Constructor with instance variables as arguments.
	 * 
	 * @param username    username
	 * @param word1       first word
	 * @param word2       second word
	 * @param areAnagrams result of anagram checker
	 */
	public Result(String username, String word1, String word2, boolean areAnagrams) {
		this.setUsername(username);
		this.setWord1(word1);
		this.setWord2(word2);
		this.areAnagrams = areAnagrams;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username. Include validation to ensure username length is between 1
	 * and 15 characters in length.
	 * 
	 * @param username the username to set
	 */
	public void setUsername(String username) throws IllegalArgumentException {

		if (username.length() > 0 && username.length() <= 15) {
			this.username = username;
		} else {
			throw new IllegalArgumentException("Inavlid Input - Please Try Again");
		}
	}

	/**
	 * Gets the 1st word.
	 * 
	 * @return the 1st word
	 */
	public String getWord1() {
		return word1;
	}

	/**
	 * Sets the 1st word. Contains business rules that do not allow for spaces,
	 * numbers or special characters to be used in the word.
	 * 
	 * @param word1 the word to set
	 */
	public void setWord1(String word1) throws IllegalArgumentException {

		if ((!word1.contains(" ")) && (!containsDigit(word1)) && (word1.matches("[a-zA-Z]+"))) {
			this.word1 = word1;
		} else {
			throw new IllegalArgumentException("Inavlid Input - Please Try Again");
		}
	}

	/**
	 * Gets the 2nd word.
	 * 
	 * @return the 2nd word
	 */
	public String getWord2() {
		return word2;
	}

	/**
	 * Sets the 2nd word. Contains business rules that do not allow for spaces,
	 * numbers or special charactera to be used in the word.
	 * 
	 * @param word2 the word to set
	 */
	public void setWord2(String word2) throws IllegalArgumentException {

		if ((!word2.contains(" ")) && (!containsDigit(word2)) && (word2.matches("[a-zA-Z]+"))) {
			this.word2 = word2;
		} else {
			throw new IllegalArgumentException("Inavlid Input - Please Try Again");
		}
	}

	/**
	 * Gets the isAnagram boolean value.
	 * 
	 * @return the isAnagram
	 */
	public boolean isAnagram() {
		return areAnagrams;
	}

	/**
	 * Sets the isAnagram boolean value.
	 * 
	 * @param isAnagram the isAnagram to set
	 */
	public void setAnagram(boolean isAnagram) {
		this.areAnagrams = isAnagram;
	}

	/**
	 * Checks whether each character in an input String is a number or not.
	 * 
	 * @param word input word
	 * @return true if the input word contains a number. False if the input word
	 *         does not contain a number
	 */
	public boolean containsDigit(String word) {

		for (char character : word.toCharArray()) {
			if (Character.isDigit(character)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Returns as a String the instance vars and their assigned values.
	 */
	@Override
	public String toString() {
		return "Result [username=" + username + ", word1=" + word1 + ", word2=" + word2 + ", areAnagrams=" + areAnagrams
				+ "]";
	}

}
