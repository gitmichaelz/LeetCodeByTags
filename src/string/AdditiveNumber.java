package string;

/**
 * An additive number is a string whose digits can form an additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits, return true if it is an additive number or false otherwise.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: "112358"
 * Output: true
 * Explanation:
 * The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 */
public class AdditiveNumber {
    //since the num length is 35, and each operand's length can be (num.length - 1)/2, so it is safe to use a long( which length is up to 19)
    public boolean isAdditiveNumber(String num){
        int len = num.length();
        for(int i = 1; i <= (len - 1)/2; i++){//i is the length of first num
            if(num.charAt(0) == '0' && i > 1) return false;
            Long a = Long.valueOf(num.substring(0, i));
            for(int j = 1; Math.max(i, j) <= len - i - j; j++){//j is the length of the second num
                if(num.charAt(i) == '0' && j > 1) break;
                Long b = Long.valueOf(num.substring(i, i + j));//注意这里是i + j, 不是j
                if(isValid(a, b, num.substring(i + j))){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(Long a, Long b, String sub){
        if(sub.isEmpty()) return true;
        Long sum = a + b;
        String next = String.valueOf(sum);
        if(!sub.startsWith(next)) return false;
        return isValid(b, sum, sub.substring(next.length()));
    }

//     //iterative
//     public boolean isAdditiveNumber(String num){
//         int len = num.length();
//         for(int i = 1; i <= (len - 1)/2; i++){
//             for(int j = 1; Math.max(i, j) <= len - i - j; j++){
//                 if(isValid(i, j, num)) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

//     private boolean isValid(int i, int j, String num){
//         if(num.charAt(0) == '0' && i > 1) return false;
//         if(num.charAt(i) == '0' && j > 1) return false;
//         String next = "";
//         Long a = Long.valueOf(num.substring(0, i));
//         Long b = Long.valueOf(num.substring(i, i + j));
//         for(int start = i + j; start < num.length(); start += next.length()) {
//             Long sum = a + b;
//             b = sum;//dont forget update b = sum, a = sum - a;
//             a = sum - a;
//             next = String.valueOf(sum);
//             if(!num.startsWith(next, start)) return false;
//         }
//         return true;
//     }
}
