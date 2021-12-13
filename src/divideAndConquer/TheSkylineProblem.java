package divideAndConquer;

import java.util.LinkedList;
import java.util.List;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 *     lefti is the x coordinate of the left edge of the ith building.
 *     righti is the x coordinate of the right edge of the ith building.
 *     heighti is the height of the ith building.
 *
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 *
 *
 * Example 1:
 *
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 */
public class TheSkylineProblem {
    //注意，这题的输入已经按照x排好序了
    //经典的divide and conquer, 类似于merge sort.先split,再merge, merge的时候注意，要加入的点的height必须是leftH, rightH最大的那个。
    //this problem has the best running time when using divide and conque
    //idea: first we divide the array into smaller subarray and merge them
    //the merge process is as following:
    //Assume we already have two sub skylines, left and right
    //(base case is we only have one rectangle, we add (x1, h) and (x2, 0))
    //first we compare the x cordinates of first points in each part,
    //and pick the smaller one, also we get it's height
    //then we need to maintain the max height
    //a little trick by maintaining the max height, say we have leftH for left height and rightH for right height, the values of leftH/rightH is either a real height or a 0. say if we pick one point from left, and we got leftH, we also need to compare the previous rightH
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new LinkedList<>();
        if(buildings == null || buildings.length == 0) return res;
        return merge(buildings, 0, buildings.length - 1);
    }
    //因为要对list里的元素（元素是点,都是一个list(x, y)）进行移除操作，为提高效率，用linkedlist
    public LinkedList<List<Integer>> merge(int[][] buildings, int low, int hi) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (low == hi) {//only one rectangle left, add the left edge and right edge separately
            //note: for left edge, we add the (x1, h), for right edge, we add (x2, 0)

            LinkedList<Integer> leftEdge = new LinkedList<>();
            leftEdge.offer(buildings[low][0]);
            leftEdge.offer(buildings[low][2]);
            LinkedList<Integer> rightEdge = new LinkedList<>();
            rightEdge.offer(buildings[low][1]);
            rightEdge.offer(0);
            res.offer(leftEdge);
            res.offer(rightEdge);
            return res;
        }

        int mid = low + (hi - low)/2;
        LinkedList<List<Integer>> left = merge(buildings, low, mid);
        LinkedList<List<Integer>> right = merge(buildings, mid + 1, hi);

        //Why initiallzed as 0? because we assume all the rectangels/points smaller than left is already added
        //since all added points's last height is 0, so let's make leftH = 0, rightH = 0
        int leftH = 0;
        int rightH = 0;
        while(!left.isEmpty() || !right.isEmpty()) {
            //why x1 = Long.MAX_VALUE when left is empty? because if empty, we assume left has already completed
            //we just need to consider the right part,which has an valid x cordinate.
            //and we use long type in case one of the skylines has x which value is equal to Integer.MAX_VALUE
            long x1 = left.isEmpty()? Long.MAX_VALUE : left.peekFirst().get(0);
            long x2 = right.isEmpty()? Long.MAX_VALUE : right.peekFirst().get(0);
            int x = 0;//the x cordinate to be added
            if(x1 < x2) {//we add the left rectangle if first edge/strip is smaller than right edge/strip
                List<Integer> tmp = left.pollFirst();
                x = tmp.get(0);
                leftH = tmp.get(1);
            } else if (x2 < x1) {
                List<Integer> tmp = right.pollFirst();
                x = tmp.get(0);
                rightH = tmp.get(1);
            } else {//如果x相同，那么则要同时移除这两个点
                x = left.peekFirst().get(0);
                leftH = left.pollFirst().get(1);
                rightH = right.pollFirst().get(1);
            }
            int h = Math.max(leftH, rightH);// we make sure get the max height above certain point
            if(res.isEmpty() || h != res.peekLast().get(1)) {//we only add the point when it's height is not equle to pre's height
                LinkedList<Integer> list = new LinkedList<>();
                list.offer(x);
                list.offer(h);
                res.offer(list);
            }
        }
        return res;
    }
}
