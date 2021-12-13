package math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 *
 * Example 1:
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 */
public class FractionToRecurringDecimal {
    //这个题的坑在于存放repeating number的index, 如果有recuring decimal,那么一定有一个remainder是重复出现的，我们把计算小数部分decimal的remainder及其位置放进map里，如果出现相同的remainder,则说明出现了循环部分
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
        StringBuilder sb = new StringBuilder();
        if(numerator > 0 ^ denominator > 0) sb.append('-');//符号相异
        long n = Math.abs((long)numerator);//in case of overflow Integer.MIN_VALUE/-1;如果不加long,结果还是Integer.MIN_VALUE
        long d = Math.abs((long)denominator);
        sb.append(n /d);
        if(n % d == 0) return sb.toString();

        sb.append('.');
        //deal with the decimal part, key is the remainder, the value is the start index of repeating digit
        Map<Long, Integer> map = new HashMap<>();
        long r = n % d;
        while(r > 0){
            if(map.containsKey(r)){
                int idx = map.get(r);
                sb.insert(idx, "(");
                sb.append(")");
                break;
            }
            map.put(r, sb.length());
            r *= 10;
            sb.append(r / d);
            r %= d;
        }
        return sb.toString();
    }
}
