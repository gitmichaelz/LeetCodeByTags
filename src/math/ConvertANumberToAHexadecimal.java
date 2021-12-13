package math;

/**
 * Given an integer num, return a string representing its hexadecimal representation. For negative integers, two’s complement method is used.
 *
 * All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the answer except for the zero itself.
 *
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 26
 * Output: "1a"
 */
public class ConvertANumberToAHexadecimal {
    //15的二进制表示 0b1111,    十六进制0xf 代表15    0X1a代表26  在本题中，返回结果不要带0x，但运算过程中的表示可以这样写
    public String toHex(int num) {
        if(num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while(num != 0){
            if((num & 0xf) > 9) {//也可以直接写num & 15
                sb.append((char)('a' + (num & 0xf) - 10));//坑，这些括号一定不要搞错。
            } else {
                sb.append(num & 0xf);
            }
            num >>>= 4;
        }
        return sb.reverse().toString();
    }
}
