package string_utils;

public class StringUtils {
    public static boolean isPalindrome(String text) {
        if (text == null) return false;
        String cleaned = text.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase();
        return cleaned.contentEquals(new StringBuilder(cleaned).reverse());
    }

    public static int countVowels(String text) {
        if (text == null) return 0;
        return (int) text.toLowerCase().chars()
                .filter(c -> "aeiouаеёиоуыэюя".indexOf(c) >= 0)
                .count();
    }

    public static int countConsonants(String text) {
        if (text == null) return 0;
        return (int) text.toLowerCase().chars()
                .filter(Character::isLetter)
                .filter(c -> "aeiouаеёиоуыэюя".indexOf(c) < 0)
                .count();
    }

    public static int countWordOccurrences(String text, String word) {
        if (text == null || word == null || word.isEmpty()) return 0;

        int count = 0;
        String[] words = text.toLowerCase().split("\\W+");
        String target = word.toLowerCase();

        for (String w : words) {
            if (w.equals(target)) {
                count++;
            }
        }
        return count;
    }
}
