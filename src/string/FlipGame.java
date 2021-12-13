package string;

import java.util.ArrayList;
import java.util.List;

/**
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other person will be the winner.
 *
 * Return all possible states of the string currentState after one valid move. You may return the answer in any order. If there is no valid move, return an empty list [].
 *
 *
 *
 * Example 1:
 *
 * Input: currentState = "++++"
 * Output: ["--++","+--+","++--"]
 */
public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> answer = new ArrayList<>();
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] == '+' && arr[i+1] == '+'){
                arr[i] = arr[i+1] = '-';
                answer.add(new String(arr));
                arr[i] = arr[i+1] = '+';
            }
        }
        return answer;
    }
}
