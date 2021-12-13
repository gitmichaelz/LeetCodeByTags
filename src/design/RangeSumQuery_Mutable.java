package design;

/**
 * Given an integer array nums, handle multiple queries of the following types:
 *
 *     Update the value of an element in nums.
 *     Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 *
 * Implement the NumArray class:
 *
 *     NumArray(int[] nums) Initializes the object with the integer array nums.
 *     void update(int index, int val) Updates the value of nums[index] to be val.
 *     int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 *
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 */
public class RangeSumQuery_Mutable {
    class NumArray {//这个题binary indexed tree 和segment tree都要掌握, binary indexed tree稍微快一点
        //https://husterxsp.github.io/2017/06/06/binary-indexed-tree/
        int[] tree;//store partial sum in each node and get total sum by traversing the tree from leaf to root. the tree has a height of logN, 每个节点实际上等于该节点值加上其左子树的值的和。我们建树的方式决定了我们修改和查询的方式。
        int[] nums;
        public NumArray(int[] nums){
            tree = new int[nums.length + 1];//tree的节点从index = 1开始，需要size + 1
            this.nums = nums;
            for(int i = 0; i < nums.length; i++){//坑，这里是i < nums.length
                updateTree(i + 1, nums[i]);
            }
        }
        private void updateTree(int i, int delta){//update tree，这里要update with delta, from position i, go up right and make sure i < tree.length; i += lowbit(i);
            while(i < tree.length){
                tree[i] += delta;
                i += lowbit(i);
            }
        }
        //为什么需要lowbit函数？用来建树时按照一定的规则来找到相应的节点。某个节点的父节点：该节点的index + index二进制形式的最低位的1对应的值
        //Lowbit(x) 为 x 的二进制表达式中最右边的1所对应的值。如88的二进制是101 1000， 所以lowbit(88) = 8(二进制是1000)，实现中用lowbit(x) = x & -x。因为计算机中整数采用补码存储，因此 -x 是 x 按位取反，末尾加1以后的结果。两者按位与之后，前面的变为0，就可以得到lowbit。
        private int lowbit(int i){
            return i & (-i);
        }
        public void update(int i, int val){
            updateTree(i + 1, val - nums[i]);//注意，第i个元素对应tree中的第i + 1个节点
            nums[i] = val;
        }

        public int sumRange(int i, int j) {//nums from i to j, sums from i + 1, j + 1
            return query(j + 1) - query(i);
        }
        private int query(int i){//前i个数的和。从节点i开始，go up left and make sure i > 0; i -= lowbit(i);
            int sum = 0;
            while(i > 0){
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }


    /*
    class SegmentTreeNode{
        int start, end;
        SegmentTreeNode left, right;
        int sum;
        SegmentTreeNode(int start, int end, int sum){
            this.start = start;
            this.end = end;
            left = null;
            right = null;
            this.sum = sum;
        }
    }
    SegmentTreeNode root;
    public NumArray(int[] nums) {
        root = buildSegmentTree(nums, 0, nums.length - 1);
    }
    private SegmentTreeNode buildSegmentTree(int[] nums, int start, int end){
        if(start > end) return null;
        if(start == end){
            return new SegmentTreeNode(start, end, nums[start]);
        }

        int mid = start + (end - start)/2;
        SegmentTreeNode left = buildSegmentTree(nums, start, mid);
        SegmentTreeNode right = buildSegmentTree(nums, mid + 1,end);
        SegmentTreeNode root = new SegmentTreeNode(start, end, left.sum + right.sum);
        root.left = left;
        root.right = right;
        return  root;
    }

    public void update(int i, int val) {
        update(root, i, val);//坑，一定要用separate function, 把root传入，然后操作root.left/root.right
    }
    private void update(SegmentTreeNode root, int i, int val){
        if(root.start == i && root.end == i){
            root.sum = val;
            return;
        }
        int mid = root.start + (root.end - root.start)/2;
        if(i <= mid) {
            update(root.left, i, val);
        }else {
            update(root.right, i, val);
        }
        root.sum = root.left.sum + root.right.sum;
    }

    public int sumRange(int i, int j) {
        return getSumRange(root, i, j);
    }
    private int getSumRange(SegmentTreeNode root, int i, int j){
        if(root.start == i && root.end == j) return root.sum;
        int mid = root.start + (root.end - root.start)/2;
        if(j <= mid){//left part
            return getSumRange(root.left, i, j);
        } else if(i > mid){
            return getSumRange(root.right, i, j);
        } else {
            return getSumRange(root.left, i, mid) + getSumRange(root.right, mid + 1, j);
        }
    }*/

    }
}