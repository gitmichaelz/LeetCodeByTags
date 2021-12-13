package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the
 * values of the nodes in the tree.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 */
public class KthSmallestElementInaBST {
    //Time complexity : O(H+k), where H is a tree height. This complexity is defined by the stack, which contains at least H+k elements, since before starting to pop out one has to go down to a leaf. This results in O(log⁡N+k) for the balanced tree and O(N+k) for completely unbalanced tree with all the nodes in the left subtree.
//Space complexity : O(H+k), the same as for time complexity, O(N+k) in the worst case, and O(log⁡N+k) in the average case.
    ////follow up question, if tree can be delete/insert frequently, how to optimize
    //we can make some change with the treenode, add pre, next pointer to make it looks like a doubly linked list, but also
    //it has left, right child, so insert/delete would be O(H), and search k would be O(k), total O(H + K)
    //space: O(N)  to keep the linkedlist
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        while(root != null || !stack.isEmpty()){
            if(root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                k--;
                if(k == 0) return root.val;
                root = root.right;
            }
        }
        return -1;
    }
}
