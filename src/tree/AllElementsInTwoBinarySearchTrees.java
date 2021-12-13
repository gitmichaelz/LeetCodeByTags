package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given two binary search trees root1 and root2.
 *
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 */
public class AllElementsInTwoBinarySearchTrees {
    //重点掌握approach 1 and approach 2
    //approach 1>  use two stacks to store the path from current root to smallest node, then compare the top of the two stacks
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        smallest(root1, stack1);
        smallest(root2, stack2);
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            Deque<TreeNode> stack = stack1.isEmpty()? stack2 : stack2.isEmpty()? stack1 : stack1.peek().val > stack2.peek().val? stack2 : stack1;
            TreeNode node = stack.pop();
            res.add(node.val);
            smallest(node.right, stack);
        }
        return res;
    }

    private void smallest(TreeNode root, Deque<TreeNode> stack) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }


    //approach 2>
    //use a queue inorder traverse root1 first, then root2, when traverse root 2, when can add any nodes in queue which is or equal than root2 to our res list, then add cur root2's val, after traverse root2, we need to add any nodes left in queue to our res list
    // List<Integer> ans = new ArrayList<>();
    // public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
    //     Deque<TreeNode> queue = new LinkedList<>();
    //     inorder(root1, queue, true);
    //     inorder(root2, queue, false);
    //     while(!queue.isEmpty()) {
    //         ans.add(queue.poll().val);
    //     }
    //     return ans;
    // }
    // private void inorder(TreeNode root, Deque<TreeNode> queue, boolean pushEnabled) {
    //     if(root == null) return;
    //     inorder(root.left, queue, pushEnabled);
    //     if(pushEnabled) {
    //         queue.offer(root);
    //     } else {
    //         while(!queue.isEmpty() && queue.peek().val <= root.val) {
    //             ans.add(queue.poll().val);
    //         }
    //         ans.add(root.val);
    //     }
    //     inorder(root.right, queue, pushEnabled);
    // }




    // public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
    //     List<Integer> res = new ArrayList<>();
    //     List<Integer> l1 = new ArrayList<>();
    //     List<Integer> l2 = new ArrayList<>();
    //     inorderTraversal(root1, l1);
    //     inorderTraversal(root2, l2);
    //     int p1 = 0, p2 = 0;
    //     while(p1 < l1.size() || p2 < l2.size()) {
    //         if(p1 == l1.size() || (p2 < l2.size() && l2.get(p2) <= l1.get(p1))) {
    //             res.add(l2.get(p2++));
    //         } else {
    //             res.add(l1.get(p1++));
    //         }
    //     }
    //     return res;
    // }
    // private void inorderTraversal(TreeNode root, List<Integer> res) {
    //     if(root == null) return;
    //     inorderTraversal(root.left, res);
    //     res.add(root.val);
    //     inorderTraversal(root.right, res);
    // }
}
