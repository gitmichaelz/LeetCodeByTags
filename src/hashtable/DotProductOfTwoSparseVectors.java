package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 *
 *     SparseVector(nums) Initializes the object with the vector nums
 *     dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 *
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 */
public class DotProductOfTwoSparseVectors {
    class SparseVector {

        Map<Integer, Integer> map = new HashMap<>();
        SparseVector(int[] nums) {
            for(int i = 0; i < nums.length; i++){
                if(nums[i] != 0){
                    map.put(i, nums[i]);
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec){
            if(this.map.size() < vec.map.size()){
                return vec.dotProduct(this);//for follow up, if one is sparse one is not.
            }
            int sum = 0;
            for(Integer index : vec.map.keySet()){
                if(map.containsKey(index)){
                    sum += map.get(index) * vec.map.get(index);
                }
            }
            return sum;
        }
    }
}
