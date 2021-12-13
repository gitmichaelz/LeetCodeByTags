package palindrome;

/**
 * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
 *
 * Return the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 *
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 */
public class ShortestPalindrome {
    //根据下面的approach 1, 我们知道实际上我们需要的是s + reverse的最长的公共前缀和后缀，所以可以用KMP中buildNext table的方法来求出s + reverse的最长prefixsuffix的长度，然后截取相关片段形成结果。注意特殊情况，如果s = aaaaa, rev = aaaaa,这样他们的连接形成的字符串的最长prefix suffix会出现大于原串的情况，这时，我们要在他们中间加个特殊字符以防止这种情况。
    public String shortestPalindrome(String s){
        if(s == null || s.length() < 2) return s;
        String reverse = new StringBuilder(s).reverse().toString();
        String str = s + "#" + reverse;//坑。一定要加'#'， 例如 s = aaaaa, rev = aaaaa,那这样求最长公共前后缀的时候回出现len大于原串s的情况。所有这里一定要隔开意面出现10个连续a
        int n = str.length();
        int[] next = new int[n];
        for(int j = 0, i = 1; i < n; i++){
            while(j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j - 1];
            }
            if(str.charAt(i) == str.charAt(i)){
                next[i] = ++j;
            }
        }

        return reverse.substring(0, s.length() - next[n - 1]) + s;
    }



    // approach 1> brute force: if we can find the the largest segment from beginning of s which is a palindrome,then we reverse the remaining of s
    //and reverse it and add it to the beginning.
    //for example "abcbaxy", the largest palindrome from beginning is "abcba", we then reverse the remaining part "yx" amd add it to the head of s
    //it will be "yxabcbaxy"
    //algorithm: create the reverse of s, say "reverse"
    //compare s.substring(0, n - i), reverse.substring(i) where i is from 0 to n - 1; once they are equal, we take reverse(0, i) and
    //add it the beginning of s and return. that's what we need.
    //time: O(N^2)
    // public String shortestPalindrome(String s) {
    //     int n = s.length();
    //     String reverse = new StringBuilder(s).reverse().toString();
    //     for(int i = 0; i < n; i++){
    //         if(s.substring(0, n - i).equals(reverse.substring(i))) {
    //             return reverse.substring(0, i) + s;//use the remaining of reverse
    //         }
    //     }
    //     return "";
    // }
}
