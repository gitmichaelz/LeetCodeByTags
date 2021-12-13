package tree;

/**
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped
 * by mistake. Recover the tree without changing its structure.
 */
public class RecoverBianrySearchTree {
    //https://www.zybuluo.com/mdeditor#31158-full-reader
    //two cases:
    // 1> if the two swapped are adjacent, we can only find one pair of disorder nodes   (1, 2, 4, 3, 5) -> swap (4, 3)directly
    // 2> if the two swapped are not adjacent, we can find two pair of disorder nodes    (5, 2, 3, 4, 1) -> we need to find the first one of the first disorder pairs(5) and the second one of the second disorder pairs (1), then swap their values
    //follow up requires O(1) space, we need to use Morris Traversal。 常量空间中序遍历二叉树，一般就是指Morris Traversal
    //想用O(1)空间进行遍历，因为不能用栈作为辅助空间来保存父节点的信息，重点在于当访问到子节点的时候如何重新回到父节点（当然这里是指没有父节点指针，如果有其实就比较好办，一直找遍历的后驱结点即可）。Morris遍历方法用了线索二叉树，这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。
    //算法具体分情况如下：
    //1. 如果当前结点的左孩子为空，则输出当前结点并将其当前节点赋值为右孩子。
    //2. 如果当前节点的左孩子不为空，则寻找当前节点在中序遍历下的前驱节点（也就是当前结点左子树的最右孩子）。接下来分两种情况：
    //a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点（做线索使得稍后可以重新返回父结点）。然后将当前节点更新为当前节点的左孩子。
    //b) 如果前驱节点的右孩子为当前节点，表明左子树已经访问完，可以访问当前节点。将它的右孩子重新设为空（恢复树的结构）。输出当前节点。当前节点更新为当前节点的右孩子。
    //space: O(1), time(N), actually we traverse 2 times for each path between two nodes
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null;
        TreeNode pre = null;
        while(root != null) {
            if(root .left != null) {
                TreeNode p = root.left;
                while(p.right != null && p.right != root){
                    p = p.right;
                }
                if(p.right == null) {
                    p.right = root;
                    root = root.left;
                    continue;
                } else {//we've done with left subtree
                    p.right = null;
                }
            }
            //either left is null or we've done with left subtree
            if(pre != null && pre.val >= root.val) {
                if(first == null){
                    first = pre;
                }
                second = root;
            }
            pre = root;//only when we output root, we make it as pre, and move root to right
            root = root.right;//这里，可以找到root的后驱节点。要么是起右孩子，要么是其后驱的父节点！！！！！
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    //O(N) space
    // public void recoverTree(TreeNode root) {
    //     TreeNode first = null, second = null;
    //     TreeNode pre = null;
    //     Deque<TreeNode> stack = new ArrayDeque<>();
    //     while(root != null || !stack.isEmpty()) {
    //         if(root != null) {
    //             stack.push(root);
    //             root = root.left;
    //         } else {
    //             root = stack.pop();
    //             if(pre != null && pre.val >= root.val){
    //                 if(first == null) {//important, we only assign first once,
    //                     first = pre;
    //                 }
    //                 second = root;
    //             }
    //             pre = root;
    //             root = root.right;
    //         }
    //     }
    //     int tmp = first.val;
    //     first.val = second.val;
    //     second.val = tmp;
    // }
}
