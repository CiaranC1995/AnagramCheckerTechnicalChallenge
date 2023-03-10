/**
 * 
 */
package anagrams;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit Test Case for Result.java Class. Full test coverage for all methods
 * contained in this class including valid and invalid possibilities.
 * 
 * @author CiaránC
 *
 */
class ResultTest {

	// Test Data
	Result resultTest;

	String usernameInvalidLower, usernameValidLower, usernameValidMid, usernameValidUpper, usernameInvalidUpper;
	String word1InvalidNumber, word1InvalidSpecial, word1InvalidSpace, word1Valid;
	String word2InvalidNumber, word2InvalidSpecial, word2InvalidSpace, word2Valid;
	boolean areAnagramsTrue, areAnagramsFalse;

	// test data for containsDigit() method
	String wordWithAdigit, wordNoDigits, wordEmpty, wordOnlyDigits, wordDigitAndSpecial, wordOnlySpecial;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		resultTest = new Result();

		usernameInvalidLower = "";
		usernameValidLower = "V";
		usernameValidMid = "ValidUs";
		usernameValidUpper = "ValidUsernameVa";
		usernameInvalidUpper = "InvalidUsernameI";

		word1InvalidNumber = "invalidWord123";
		word1InvalidSpecial = "invalidWord!$&";
		word1InvalidSpace = "Invalid Word One";
		word1Valid = "ValidWordOne";

		word2InvalidNumber = "invalidWord456";
		word2InvalidSpecial = "invalidWord&*(";
		word2InvalidSpace = "Invalid Word Two";
		word2Valid = "ValidWordTwo";

		areAnagramsTrue = true;
		areAnagramsFalse = false;

		wordWithAdigit = "word1";
		wordNoDigits = "wordNoDigits";
		wordEmpty = "";
		wordOnlyDigits = "12345";
		wordDigitAndSpecial = "1£$%";
		wordOnlySpecial = "£$%^";
	}

	/**
	 * Test method for {@link anagrams.Result#Result()}.
	 */
	@Test
	void testResultDefaultConstructor() {

		assertNotNull(resultTest);

	}

	/**
	 * Test method for
	 * {@link anagrams.Result#Result(java.lang.String, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	void testResultConstructorWithArgsValid() {

		Result result = new Result(usernameValidMid, word1Valid, word2Valid, areAnagramsTrue);

		assertEquals(usernameValidMid, result.getUsername());
		assertEquals(word1Valid, result.getWord1());
		assertEquals(word2Valid, result.getWord2());
		assertEquals(areAnagramsTrue, result.isAnagram());

		Result result1 = new Result(usernameValidMid, word1Valid, word2Valid, areAnagramsFalse);

		assertEquals(areAnagramsFalse, result1.isAnagram());
	}

	/**
	 * Test method for
	 * {@link anagrams.Result#Result(java.lang.String, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	void testResultConstructorWithArgsInValid() {

		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Result result = new Result(usernameInvalidLower, word1Valid, word2Valid, areAnagramsTrue);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Result result = new Result(usernameValidMid, word1InvalidNumber, word2Valid, areAnagramsTrue);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Result result = new Result(usernameValidMid, word1Valid, word2InvalidNumber, areAnagramsTrue);
		});
	}

	/**
	 * Test method for {@link anagrams.Result#getUsername()}.
	 */
	@Test
	void testGetSetUsernameValid() {

		resultTest.setUsername(usernameValidLower);
		assertEquals(usernameValidLower, resultTest.getUsername());

		resultTest.setUsername(usernameValidMid);
		assertEquals(usernameValidMid, resultTest.getUsername());

		resultTest.setUsername(usernameValidUpper);
		assertEquals(usernameValidUpper, resultTest.getUsername());
	}

	/**
	 * Test method for {@link anagrams.Result#setUsername(java.lang.String)}.
	 */
	@Test
	void testSetUsernameInvalid() {

		@SuppressWarnings("unused")
		IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
			resultTest.setUsername(usernameInvalidLower);
		});

		illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
			resultTest.setUsername(usernameInvalidUpper);
		});
	}

	/**
	 * Test method for {@link anagrams.Result#getWord1()}.
	 */
	@Test
	void testGetSetWord1Valid() {

		resultTest.setWord1(word1Valid);
		assertEquals(word1Valid, resultTest.getWord1());
	}

	/**
	 * Test method for {@link anagrams.Result#setWord1(java.lang.String)}.
	 */
	@Test
	void testSetWord1Invalid() {

		assertThrows(IllegalArgumentException.class, () -> {
			resultTest.setWord1(word1InvalidNumber);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			resultTest.setWord1(word1InvalidSpace);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			resultTest.setWord1(word1InvalidSpecial);
		});
	}

	/**
	 * Test method for {@link anagrams.Result#getWord2()}.
	 */
	@Test
	void testGetSetWord2Valid() {

		resultTest.setWord2(word2Valid);
		assertEquals(word2Valid, resultTest.getWord2());
	}

	/**
	 * Test method for {@link anagrams.Result#setWord2(java.lang.String)}.
	 */
	@Test
	void testSetWord2Invalid() {

		assertThrows(IllegalArgumentException.class, () -> {
			resultTest.setWord2(word2InvalidNumber);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			resultTest.setWord2(word2InvalidSpace);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			resultTest.setWord2(word2InvalidSpecial);
		});
	}

	/**
	 * Test method for {@link anagrams.Result#isAnagram()}.
	 */
	@Test
	void testIsSetAnagram() {

		resultTest.setAnagram(areAnagramsFalse);
		assertEquals(areAnagramsFalse, resultTest.isAnagram());

		resultTest.setAnagram(areAnagramsTrue);
		assertEquals(areAnagramsTrue, resultTest.isAnagram());
	}

	/**
	 * Test method for {@link anagrams.Result#containsDigit(java.lang.String)}.
	 */
	@Test
	void testContainsDigit() {

		assertTrue(resultTest.containsDigit(wordWithAdigit));
		assertFalse(resultTest.containsDigit(wordEmpty));
		assertTrue(resultTest.containsDigit(wordOnlyDigits));
		assertTrue(resultTest.containsDigit(wordDigitAndSpecial));
		assertFalse(resultTest.containsDigit(wordOnlySpecial));
	}

	/**
	 * Test method for {@link anagrams.Result#toString()}.
	 */
	@Test
	void testToString() {

		Result result = new Result(usernameValidLower, word1Valid, word2Valid, areAnagramsTrue);

		String expected = "Result [username=V, word1=ValidWordOne, word2=ValidWordTwo, areAnagrams=true]";
		String actual = result.toString();

		assertEquals(expected, actual);
	}

}
