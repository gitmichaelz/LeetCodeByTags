package string;

/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 *     countAndSay(1) = "1"
 *     countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a
 *     different digit string.
 *
 * To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a
 * contiguous section all of the same character. Then for each group, say the number of characters, then say the character. To convert the saying into a digit string, replace the counts with a number and concatenate every saying.
 * Example:
 *
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 */
public class CountAndSay {
    //这个题唯一需要注意的是不要忘记外层循环最后一定要把最后一个加上，因为内循环构造字符串时只有当前字符和之前字符不一致时才构造
    public String countAndSay(int n) {
        if(n < 1) return "";
        String res = "1";//the base case,ie. the first sequence "1"
        for(int i = 2; i <=n; i++) {
            StringBuilder sb = new StringBuilder();//to construct the ith sequence
            int count = 1;//we count how many digits are same by compare the jth and (j-1)th
            for(int j = 1; j < res.length(); j++) {
                if(res.charAt(j) == res.charAt(j - 1)){
                    count++;
                } else {
                    sb.append(count).append(res.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count).append(res.charAt(res.length() - 1));//dont forget append the number and the last digit as we are counting the number of j-1th digit
            res = sb.toString();//update res
        }
        return res;
    }
}
