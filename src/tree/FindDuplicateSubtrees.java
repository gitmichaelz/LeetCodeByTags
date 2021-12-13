package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 */
public class FindDuplicateSubtrees {
    //approach 2, instead get the serialization string for each subtree, we can assign an id for each unique subtree, and this id is a long type
    //id = root.val << 32 + left << 16 + right
    //time: O(N), space: O(N)
    public List<TreeNode> findDuplicateSubtrees(TreeNode root){
        List<TreeNode> res = new ArrayList<>();
        Map<Long, int[]> map = new HashMap<>();
        traverse(root, res, map);
        return res;
    }
    private long traverse(TreeNode root, List<TreeNode> res, Map<Long, int[]> map){
        if(root == null) return 0;
        long left = traverse(root.left, res, map);
        long right = traverse(root.right, res, map);
        long id = ((long)root.val << 32) | (left << 16) | right;
        int[] count = map.get(id);
        if(count == null){
            count = new int[] {map.size() + 1, 1};
            map.put(id, count);
        } else if(++count[1] == 2){
            res.add(root);
        }
        return count[0];
    }
    //approach 1, get serialization format for each subtree's root, use a map to store the count
    //of each serialization of each subtree, if the count is 2, add to res, if larger than 2, we ignore that
    //time: O(N^2), space: O(N^2) 每个节点访问一次 处理每个节点的时间是O(i), i是该节点所有子节点的数量，因为字符串拼接是线性时间。对于根结点，key的长度是O(n)。对于perfect tree (best case)，相当于 T(n) = O(n) + 2T(n/2) = O(nlogn)。worst case 就是每个节点只有左子树, T(n) = O(n) + T(n-1) = O(n^2)。
    // public List<TreeNode> findDuplicateSubtrees(TreeNode root){
    //     List<TreeNode> res = new ArrayList<>();
    //     Map<String, Integer> map = new HashMap<>();
    //     serializeTree(root, res, map);
    //     return res;
    // }
    //it's actually postorder indeed. Even the cur.val is in the beginning of string serial, it doesn't make the algorithm preorder.
    // The order of value in the string serial doesn't really matter, it's the order that we process subtrees or current tree node first that matters.
    // As the string serial is adding to the HashMap and to the result list (this is when we done processing the current tree node, adding cur.val to serial is not) after we processing things for the left and right subtrees,  this is still postorder.
    // private String serializeTree(TreeNode root, List<TreeNode> res, Map<String, Integer> map){
    //     if(root == null) return "*";
    //     String serial = root.val + "," +
    //             serializeTree(root.left, res, map) + "," +
    //             serializeTree(root.right, res, map) + ",";
    //     if(map.getOrDefault(serial, 0) == 1) res.add(root);
    //     map.put(serial, map.getOrDefault(serial, 0) + 1);
    //     return serial;
    // }
}
