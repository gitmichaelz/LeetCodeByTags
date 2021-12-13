package tree;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Example 1:
 *
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 */
public class InvertBinaryTree {
    //这题stack, queue，bottom up 递归, top down递归都要会，理解其精髓
    //preOrder traversal, swap each node's left/right child
    // public TreeNode invertTree(TreeNode root) {
    //     if(root == null) return null;
    //     Deque<TreeNode> stack = new ArrayDeque<>();//注意， arrayDeque 不支持null值插入
    //     TreeNode cur = root;
    //     stack.push(cur);
    //     while(!stack.isEmpty()) {
    //         cur = stack.pop();
    //         TreeNode tmp = cur.left;
    //         cur.left = cur.right;
    //         cur.right = tmp;
    //         if(cur.left != null) {
    //             stack.push(cur.left);
    //         }
    //         if(cur.right != null) {
    //             stack.push(cur.right);
    //         }
    //     }
    //     return root;
    // }

    //level order traversal, swap each node's left/right child
    // public TreeNode invertTree(TreeNode root) {
    //     if(root == null) return null;
    //     Deque<TreeNode> queue = new ArrayDeque<>();
    //     TreeNode cur = root;
    //     queue.offer(cur);
    //     while(!queue.isEmpty()) {
    //         cur = queue.poll();
    //         TreeNode tmp = cur.left;
    //         cur.left = cur.right;
    //         cur.right = tmp;
    //         if(cur.left != null) {
    //             queue.offer(cur.left);
    //         }
    //         if(cur.right != null) {
    //             queue.offer(cur.right);
    //         }
    //     }
    //     return root;
    // }


    //bottom up
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }



    //top down
    // public TreeNode invertTree(TreeNode root) {
    //     if(root == null) return root;
    //     TreeNode tmp = root.left;
    //     root.left = root.right;
    //     root.right = tmp;
    //     invertTree(root.left);
    //     invertTree(root.right);
    //     return root;
    // }
}
