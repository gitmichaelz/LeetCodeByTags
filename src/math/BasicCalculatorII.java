package math;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 *
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 *
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 * Constraints:
 *
 *     1 <= s.length <= 3 * 105
 *     s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 *     s represents a valid expression.
 *     All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 *     The answer is guaranteed to fit in a 32-bit integer.
 */
public class BasicCalculatorII {
    //idea来自于下面stack方法。这里用一个preVal来维护push进栈的元素。同时需要维护一个operator和curVal,当我们每遇到一个非空格非数字的字符(即遇到运算符时),
    //我们可以把一个tmpSum加进结果集。
    public int calculate(String s){
        if(s == null || s.isEmpty()) return 0;
        int sum = 0;
        int preVal = 0;
        char operator = '+';//初始化为'+', 即假设每个数都可以作为本身或者一个因子加入到结果集中。
        s += '+';//可以是任意
        for(int i = 0, curVal = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                curVal = curVal * 10 + c - '0';
            } else if(c != ' '){
                if(operator == '+'){
                    sum += preVal;//第一次计算的时候， sum += 0; 因为preVal初始化为0.
                    preVal = curVal;
                } else if(operator == '-'){
                    sum += preVal;
                    preVal = -curVal;
                } else if(operator == '*'){
                    preVal *= curVal;//注意此时只更新preVal,
                } else if(operator == '/'){
                    preVal /= curVal;
                }
                operator = c;
                curVal = 0;
            }
        }
        return sum + preVal;//主要最后要把最后更新的preVal加上。
    }

    //use stack
    // public int calculate(String s){
    //     if(s == null || s.isEmpty()) return 0;
    //     char operator = '+';
    //     s += '+';//这里可以是任意符号，即遇到非数字空格的情况下，对之前的tmpSum/preVal进行运算,怎样运算，利用维护的两个量，curVal, operator
    //             //详见else if里各种operator对应的运算。
    //     Deque<Integer> stack = new LinkedList<>();
    //     for(int i = 0, curVal = 0; i < s.length(); i++){
    //         char c = s.charAt(i);
    //         if(Character.isDigit(c)){
    //             curVal = curVal * 10 + c - '0';
    //         } else if(c != ' '){
    //             if(operator == '+'){
    //                 stack.push(curVal);
    //             } else if(operator == '-'){
    //                 stack.push(-curVal);
    //             } else if(operator == '*'){
    //                 stack.push(stack.pop() * curVal);
    //             } else if(operator == '/'){
    //                 stack.push(stack.pop() / curVal);
    //             }
    //             operator = c;
    //             curVal = 0;
    //         }
    //     }
    //     int sum = 0;
    //     while(!stack.isEmpty()){
    //         sum += stack.pop();
    //     }
    //     return sum;
    // }
}
