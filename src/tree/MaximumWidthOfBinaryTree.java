package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 *
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * It is guaranteed that the answer will in the range of 32-bit signed integer.
 *
 *
 */
public class MaximumWidthOfBinaryTree {
    //dfs, use the same idea that we can get the child's index given then parent id
    //record the id of left most node when first time at each level of the tree during a pre-order traversal, (you can check the depth and
    // the size of container to hold the first id),
    // and for each node, we need to calculate the width by nodes'id - first node's id + 1, and update maxWidth if necessary
    public int widthOfBinaryTree(TreeNode root){
        List<Integer> leftIds = new ArrayList<>();
        return dfs(root, 1, 0, leftIds);
    }

    private int dfs(TreeNode root, int id, int depth, List<Integer> leftIds){
        if(root == null) return 0;
        if(depth >= leftIds.size()) leftIds.add(id);
        return Math.max(id - leftIds.get(depth) + 1, Math.max(dfs(root.left, 2 * id, depth + 1, leftIds), dfs(root.right, 2 * id + 1, depth + 1, leftIds)));
    }
    //level order traverse tree nodes, for each node, we use a map to store the node and it's position (for example, root is 1, it's left child is 2 * 1,
    // it's right child is 2 * 1 + 1). and at each level we mark the start and end point, length = end - start + 1
    // public int widthOfBinaryTree(TreeNode root){
    //     int maxWidth = 0;
    //     if(root == null) return maxWidth;
    //     Deque<TreeNode> queue = new LinkedList<>();
    //     Map<TreeNode, Integer> map = new HashMap<>();
    //     queue.offer(root);
    //     map.put(root, 1);
    //     while(!queue.isEmpty()){
    //         int size = queue.size();
    //         int start = 0, end = 0;
    //         for(int i = 0; i < size; i++){
    //             root = queue.poll();
    //             if(i == 0) start = map.get(root);
    //             if(i == size - 1) end = map.get(root);
    //             if(root.left != null) {
    //                 queue.offer(root.left);
    //                 map.put(root.left, 2 * map.get(root));
    //             }
    //             if(root.right != null) {
    //                 queue.offer(root.right);
    //                 map.put(root.right, 2 * map.get(root) + 1);
    //             }
    //         }
    //         maxWidth = Math.max(end - start + 1, maxWidth);
    //     }
    //     return maxWidth;
    // }
}
