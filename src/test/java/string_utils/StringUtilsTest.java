package string_utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    void testIsPalindrome() {
        assertTrue(StringUtils.isPalindrome("Madam"));
        assertTrue(StringUtils.isPalindrome("Искать такси"));
        assertFalse(StringUtils.isPalindrome("Hello"));
        assertFalse(StringUtils.isPalindrome(null));
    }

    @Test
    void testCountVowels() {
        assertEquals(2, StringUtils.countVowels("Hello"));
        assertEquals(4, StringUtils.countVowels("Искать такси"));
        assertEquals(0, StringUtils.countVowels(""));
        assertEquals(0, StringUtils.countVowels(null));
    }

    @Test
    void testCountConsonants() {
        assertEquals(3, StringUtils.countConsonants("Hello"));
        assertEquals(7, StringUtils.countConsonants("Искать такси"));
        assertEquals(0, StringUtils.countConsonants(""));
        assertEquals(0, StringUtils.countConsonants(null));
    }

    @Test
    void testCountWordOccurrences() {
        String text = "Hello world, bye world, my dear world!";
        assertEquals(1, StringUtils.countWordOccurrences(text, "hello"));
        assertEquals(3, StringUtils.countWordOccurrences(text, "world"));
        assertEquals(0, StringUtils.countWordOccurrences(text, "hi"));
        assertEquals(0, StringUtils.countWordOccurrences(null, "hello"));
    }
}