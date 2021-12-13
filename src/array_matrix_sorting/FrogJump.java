package array_matrix_sorting;

public class FrogJump {
    //DP approach
    //dp[i][j] denotes if the frog can make j jumps at stones i
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n + 1];
        dp[0][1] = true;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int diff = stones[i] - stones[j];
                if(diff <= 0 || diff > n - 1) continue;
                if(dp[j][diff]) {
                    dp[i][diff] = true;
                    dp[i][diff + 1] = true;
                    dp[i][diff - 1] = true;
                    if(i == n - 1) return true;
                }
            }
        }
        return false;
    }

    //using stack, much faster than bellow approach
//     public boolean canCross(int[] stones){
//         if(stones == null || stones.length == 0) return true;
//         for(int i = 1; i < stones.length; i++) {
//             if(stones[i] > stones[i - 1] + i) return false;
//         }
//         Stack<Integer> positions = new Stack<>();
//         Stack<Integer> steps = new Stack<>();
//         Set<Integer> set = new HashSet<>();
//         for(int stone : stones){
//             set.add(stone);
//         }
//         int lastStone = stones[stones.length - 1];
//         positions.push(0);
//         steps.push(1);
//         while(!positions.isEmpty()){
//             int pre = positions.pop();
//             int step = steps.pop();
//             for(int i = step - 1; i <= step + 1; i++) {
//                 if(i > 0) {
//                     int reach = pre + i;
//                     if(reach == lastStone) return true;
//                     if(set.contains(reach)){
//                         positions.push(reach);
//                         steps.push(i);
//                     }
//                 }
//             }

//         }
//         return false;
//     }
    //map<Integer, Set<Integer>> store each stone and the steps can be made from that stone
    //<0, [1]>  <1, [1, 2]>  <3, [1, 2, 3]>  <5, [1, 2, 3]>  <6, [1, 2, 3, 4]>  <8, [1, 2, 3, 4]>   <12, [3, 4, 5]>  <17>
//     public boolean canCross(int[] stones) {
//         if(stones == null || stones.length == 0) return true;
//         Map<Integer, Set<Integer>> map = new HashMap<>();//use set instead of list, because set can ensures the element is unique
//         for(int stone: stones) {
//             map.put(stone, new HashSet<>());
//         }
//         map.get(stones[0]).add(1);
//         for(int i = 0; i < stones.length - 1; i++) {
//             for(int k : map.get(stones[i])) {
//                 int reach = stones[i] + k;
//                 if(reach == stones[stones.length - 1]) return true;
//                 if(map.containsKey(reach)) {
//                     map.get(reach).add(k);
//                     map.get(reach).add(k + 1);
//                     if(k > 1) {
//                         map.get(reach).add(k - 1);
//                     }
//                 }
//             }
//         }

//         return false;
//     }
}
