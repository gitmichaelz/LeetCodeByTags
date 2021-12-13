package bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 *
 *
 * Example 1:
 *
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 */
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> dict = new HashSet<>(Arrays.asList(deadends));
        String start = "0000";
        if (dict.contains(start))
            return -1;
        if (start.equals(target))
            return 0;

        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        begin.add(start);
        end.add(target);

        int step = 0;
        Set<String> temp;
        while (!begin.isEmpty() && !end.isEmpty()) {
            step++;
            if (begin.size() > end.size()) {
                temp = begin;
                begin = end;
                end = temp;
            }
            temp = new HashSet<>();

            for (String s : begin) {
                dict.add(s);
                for (int i = 0; i < 4; i++) {
                    for (int j = -1; j <= 1; j += 2) {
                        char[] wordToChar = s.toCharArray();
                        wordToChar[i] = (char)((wordToChar[i] - '0' + j + 10) % 10 + '0');
                        String next = new String(wordToChar);
                        if (end.contains(next))
                            return step;
                        if (dict.contains(next))
                            continue;
                        temp.add(next);
                    }
                }
            }
            begin = temp;
        }
        return -1;
    }
}
