package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 *
 * A leaf is a node with no children.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 */
public class BinaryTreePaths {
    //dfs, collect all nodes down to the leaf, once hit the leaf, collect current path to res list, if not leaf, add arrow and continue do dfs
    public List<String> binaryTreePaths(TreeNode root){
        List<String> res = new ArrayList<>();
        dfs(root, res, new StringBuilder());
        return res;
    }

    private void dfs(TreeNode root, List<String> res, StringBuilder path){
        if(root == null) return;
        path.append(root.val);
        if(root.left == null && root.right == null){//一定要有一个叶子的判断
            res.add(path.toString());
            return;
        }
        path.append("->");//如果不是叶子节点，需要加箭头
        int len = path.length();
        dfs(root.left, res, path);
        path.setLength(len);
        dfs(root.right, res, path);
        path.setLength(len);
    }
}
