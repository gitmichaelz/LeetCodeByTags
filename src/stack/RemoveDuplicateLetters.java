package stack;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 */
public class RemoveDuplicateLetters {
    //这个和下面那种方法一个思路，无非这个不用stack, 可以对比着看看，这个稍微快一点，然后空间也更优
    public String removeDuplicateLetters(String s) {
        char[] ss = s.toCharArray();
        int[] count = new int[26];
        boolean[] used = new boolean[26];
        StringBuilder sb = new StringBuilder();

        for (char c : ss) {
            count[c - 'a']++;
        }

        for (char c : ss) {
            count[c - 'a']--; // 这一行和下面那一行不能替换，很明显啊，扫过了数目要减啊，尽管后面可能不加到结果中取了
            if (used[c - 'a']) {
                continue;
            }
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c && count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                used[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }

            used[c - 'a'] = true;
            sb.append(c);
        }
        return sb.toString();
    }
    /*
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() < 2) return s;
        Deque<Character> stack = new LinkedList<>();//its more like a monotonous stack, if cur char is smaller than stack.peek(); we keep popping stack until stack is empty or cur char > stack.peek
        int[] count = new int[26];
        boolean[] visited = new boolean[26];//skip duplicates, if we meet a char that already visited, we keep the first visited element. for example, "acbabc", the second a and b will be skipped
        char[] chars = s.toCharArray();
        for(char c : chars){
            count[c - 'a']++;
        }
        for(char c : chars){
            count[c - 'a']--;//这一步不要忘记，每遇上一个decrease its count, we consumed one char
            if(visited[c - 'a']) continue;

            while(!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0){//要确保count[stack.peek() - 'a'] > 0 即后面还有这个char
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(c);
            visited[c - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.removeLast());//因为我们用的是deque, stack最底部相当于last,
        }
        return sb.toString();
    }*/
}
