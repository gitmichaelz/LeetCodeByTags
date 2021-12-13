package math;

/**
 * An axis-aligned rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) is the coordinate of its bottom-left corner, and (x2, y2) is the coordinate of its top-right corner. Its top and bottom edges are parallel to the X-axis, and its left and right edges are parallel to the Y-axis.
 *
 * Two rectangles overlap if the area of their intersection is positive. To be clear, two rectangles that only touch at the corner or edges do not overlap.
 *
 * Given two axis-aligned rectangles rec1 and rec2, return true if they overlap, otherwise return false.
 *
 *
 *
 * Example 1:
 *
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 */
public class RectangleOverlap {
    //https://leetcode.com/problems/rectangle-overlap/discuss/132340/C%2B%2BJavaPython-1-line-Solution-1D-to-2D
    //Before we do it in 2D plane, let's try it in 1D.
    //Given 2 segment (left1, right1), (left2, right2), how can we check whether they overlap?
    //If these two intervals overlap, it should exist an number x,
    //
    //left1 < x < right1 && left2 < x < right2
    //
    //left1 < x < right2 && left2 < x < right1
    //
    //left1 < right2 && left2 < right1  这个结论重要。code根据这个结论来判断
    //
    //This is the sufficient and necessary condition for two segments overlap.

    //For 2D, if two rectangle overlap both on x and y, they overlap in the plane.
    //rec1[x1, y1, x2, y2]  rec2[a1, b1, a2, b2]
    //left1: x1,  right1: x2,   left2: a1,   right2: a2
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if(rec1[0] == rec1[2] || rec1[1] == rec1[3] || rec2[0] == rec2[2] || rec2[1] == rec2[3]) return false;//排除非矩形
        return rec1[0] < rec2[2] && rec2[0] < rec1[2] && rec1[1] < rec2[3] && rec2[1] < rec1[3];
    }
}
