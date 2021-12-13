package greedy;

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 *     Each child must have at least one candy.
 *     Children with a higher rating get more candies than their neighbors.
 *
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 *
 *
 * Example 1:
 *
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 */
public class Candy {
    /**
     *
     分析：这道题用到的思路和Trapping Rain Water是一样的，用动态规划。基本思路就是进行两次扫描，一次从左往右，一次从右往左。第一次扫描的时候
     维护对于每一个小孩左边所需要最少的糖果数量，存入数组对应元素中，第二次扫描的时候维护右边所需的最少糖果数，并且比较将左边和右边大的糖果数量存
     入结果数组对应元素中。这样两遍扫描之后就可以得到每一个所需要的最最少糖果量，从而累加得出结果。方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。
     空间上需要一个长度为n的数组，复杂度是O(n)
     */
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0)
            return 0;
        int[] nums = new int[ratings.length];
        nums[0] = 1;
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] > ratings[i - 1])
                nums[i] = nums[i - 1] + 1;
            else
                nums[i] = 1;
        }
        int res = nums[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--){
            if(ratings[i] > ratings[i + 1]){
                nums[i] = Math.max(nums[i], nums[i + 1] + 1);
            }
            res += nums[i];
        }
        return res;
    }
}
