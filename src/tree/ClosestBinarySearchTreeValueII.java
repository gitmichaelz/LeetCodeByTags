package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target. You may return the answer in any order.
 *
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 * Example 1:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286, k = 2
 * Output: [4,3]
 */
public class ClosestBinarySearchTreeValueII {
    //https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/70511/AC-clean-Java-solution-using-two-stacks
    //思路就是用两个stack,分别维护树中小于target的节点和大于target的节点。即predecessor 和successor节点。
//通过中序遍历来分别收集这些前驱和后驱节点。这样两个装有前驱节点的stack从顶到底部是递减的，装有后驱节点的stack从顶部到底部是递增的。
//然后类似于merge sort那样从俩stack中拿出总共k个节点。
    public List<Integer> closestKValues(TreeNode root, double target, int k){
        Deque<Integer> precedessors = new LinkedList<>();
        Deque<Integer> successors = new LinkedList<>();
        inorder(precedessors, root, false, target);
        inorder(successors, root, true, target);
        List<Integer> res = new ArrayList<>();
        while(k-- > 0){
            if(precedessors.isEmpty()){
                res.add(successors.pop());
            } else if(successors.isEmpty()){
                res.add(precedessors.pop());
            } else if(target - precedessors.peek() < successors.peek() - target) {
                res.add(precedessors.pop());
            } else {
                res.add(successors.pop());
            }
        }
        return res;
    }

    private void inorder(Deque<Integer> stack, TreeNode root, boolean reverse, double target){
        if(root == null) return;
        inorder(stack, reverse? root.right : root.left, reverse, target);
        if(!reverse && root.val >= target || reverse && root.val < target) return;//如果当前节点已经不符合,没必要继续递归另一半子树了,因为肯定不符合.符合的都在前一半.自己画个图体会一下. 注意!!!不要忘记把"="加到其中一个上
        stack.push(root.val);
        inorder(stack, reverse? root.left : root.right, reverse, target);
    }
}
