package math;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 *
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int pa = a.length() - 1, pb = b.length() - 1;
        int carry = 0;
        while(pa >= 0 || pb >= 0 || carry > 0) {
            if(pa >= 0) carry += a.charAt(pa--) - '0';
            if(pb >= 0) carry += b.charAt(pb--) - '0';
            sb.append(carry % 2);
            carry /= 2;
        }
        return sb.reverse().toString();
    }
}
