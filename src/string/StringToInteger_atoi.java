package string;

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 *     Read in and ignore any leading whitespace.
 *
 *     Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
 *
 *     Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
 *
 *     Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 *
 *     If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
 *
 *     Return the integer as the final result.
 */
public class StringToInteger_atoi {
    //1> skip spaces
    //2> optional: take a plus or minus sign
    //3> interprets numerical digit as interger value, and the thing needs to be taken care is to check if overflow, if overflow, return Integer.MAX_VALUE/MIN_VALUE

    //Overflow explanation: Integer.MAX_VALUE = 2147483647 and Integer.MIN_VALUE = -2147483648 is the largest/smallest value that an int primitive can contain.
    //
    //Let's simplify this problem. Suppose str1 = " -a649b ", st2 = " a652b ", max = 647, min = -648. So if atoi(str) > 647 || atoi(str) < -648 atoi will overflow.
    //In other words, when we've parsed num == 64 and the next char is also digit, max / min can directly be returned if the next digit >= 8; or we've parsed num > 64, directly return too.
    public int myAtoi(String s) {
        if(s == null) return 0;
        s = s.trim();
        if(s.isEmpty()) return 0;
        int idx = 0;
        int sign = 1;
        int res = 0;
        if(s.charAt(idx) == '+' || s.charAt(idx) == '-') {
            if(s.charAt(idx) == '-') {
                sign = -1;
            }
            idx++;
        }
        while(idx < s.length() && Character.isDigit(s.charAt(idx))) {
            int digit = s.charAt(idx) - '0';
            if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + digit;
            idx++;
        }
        return sign * res;
    }
}
