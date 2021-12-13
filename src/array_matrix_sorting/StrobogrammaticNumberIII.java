package array_matrix_sorting;

/**
 * Given two strings low and high that represent two integers low and high where low <= high, return the number of strobogrammatic numbers in the range [low, high].
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Example 1:
 *
 * Input: low = "50", high = "100"
 * Output: 3
 */
public class StrobogrammaticNumberIII {
    //借用II的思路
    private char[][] pairs = new char[][] {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'9', '6'}, {'8', '8'}};

    public int strobogrammaticInRange(String low, String high) {
        if(low == null || high == null || low.length() > high.length()
                || low.length() == high.length() && low.compareTo(high) > 0) return 0;
        int count = 0;
        for(int len = low.length(); len <= high.length(); len++){
            count += dfs(low, high, new char[len], 0, len - 1);
        }
        return count;
    }
    //注意：这里判断合法的时候，一定要用字符串比较，不要将其转化为整数，否则会有溢出风险，即使转化成long类型，效率也会降低
    private int dfs(String low, String high, char[] num, int left, int right) {
        if(left > right) {
            String s = new String(num);
            if(s.length() == low.length() && s.compareTo(low) < 0
                    || s.length() == high.length() && s.compareTo(high) > 0) {//判断是否合法，一定要先判断长度，只有长度相同才判断大小，否则 "69" > "100"
                return 0;
            } else {
                return 1;
            }
        }
        int count = 0;
        for(char[] pair : pairs){
            num[left] = pair[0];
            num[right] = pair[1];
            if(num.length > 1 && num[0] == '0') continue;//cannot start with 0 if num.length > 1
            if(left == right && pair[0] != pair[1]) continue;//cannot put 6/9 in the middle of string
            count += dfs(low, high, num, left + 1, right - 1);
        }
        return count;
    }
}
