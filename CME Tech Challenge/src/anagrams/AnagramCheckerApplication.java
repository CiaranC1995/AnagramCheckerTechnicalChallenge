/**
 * 
 */
package anagrams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Contains the main method which is the starting point of the Anagram Checker
 * Application. Also includes utility methods that allow the program to
 * function.
 * 
 * @author Ciarán Casey
 *
 */
public class AnagramCheckerApplication {

	/**
	 * Global TreeMap collection to store previous user results from the external
	 * file in a cache. Key is a String representing the username and value is an
	 * object representing the words used in the program, the result and the
	 * username.
	 */
	public static Map<String, Result> previousResults = new TreeMap<String, Result>();

	/**
	 * Name of file that each result is written to.
	 */
	public static final String EXTERNAL_FILE_PATH = "ResultsHistory.txt";

	/**
	 * Beginning of the Application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * At start of application, read in from file and populate map with previous
		 * processed results.
		 */
		readInPreviousResultsFromFile();

		/**
		 * Display the menu to the console.
		 */
		displayMenu();

	}

	/**
	 * Displays welcome message to the user at the beginning of the program.
	 */
	public static void displayWelcomeMessage() {
		System.out.println("Welcome To The Anagram Checker!");
		System.out.println("\nPlease Enter Your Username Followed By Two Words That You Would Like Checked ... \n");
	}

	/**
	 * Captures and returns as a String[] the user input from the console.
	 * Validation rules in place to ensure that invalid input from the user is
	 * caught.
	 * 
	 * @return String[] containing user input
	 */
	public static String[] captureUserInput() {

		/**
		 * Allowable Minimum Username Length.
		 */
		final int MINIMUM_VALID_USERNAME_LENGTH = 1;

		/**
		 * Allowable Minimum Username Length.
		 */
		final int MAXIMUM_VALID_USERNAME_LENGTH = 15;

		// Variables to contain user input.
		String[] userInput = new String[3];
		String username, word1, word2;

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.print("Username : ");
		username = scanner.nextLine();

		while ((username.length() < MINIMUM_VALID_USERNAME_LENGTH)
				|| (username.length() > MAXIMUM_VALID_USERNAME_LENGTH)) { // Check if username length is valid
			System.out.print("Invalid Length. Please input a username between 1 and 15 characters in length: ");
			username = scanner.nextLine();
		}
		userInput[0] = username;

		System.out.print("First Word : ");
		word1 = scanner.nextLine();

		while (!word1.matches("[a-zA-Z]+")) { // Check if the input contains only letters
			System.out.print("Invalid input. Please enter a word with only letters: ");
			word1 = scanner.nextLine();
		}
		userInput[1] = word1;

		System.out.print("Second Word : ");
		word2 = scanner.nextLine();

		while (!word2.matches("[a-zA-Z]+")) { // Check if the input contains only letters
			System.out.print("Invalid input. Please enter a word with only letters: ");
			word2 = scanner.nextLine();
		}
		userInput[2] = word2;

		return userInput;
	}

	/**
	 * Processes the user input from the console. If the words have been processed
	 * before they will not be added to the cache or written to the file but the
	 * result will be displayed. If the words are new they will be stored in the
	 * external file and the cache.
	 * 
	 * @param userInput input from the user containing username and the words to be
	 *                  checked
	 */
	public static void processUserInput(String[] userInput) {

		if (!haveWordsBeenProcessedBefore(userInput)) {

			Result newResult = new Result();
			boolean newIsAnagram;

			newResult.setUsername(userInput[0]);
			newResult.setWord1(userInput[1]);
			newResult.setWord2(userInput[2]);

			newIsAnagram = areAnagrams(newResult.getWord1(), newResult.getWord2());

			newResult.setAnagram(newIsAnagram);

			if (newResult.isAnagram()) {
				System.out.println(
						"\n'" + newResult.getWord1() + "' and '" + newResult.getWord2() + "' are anagrams!!\n");
			} else {
				System.out.println(
						"\n'" + newResult.getWord1() + "' and '" + newResult.getWord2() + "' are not anagrams!!\n");

			}

			// Storing the new result in the previousResults TreeMap
			previousResults.put(newResult.getUsername(), newResult);

			writeNewResultToExternalFile(userInput[0], userInput[1], userInput[2], newIsAnagram);

		}

	}

	/**
	 * Writes to an external file the results of a new set of words being processed
	 * by the anagram checker. Also writes to file the date and time of the result
	 * being processed.
	 * 
	 * @param username      the username
	 * @param word1         the first word used in th program
	 * @param word2         the second word used in th program
	 * @param anagramResult the result of the anagram checker
	 */
	public static void writeNewResultToExternalFile(String username, String word1, String word2,
			boolean anagramResult) {

		/**
		 * Creation of DateTimeFormatter and LocalDateTime objects to allow for the date
		 * and time of results being processed to be written to the file.
		 */
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Writing Results To An External File

		File file = new File(EXTERNAL_FILE_PATH);

		try {
			/**
			 * Creation of File Writers to allow the appending of results to the end of the
			 * file
			 */
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

			/**
			 * String Builder to format the String that is written to the file. Also written
			 * to the file is the date and time that the result was processed.
			 */
			StringBuilder sb = new StringBuilder();
			sb.append(username + ",");
			sb.append(word1 + ",");
			sb.append(word2 + ",");
			sb.append(anagramResult + ",");
			sb.append(dateTimeFormatter.format(currentDateTime) + "\n");

			bufferedWriter.write(sb.toString());

			bufferedWriter.close();

		} catch (IOException ioException) {
			System.err.println("Unable To Write To File");
			ioException.printStackTrace();
		}
	}

	/**
	 * Takes two String inputs, checks if they are anagrams of each other and
	 * returns the result as a boolean.
	 * 
	 * @param userInput1 first word to be checked
	 * @param userInput2 second word to be checked
	 * @return boolean result of the check
	 */
	public static boolean areAnagrams(String userInput1, String userInput2) {

		// Convert input words to an array of chars
		char[] tokenedUserInput1 = userInput1.toCharArray();
		char[] tokenedUserInput2 = userInput2.toCharArray();

		// Sort the char arrays
		Arrays.sort(tokenedUserInput1);
		Arrays.sort(tokenedUserInput2);

		return Arrays.equals(tokenedUserInput1, tokenedUserInput2);
	}

	/**
	 * Prints to the console the options that a user can choose from and makes
	 * appropriate method calls according to their choice. Includes validation to
	 * ensure invalid input is caught.
	 */
	public static void displayMenu() {

		Scanner scanner = new Scanner(System.in);
		int userOption;

		do {

			System.out.println("Hello, Please Choose An Option : \n");
			System.out.println("1. Check To See If 2 Words Are Anagrams");
			System.out.println("2. Quit Application\n");
			System.out.print("Option : ");

			// Validation to ensure user enters a number
			while (!scanner.hasNextInt()) {
				System.out.print("Invalid input. Please enter a number: ");
				scanner.next();
			}

			userOption = scanner.nextInt();
			System.out.println();

			switch (userOption) {
			case 1:
				displayWelcomeMessage();
				String[] userInput = captureUserInput();
				processUserInput(userInput);
				break;
			case 2:
				System.out.println("Thanks For Using The Application, Goodbye");
				break;
			default:
				System.out.println("Please Choose A Valid Option");
			}

		} while (userOption != 2);

		scanner.close();
	}

	/**
	 * Reads in any previously processed results from the external file and uses
	 * these to populate the cache (Global TreeMap previousResults).
	 */
	public static void readInPreviousResultsFromFile() {

		File file = new File(EXTERNAL_FILE_PATH);
		String lineFromFile;
		String[] tokenedLineFromFile;
		String username, word1, word2;
		boolean isAnagram;

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			lineFromFile = bufferedReader.readLine();

			while (lineFromFile != null) {

				if (!lineFromFile.trim().isEmpty()) {

					tokenedLineFromFile = lineFromFile.split(",");

					Result previousSessionResult = new Result();

					username = tokenedLineFromFile[0];
					word1 = tokenedLineFromFile[1];
					word2 = tokenedLineFromFile[2];
					isAnagram = Boolean.parseBoolean(tokenedLineFromFile[3]);

					previousSessionResult.setUsername(username);
					previousSessionResult.setWord1(word1);
					previousSessionResult.setWord2(word2);
					previousSessionResult.setAnagram(isAnagram);

					previousResults.put(username, previousSessionResult);

				}

				lineFromFile = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	/**
	 * Checks the cache (Global TreeMap previousResults) against the user input to
	 * see if the words have been processed before or not. Informs the user if they
	 * have been processed and returns a boolean true or false depending on the
	 * outcome.
	 * 
	 * @param userInput user input containing the words to check against the cache
	 *                  of previous results
	 * @return boolean true or false depending on the outcome
	 */
	public static boolean haveWordsBeenProcessedBefore(String[] userInput) {

		for (Map.Entry<String, Result> entry : previousResults.entrySet()) {

			Result previousResult = entry.getValue();

			if (((previousResult.getWord1().equals(userInput[1]) && previousResult.getWord2().equals(userInput[2]))
					|| (previousResult.getWord1().equals(userInput[2])
							&& previousResult.getWord2().equals(userInput[1])))
					&& (previousResult.isAnagram())) {

				System.out.println("\n'" + userInput[1] + "' and '" + userInput[2]
						+ "' have been processed before and are anagrams!\n");

				return true;

			} else if (((previousResult.getWord1().equals(userInput[1])
					&& previousResult.getWord2().equals(userInput[2]))
					|| (previousResult.getWord1().equals(userInput[2])
							&& previousResult.getWord2().equals(userInput[1])))
					&& (!previousResult.isAnagram())) {

				System.out.println("\n'" + userInput[1] + "' and '" + userInput[2]
						+ "' have been processed before and are not anagrams!\n");

				return true;
			}
		}
		return false;
	}
}
