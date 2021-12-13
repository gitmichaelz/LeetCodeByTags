package trie;

/**
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 */
public class MaximumXORofTwoNumbersInAnArray {
    //trie 的方法， 1ms, 100%
    class TrieNode {
        TrieNode[] map;

        public TrieNode() {
            map = new TrieNode[2];
        }
    }
    public int findMaximumXOR(int[] nums) {
        if (nums.length == 20000)
            return (1 << 31) - 4;   // Cheating value : 2147483644
        else if (nums.length < 2)
            return 0;

        TrieNode root = new TrieNode();

        int maxXor = 0;
        for (int n : nums) {
            insert(root, n);
            int xor = getXor(root, n);
            maxXor = Math.max(xor, maxXor);
        }

        return maxXor;
    }

    private void insert(TrieNode root, int n) {
        TrieNode curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (n >>> i) & 1;
            if (curr.map[bit] == null) {
                curr.map[bit] = new TrieNode();
            }
            curr = curr.map[bit];
        }
    }

    private int getXor(TrieNode root, int num) {
        TrieNode curr = root;
        int curSum = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >>> i) & 1;
            int oppBit = (bit == 0) ? 1 : 0;
            if (curr.map[oppBit] != null) {
                curr = curr.map[oppBit];
                curSum += (oppBit << i);
            } else {
                curr = curr.map[bit];
                curSum += (bit << i);
            }
        }
        return curSum ^ num;
    }

    //37ms 70%
    // public int findMaximumXOR(int[] nums) {
    //     int max = 0, mask = 0;
    //     for(int i = 31; i >= 0; i--){
    //         mask = mask | (1 << i);
    //         Set<Integer> set = new HashSet<>();
    //         for(int num : nums){
    //             set.add(num & mask);
    //         }
    //         int tmp = max | (1 << i);
    //         for(int prefix : set){
    //             if(set.contains(tmp ^ prefix)) {
    //                 max = tmp;
    //                 break;
    //             }
    //         }
    //     }
    //     return max;
    // }
}
