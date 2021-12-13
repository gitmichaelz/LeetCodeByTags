package string;

/**
 * ou are playing the Bulls and Cows game with your friend.
 *
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
 *
 *     The number of "bulls", which are digits in the guess that are in the correct position.
 *     The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 *
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 *
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.
 *
 *
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 *   |
 * "7810"
 */
public class BullsAndCows {
    //一个小trick就是，遇到secret里的digit时加1,遇到guess里的digit时减1，同时判断，如果digits[secret[i]]的数量小于0，说明此digit在guess里出现过，让cows++，同理，如果digits[guess[i]] > 0,说明此digit在secret里出现过， cows++;
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] digits = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                if (digits[secret.charAt(i) - '0']++ < 0) cows++;
                if (digits[guess.charAt(i) - '0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
