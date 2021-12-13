package string;

/**
 * Given two stings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 *
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] charCount = new int[26];

        int count = 0;
        for(char c:ransomNote.toCharArray()) {
            charCount[c-'a']++;
            if(charCount[c-'a'] == 1) {
                count++;
            }
        }

        for(char c: magazine.toCharArray()) {
            if(count == 0) return true;
            charCount[c-'a']--;
            if(charCount[c-'a'] == 0) {
                count--;
            }
        }

        return count == 0;
    }
}
