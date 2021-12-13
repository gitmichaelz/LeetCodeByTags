package string;

/**
 * Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given sentence can be fitted on the screen.
 *
 * The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence = ["hello","world"], rows = 2, cols = 8
 * Output: 1
 * Explanation:
 * hello---
 * world---
 * The character '-' signifies an empty space on the screen.
 */
public class SentenceScreenFitting {
    //dp[i] means the how many words each row can hold if each row start with sentences[i]
    //then we can count total number of words row by row
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            int len = 0, index = i, words = 0;
            while(len + sentence[index % n].length() <= cols) {
                len += sentence[index % n].length() + 1;//dont forget + " "
                words++;
                index++;
            }
            dp[i] = words;
        }
        int total = 0;
        //index could larger than n, so here we use %
        for(int i = 0, index = 0; i < rows; i++) {
            total += dp[index];
            index = (index + dp[index]) % n;
        }
        return total/n;
    }
}
