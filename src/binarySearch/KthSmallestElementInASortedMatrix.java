package binarySearch;

/**
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 */
public class KthSmallestElementInASortedMatrix {
    //这题最快的还是二分搜索
    //The key point for any binary search is to figure out the "Search Space". For me, I think there are two kind of "Search Space" --
    // index and range(the range from the smallest number to the biggest number). Most usually, when the array is sorted in one direction,
    // we can use index as "search space", when the array is unsorted and we are going to find a specific number, we can use "range".
    //注意该题因为不可能根据index来进行The reason why we did not use index as "search space" for this problem is the matrix
    // is sorted in two directions, we can not find a linear way to map the number and its index.
    //找下界问题，找出第一个满足<= mid(low + hi)/2，同时他处在第K个位置
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while(lo < hi){
            int count = 0, j = n - 1;
            int mid = lo + (hi - lo)/2;
            for(int i = 0; i < n; i++){
                while(j >= 0 && matrix[i][j] > mid) {//不要忘记j >= 0
                    j--;
                }
                count += (j + 1);
            }
            if(count < k){
                lo = mid + 1;
            } else {//if count >= k, 我们无限逼近lo, 要让hi = mid
                hi = mid;
            }
        }
        return lo;
    }



    /*
    //这题类似373
    //Solution 1 : Heap
    //Here is the step of my solution:
    //
    //    Build a minHeap of elements from the first row.
    //    Do the following operations k-1 times :
    //    Every time when you poll out the root(Top Element in Heap), you need to know the row number and column number of that element
    //    (so we can create a tuple class here), replace that root with the next element from the same column.
    class Triple{
        int row;
        int col;
        int val;
        Triple(int row, int col, int val){
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Triple> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for(int j = 0; j < n; j++){
            pq.offer(new Triple(0, j, matrix[0][j]));
        }
        while(--k > 0){//iterate k - 1 times, so we use --k > 0
            Triple cur = pq.poll();
            if(cur.row == n - 1) continue;
            pq.offer(new Triple(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
        }
        return pq.poll().val;
    }
    */
}
