package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [5,6,3,2,4,1]
 */

public class N_aryTreePostorderTraversal {
    //Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }
    private void dfs(Node root, List<Integer> res) {
        if(root == null) return;
        for(Node child : root.children) {
            dfs(child, res);
        }
        res.add(root.val);
    }
    // public List<Integer> postorder(Node root) {
    //     LinkedList<Integer> res = new LinkedList<>();
    //     if(root == null) return res;
    //     Deque<Node> stack = new LinkedList<>();
    //     stack.push(root);
    //     while(!stack.isEmpty()){
    //         root = stack.pop();
    //         res.addFirst(root.val);
    //         for(Node child : root.children) {
    //             stack.push(child);
    //         }
    //     }
    //     return res;
    // }
}
