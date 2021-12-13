package dp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class SlidingWindowMaximum {
    /*DP much faster  O(N)
    https://leetcode.com/problems/sliding-window-maximum/solution/
    The algorithm is quite straightforward :

    Iterate along the array in the direction left->right and build an array left.

    Iterate along the array in the direction right->left and build an array right.

    Build an output array as max(right[i], left[i + k - 1]) for i in range (0, n - k + 1).
    */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] left = new int[len], right = new int [len];
        for(int i = 0; i < len; i++) {
            if(i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
        }
        right[len - 1] = nums[len - 1];
        for(int j = len - 2; j >= 0; j--) {//注意这里是len - 2开始
            if(j % k == k - 1) {//block end
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }
        int[] res = new int[len - k + 1];
        for(int i = 0; i <= len - k; i++) {
            res[i] = Math.max(right[i], left[i + k - 1]);
        }
        return res;
    }







    //思路，单调队列，从头到尾单调递减。即遇到一个数，先把队列尾部小于该数的元素都remove掉，然后将该数入队列。
    //为了确保队列里的元素在window size k以内，我们可以让队列存数组的坐标，这样可以通过i - q.peek() + 1 > k 来判断是否超过了window size
    //monotonic queue
    //use a queue as a window to store the index of elements which in the range size k
    //when we meet an element nums[i], we remove all elements(actually their indices) which are less than or equal to nums[i] from queue
    //it is guaranteed that the elements value in queue is descending, after that, we push nums[i] into queue
    //once i - queue.getFirst + 1 > k, means the number of elements exceed the window size k, so we need to remove the first element
    //then if i + 1 >= k, we got our res[i] = nums[queue.getFirst()]
    public int[] maxSlidingWindowMonotonicQueue(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();//to store the index of elements
        for(int i = 0; i < nums.length; i++){
            while(!queue.isEmpty() && nums[i] >= nums[queue.getLast()]){//remove smaller numbers than nums[i] in range k
                queue.removeLast();//从后面删，从而保证前面（如果有的话）都是大于nums[i]的;进而推算出，queue里存的元素的角标所代表的元素，从前往后是递减的，
            }//(接上面comment),即queue里头部存的是最大的元素的index
            queue.offer(i);//add cur element's index to the tail of queue
            if(i - queue.peek() + 1 > k){//remove numbers out of range k
                queue.poll();
            }
            if(i + 1 >= k){
                res[i + 1 - k] = nums[queue.peek()];//根据上面的分析，queue里第一个元素是最大的
            }
        }
        return res;
    }
}
