package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 */
public class AllNodesDistanceKInBinaryTree {
    //这是个非常好的问题。考察的非常全面。三种方法都要掌握。
    //approach 3>
    //same idea, no map, when we find target, we will find nodes with the distance K from target
    //we traverse to tree to compute each node's distance to target, (the nodes are along the path from root to target)
    //once we find the target, we can 1> start from there to search from the target's descendant's  2> when we return the distance bottom up to
    //parent and once we get left >=0, we know how to search from cur node's right subtree, similar to the other case(right >= 0, we know how to
    // search from cur node's left subtree)
    List<Integer> res;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        res = new ArrayList<>();
        getDistance(root, target, K);
        return res;
    }
    private int getDistance(TreeNode root, TreeNode target, int K) {
        if(root == null) return -1;
        if(root.val == target.val) {
            collect(root, 0, K);
            return 0;
        }
        int left = getDistance(root.left, target, K);
        int right = getDistance(root.right, target, K);
        if(left >= 0) {//cur node is in the path from root to target
            if(left == K - 1) {//means cur node has distance K from target
                res.add(root.val);
            } else if(left < K - 1){//if left >= K, there is no need to search further
                collect(root.right, left + 2, K);//if left child -> root -> right child, so right child is (left + 2) distance from target
            }
            return left + 1;
        }
        if(right >= 0) {
            if(right == K - 1) {
                res.add(root.val);
            } else if(right < K - 1) {
                collect(root.left, right + 2, K);
            }
            return right + 1;
        }
        return -1;
    }

    private void collect(TreeNode root, int distance, int K){
        if(root == null) return;
        if(distance == K) {
            res.add(root.val);
        }else {
            collect(root.left, distance + 1, K);
            collect(root.right, distance + 1, K);
        }
    }



    //2> dfs recursion
    //first we need to traverse the tree to find target, from the path to target, we use a Map<Integer, Integer> to store all the nodes along the path
    //and their distances to target.
    //then we need to dfs the tree, once the cur node is stored in the map, its distance to target can be retrieved by map.get(curNode)
    //otherwise its distance to target is distance (parent to target) + 1,
//     public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
//         List<Integer> res = new ArrayList<>();
//         if(root == null || K < 0) return res;
//         Map<TreeNode, Integer> map = new HashMap<>();
//         int distance = findTarget(root, target, map);
//         if(distance == -1) return res;//dont find target
//         traverse(root, map, res, 0, K);
//         return res;
//     }
//     private void traverse(TreeNode root, Map<TreeNode, Integer> map, List<Integer> res, int depth, int K){
//         if(root == null) return;
//         if(map.containsKey(root)) {
//             depth = map.get(root);//if we stored the cur node's depth, we use the stored depth
//         }
//         if(depth == K){
//             res.add(root.val);
//         }
//         traverse(root.left, map, res, depth + 1, K);
//         traverse(root.right, map, res, depth + 1, K);
//     }

//     //since we need the distance from some node to target, so return value must be this information
//     //return the distance from cur node to target, cur node must be in the path from root to target
//     private int findTarget(TreeNode root, TreeNode target, Map<TreeNode, Integer> map){
//         if(root == null) return -1;
//         if(root.val == target.val) {
//             map.put(root, 0);
//             return 0;
//         }
//         int left = findTarget(root.left, target, map);
//         int right = findTarget(root.right, target, map);
//         if(left >= 0) {//cur node is on the path  from root to target
//             map.put(root, left + 1);
//             return left + 1;
//         }
//         if(right >= 0) {
//             map.put(root, right + 1);
//             return right + 1;
//         }
//         return -1;
//     }










    //1> build graph + bfs, very typical and standard
//     Map<TreeNode, List<TreeNode>> map = new HashMap<>();
//     public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
//         List<Integer> res = new ArrayList<>();
//         if(root == null || K < 0) return res;
//         buildMap(root, null);
//         if(!map.containsKey(target)) return res;
//         Deque<TreeNode> queue = new LinkedList<>();
//         Set<TreeNode> visited = new HashSet<>();
//         queue.offer(target);//start from target!!! not root
//         visited.add(target);
//         while(K >= 0 && !queue.isEmpty()) {
//             int size = queue.size();
//             if(K == 0) {
//                 for(int i = 0; i < size; i++) {
//                     res.add(queue.poll().val);
//                 }
//                 return res;
//             }
//             for(int i = 0; i < size; i++) {
//                 root = queue.poll();
//                 for(TreeNode next: map.get(root)) {
//                     if(visited.contains(next)) continue;//因为你中有我。我中有你，所以需要skip 掉访问过得
//                     queue.offer(next);
//                     visited.add(next);
//                 }
//             }
//             K--;
//         }
//         return res;
//     }

//     private void buildMap(TreeNode child, TreeNode parent) {
//         if(child == null) return;
//         map.put(child, new ArrayList<>());
//         if(parent != null) {
//             map.get(child).add(parent);
//             map.get(parent).add(child);
//         }
//         buildMap(child.left, child);
//         buildMap(child.right, child);

//     }
}
