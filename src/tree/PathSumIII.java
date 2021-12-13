package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 *
 *
 * Example 1:
 *
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 */
public class PathSumIII {
    //https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-O(n)-java-Prefix-sum-method
    //read kekezi's post under this discussion

    //a more efficient way is to use a hashmap
    public int pathSum(TreeNode root, int sum){
        if(root == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);//注意，这个初始化的意义在于，如果root的val = sum，则说明preSum - Sum = 0，即根为路径的一个解。所以map.put(0, 1)
        return getPath(map, root, sum, 0);
    }
    //we calculate the preSum of all nodes from root down to leaf, and put the preSum and frequency in a map.
    // if there exists preSum - target, that means there must be node from which till cur node, the sum is target.
    //then we need to recursively calculate its left/right substree and sum them up.
    //at last, dont forget to restore the presum in the map since we will be switching to other path
    private int getPath(Map<Integer, Integer> map, TreeNode root, int target, int preSum){
        if(root == null) return 0;
        preSum += root.val;
        int pathToCur = map.getOrDefault(preSum - target, 0);//number of path ending with cur Node that sums to target
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        int res = pathToCur + getPath(map, root.left, target, preSum) + getPath(map, root.right, target, preSum);
        map.put(preSum, map.get(preSum) - 1);//restore the frequency of preSum when backtracking,we are switching to other path
        return res;
    }
    //Each time find all the path start from current node
    //Then move start node to the child and repeat.
    //Time Complexity should be O(N^2), N + N + ...+ N
    //这个函数的作用是求root为顶点的子树里面的符合条件所有的路径的数目,即当前点开始或者从他的左右孩子开始。
    // public int pathSum(TreeNode root, int sum) {
    //     if(root == null) return 0;
    //     return pathSumFromCurNode(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    // }
    // //这个函数的作用是，规定了路径的起始位置，即以此为开始点，往下的路径数目。
    // private int pathSumFromCurNode(TreeNode root, int sum){
    //     if(root == null) return 0;
    //     int count = 0;
    //     if(root.val == sum) count++;
    //     count += pathSumFromCurNode(root.left, sum - root.val);
    //     count += pathSumFromCurNode(root.right, sum - root.val);
    //     return count;
    // }
}
