package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.
 *
 *     For example, the below binary watch reads "4:51".
 *
 * Given an integer turnedOn which represents the number of LEDs that are currently on, return all possible times the watch could represent. You may return the answer in any order.
 *
 * The hour must not contain a leading zero.
 *
 *     For example, "01:00" is not valid. It should be "1:00".
 *
 * The minute must be consist of two digits and may contain a leading zero.
 *
 *     For example, "10:2" is not valid. It should be "10:02".
 *
 *
 *
 * Example 1:
 *
 * Input: turnedOn = 1
 * Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 */
public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        Set<String> result = new HashSet<>();
        int[] hours =  {1, 2, 4, 8};
        int[] minutes = {1, 2, 4, 8, 16, 32};
        // int[] selectedHours =  new int[hours.length];
        // int[] selectedMinutes =  new int[minutes.length];
        // helper(num, selectedHours, selectedMinutes, 0, 0, result, hours, minutes);
        helper(num, 0, 0, 0, 0, result, hours, minutes);
        return new ArrayList<>(result);
    }

    public void helper(int num, int hourIndex, int minuteIndex, int hour, int minute, Set<String> result, int[] hours, int[] minutes) {
        if(num == 0){
            StringBuffer bf = new StringBuffer();
            bf.append(hour).append(":");
            if(minute < 10){
                bf.append("0").append(minute);
            }else {
                bf.append(minute);
            }
            result.add(bf.toString());
            return;
        }
        //选择hours
        for(int i = hourIndex; i < hours.length; i ++){
            int newHour =  hour  + hours[i];
            if(newHour > 11){
                continue;
            }
            helper(num - 1, i + 1, minuteIndex, newHour, minute, result, hours, minutes);
        }
        //选择minutes
        for(int i = minuteIndex; i < minutes.length; i ++){
            int newMinute =  minute  + minutes[i];
            if(newMinute > 59){
                continue;
            }
            helper(num - 1, hourIndex, i + 1, hour, newMinute, result, hours, minutes);
        }
    }
    /*
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < 12; ++i){
            for(int j = 0; j < 60; ++j){
                if(Integer.bitCount(i) + Integer.bitCount(j) == num){
                    if(j < 10) result.add(i + ":0" + j);
                    else result.add(i + ":" + j);
                }
            }
        }
        return result;
    }
     */
}
