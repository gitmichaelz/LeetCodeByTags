package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right, and the reverse order of the right boundary.
 *
 * The left boundary is the set of nodes defined by the following:
 *
 *     The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
 *     If a node in the left boundary and has a left child, then the left child is in the left boundary.
 *     If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
 *     The leftmost leaf is not in the left boundary.
 *
 * The right boundary is similar to the left boundary, except it is the right side of the root's right subtree. Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.
 *
 * The leaves are nodes that do not have any children. For this problem, the root is not a leaf.
 *
 * Given the root of a binary tree, return the values of its boundary.
 */
public class BoundaryOfBinaryTree {
    //https://leetcode.com/problems/boundary-of-binary-tree/discuss/101294/Java-C%2B%2B-Clean-Code-(1-Pass-perorder-postorder-hybrid)
    //1. if node is left bound, node.left is also left bound. and if it has no left child, node.right could also be left bound
    //2. same applys for right bound
    //3. if node is left bound, add it before its children -- preorder
    //   if node is right bound, add it after its children -- postorder
    //4. A leaf node that is either left or right bound belongs to the bottom line
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        res.add(root.val);
        getBoundary(root.left, res, true, false);
        getBoundary(root.right, res, false, true);
        return res;
    }

    private void getBoundary(TreeNode root, List<Integer> res, boolean lb, boolean rb){
        if(root == null) return;
        if(lb) res.add(root.val);
        if(!lb && !rb && root.left == null && root.right == null) res.add(root.val);
        //note: rb && root.right == null, not "||". why? if we wanna make root.left a right bound, we must make sure rb and root.right == null
        getBoundary(root.left, res, lb, rb && root.right == null);//if node is right bound, and if it has no right child, node.left could also be right bound
        getBoundary(root.right, res, lb && root.left == null, rb);//if node is left bound, and if it has no left child, node.right could also be left bound
        if(rb) res.add(root.val);
    }
}
