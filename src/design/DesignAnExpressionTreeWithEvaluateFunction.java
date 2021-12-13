package design;

import java.util.Stack;

/**
 * Given the postfix tokens of an arithmetic expression, build and return the binary expression tree that represents this expression.
 *
 * Postfix notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before their operators. For example, the postfix tokens of the expression 4*(5-(7+2)) are represented in the array postfix = ["4","5","7","2","+","-","*"].
 *
 * The class Node is an interface you should use to implement the binary expression tree. The returned tree will be tested using the evaluate function, which is supposed to evaluate the tree's value. You should not remove the Node class; however, you can modify it as you wish, and you can define other classes to implement it if needed.
 *
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with two children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).
 *
 * It's guaranteed that no subtree will yield a value that exceeds 109 in absolute value, and all the operations are valid (i.e., no division by zero).
 *
 * Follow up: Could you design the expression tree such that it is more modular? For example, is your design able to support additional operators without making changes to your existing evaluate implementation?
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["3","4","+","2","*","7","/"]
 * Output: 2
 * Explanation: this expression evaluates to the above binary tree with expression ((3+4)*2)/7) = 14/7 = 2.
 */
public class DesignAnExpressionTreeWithEvaluateFunction {
    abstract class Node {
        public abstract int evaluate();
        // define your fields here
    }

    class TreeNode extends Node{
        // Constructor
        String val;
        TreeNode left;
        TreeNode right;
        TreeNode(String val) {
            this.val = val;
        }

        // Abstract method cannot have body; so fill in body here.
        public int evaluate() {
            return dfs(this); // use this keyword for current TreeNode object.
        }

        private int dfs(TreeNode root) {
            if (root.left == null && root.right == null) {
                return Integer.valueOf(root.val);
            }
            int left = dfs(root.left);
            int right = dfs(root.right);
            String operator = root.val;
            int res = 0;
            if (operator.equals("+")) {
                res = left + right;
            } else if (operator.equals("-")) {
                res = left - right;
            } else if (operator.equals("*")) {
                res = left * right;
            } else {
                res = left / right;
            }
            return res;
        }

    }


    /**
     * This is the TreeBuilder class.
     * You can treat it as the driver code that takes the postinfix input
     * and returns the expression tree represnting it as a Node.
     */

    class TreeBuilder {
        String operators = "+-*/";
        Stack<TreeNode> stack = new Stack<>();
        Node buildTree(String[] postfix) {
            for (String str : postfix) {
                if (operators.contains(str)) {
                    TreeNode cur = new TreeNode(str);
                    cur.right = stack.pop();
                    cur.left = stack.pop();
                    stack.push(cur);
                } else {
                    stack.push(new TreeNode(str));
                }
            }
            return stack.pop();
        }
    }
}
