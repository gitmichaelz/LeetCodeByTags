package array_matrix_sorting;

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temps) {
        int[] result = new int[temps.length];

        for (int i=result.length-1; i>=0; i--) {
            int j = i+1;
            while(j < result.length) {
                if (temps[j] > temps[i]) {
                    result[i] = j-i;
                    break;
                } else if (result[j] == 0) {
                    break;
                } else {
                    j += result[j];
                }
            }
        }

        return result;
    }
}
