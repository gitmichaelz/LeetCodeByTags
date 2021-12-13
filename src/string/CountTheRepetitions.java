package string;

/**
 * We define str = [s, n] as the string str which consists of the string s concatenated n times.
 *
 *     For example, str == ["abc", 3] =="abcabcabc".
 *
 * We define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1.
 *
 *     For example, s1 = "abc" can be obtained from s2 = "abdbec" based on our definition by removing the bolded underlined characters.
 *
 * You are given two strings s1 and s2 and two integers n1 and n2. You have the two strings str1 = [s1, n1] and str2 = [s2, n2].
 *
 * Return the maximum integer m such that str = [str2, m] can be obtained from str1.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
 * Output: 2
 */
public class CountTheRepetitions {
    //https://leetcode.com/problems/count-the-repetitions/discuss/95398/c-solution-inspired-by-70664914-with-organized-explanation
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int[] repeCount = new int[n1 + 1];
        int[] nextIdx = new int[n1 + 1];
        int j = 0, count = 0;
        for(int k = 1; k <= n1; k++) {
            for(int i = 0; i < s1.length(); i++) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    j++;
                    if(j == s2.length()) {
                        count++;
                        j = 0;
                    }
                }
            }
            nextIdx[k] = j;
            repeCount[k] = count;
            for(int start = 0; start < k; start++) {
                if (nextIdx[start] == j) {//found pattern
                    int interval = k - start;
                    int repeat = (n1 - start)/interval;
                    int patternCount = repeat * (repeCount[k] - repeCount[start]);//repeCount[k] - repeCount[start] is not necessarily 1, see
                    //example "aaa" 3  "aa" 1
                    int remainingCount = repeCount[start + (n1 - start) % (k - start)];
                    return (patternCount + remainingCount) / n2;
                }
            }
        }
        return repeCount[n1]/n2;
    }
}
