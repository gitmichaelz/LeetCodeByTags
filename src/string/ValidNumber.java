package string;

/**
 * A valid number can be split up into these components (in order):
 *
 *     A decimal number or an integer.
 *     (Optional) An 'e' or 'E', followed by an integer.
 *
 * A decimal number can be split up into these components (in order):
 *
 *     (Optional) A sign character (either '+' or '-').
 *     One of the following formats:
 *         One or more digits, followed by a dot '.'.
 *         One or more digits, followed by a dot '.', followed by one or more digits.
 *         A dot '.', followed by one or more digits.
 *
 * An integer can be split up into these components (in order):
 *
 *     (Optional) A sign character (either '+' or '-').
 *     One or more digits.
 *
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "0"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "e"
 * Output: false
 */
public class ValidNumber {
    //consider the chars:
    // 1. digit (required), both integer/decimal number must contain at least one digit
    // 2. +, - (optional for integer/decimal number), if it present, it will always be the first char, since Exponent followed
    //      by an integer,which means a sign char can also appear immediately after an exponent
    // 3. An exponent ('e' or 'E', optional), it it present, it must be after a decimal number or integer, and an integer must
    //      follow the exponent.
    // 4. A dot("."), A decimal number should only contain one dot, integers cannot contain dots.
    //5. any other chars will be invalid
    public boolean isNumber(String s) {
        boolean hasDigit = false;
        boolean hasDot = false;
        boolean hasExponent = false;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                hasDigit = true;
            } else if(c == '.') {
                if(hasDot || hasExponent) return false;
                hasDot = true;
            } else if(c == 'e' || c == 'E') {
                if(hasExponent || !hasDigit) return false;
                hasExponent = true;
                hasDigit = false;
            } else if(c == '+' || c == '-') {
                //we only care if i > 0, it's pre char must either be 'e' or 'E'
                if(i > 0 && !(s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) return false;
            } else {
                return false;
            }
        }
        return hasDigit;
    }
}
