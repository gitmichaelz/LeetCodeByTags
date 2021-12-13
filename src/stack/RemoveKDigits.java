package stack;

/**
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int remainingDigits = num.length() - k;
        char[] stack = new char[num.length()];//use a char array to mimic the stack, because we will use it to create a res string
        int top = 0;
        // k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        for(int i = 0; i < num.length(); i++){
            char c = num.charAt(i);
            while(top > 0 && stack[top - 1] > c && k > 0){//坑,不要忘记k > 0
                top--;//remove the top one
                k--;//removed one
            }
            stack[top++] = c;
        }
        //remove the leading 0s
        int start = 0;
        while(start < remainingDigits && stack[start] == '0') start++;
        return start == remainingDigits? "0" : new String(stack, start, remainingDigits - start);//new String(char[], int offset, int count)
    }
}
