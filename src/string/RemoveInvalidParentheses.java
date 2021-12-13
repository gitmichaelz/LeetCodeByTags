package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return all the possible results. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 *
 * Example 2:
 *
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 */
public class RemoveInvalidParentheses {
    //这个方法比下面的那个好懂一点。算法一样，但更readable
    //关于这道题为什么嫩敢保证remove的是minimum number of invalid parentheses
    //因为我们再判断括号有效性的时候，保证了一出现无效的情况（右括号数目比左括号多），我们就尝试删除每一个可能的右括号
    //比如 "(()()))" 这时候 所有位置的右括号都是可以被删除的，但是为了避免出现重复的结果，我们对于连续的右括号，只需要
    //删除第一个。所有上述情况我们会删除 s[2] 或者s[4], 从而得到"((()))" 和 "(()())"
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(res, s, 0, 0, '(', ')');
        return res;
    }
    //We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
    //The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.
    //this remove function is removing every possible invalid right/closed parenthesis (or left/open parenthesis in
    //reverse order)
    //i_start, j_start: the position where we start scanning the string,
    private void remove(List<String> res, String s, int i_start, int j_start, char openP, char closeP) {
        int count = 0;
        int i = i_start;
        while(i < s.length() && count >= 0) {
            char c = s.charAt(i);
            if(c == openP) {
                count++;
            } else if(c == closeP) {
                count--;
            }
            i++;
        }

        if(count >= 0) {//no extra ')' is detected, we now have to check extra '(' by reversing the string
            String reverse = new StringBuilder(s).reverse().toString();
            if(openP == '(') {//坑。这里一定要判断，否则会陷入无限循环
                remove(res, reverse, 0, 0, closeP, openP);
            } else {//we already reversed twice
                res.add(reverse);//原串被reverse了两次，顺序没变。所以这里需要添加reverse两次之后的结果
            }
        } else {//extra ')' is detected and we have to remove every possible ')',
            i--;//when we break while loop, i increment once, we need the index of abnormal ')' which makes count < 0
            for(int j = j_start; j <= i; j++) {//出现不合法的右括号，以下我们是remove掉从j_start 到 i 位置所有可能的右括号(但是连续的右括号我们只移除第一个)
                if(s.charAt(j) == closeP && (j == j_start || s.charAt(j - 1) != closeP)) {
                    remove(res, s.substring(0, j) + s.substring(j + 1), i, j, openP, closeP);//注意这里是i, j(not i + 1, j + 1)，因为remove掉一个字符，i, j相当于一个新的位置
                }
            }
        }
    }





/*
    public List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<>();
        remove1(res, s, 0, 0, '(', ')');
        return res;
    }

    private void remove1(List<String> res, String s, int i_start, int j_start, char openP, char closeP) {
        int count = 0;
        for(int i = i_start; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == openP) {
                count++;
            } else if(c == closeP) {
                count--;
            }
            if(count >= 0) continue;
            //出现不合法的右括号，以下我们是remove掉从j_start 到 i 位置所有可能的右括号(但是连续的右括号我们只移除第一个)
            for(int j = j_start; j <= i; j++) {//坑，这里j <= i,不要忘记“=”。count < 0; we have extra right parenthesis to remove。
                // try removing one right parenthesis at each position[j, i], skipping duplicates
                if(s.charAt(j) == closeP && (j == j_start || s.charAt(j - 1) != closeP)) {
                    remove1(res, s.substring(0, j) + s.substring(j + 1), i, j, openP, closeP);//注意这里是i, j(not i + 1, j + 1)，因为remove掉一个字符，i, j相当于一个新的位置
                }
            }
            //stop here, the reverse calls take care of the remaining string
            return;//当发现一处invalid right parenthesis时，我们把之前所有可能的remove掉，然后后面的会在上面28行的remove call里都解决掉。只不过是从当前invlaid的位置往后扫。坑。一开始把return放到上面for的if里面。这是不对的。因为我们要try removing ONE right parenthesis at each position, skipping duplicates
        }
        //no invalid closed parenthesis detected, now check the opposite direction
        String reverse = new StringBuilder(s).reverse().toString();
        if(openP == '(') {//坑。这里一定要判断，否则会陷入无限循环
            remove1(res, reverse, 0, 0, closeP, openP);
        } else {
            res.add(reverse);//原串被reverse了两次，顺序没变。所以这里需要添加reverse两次之后的结果
        }
    }
    */
}
