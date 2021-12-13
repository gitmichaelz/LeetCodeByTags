package binarySearch;

/**
 * You are given an m x n binary matrix image where 0 represents a white pixel and 1 represents a black pixel.
 *
 * The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.
 *
 * Given two integers x and y that represents the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
 *
 * You must write an algorithm with less than O(mn) runtime complexity
 *
 *
 *
 * Example 1:
 *
 * Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
 * Output: 6
 */
public class SmallestRectangleEnclosingBlackPixels {
    //project the 2d image into a 1d array and use binary search to find the low/high boundaries.
    //O(mlgn + nlgm)
    public int minArea(char[][] image, int x, int y){
        int m = image.length;
        int n = image[0].length;
        int left = findLowBoundry(image, 0, y, 0, m, true);
        int right = findHighBoundry(image, y, n - 1, 0, m, true);
        int top = findLowBoundry(image, 0, x, 0, n, false);
        int bottom = findHighBoundry(image, x, m - 1, 0, n, false);
        return (right - left + 1) * (bottom - top + 1);
    }

    private int findLowBoundry(char[][] image, int i, int j, int top, int bottom, boolean horizontal){
        while(i < j){
            int k = top, mid = i + (j - i)/2;
            if(checkBlackPixel(image, mid, horizontal)) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    private int findHighBoundry(char[][] image, int i, int j, int top, int bottom, boolean horizontal){
        while(i < j){
            int k = top, mid = j - (j - i)/2;
            if(checkBlackPixel(image, mid, horizontal)){
                i = mid;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

    private boolean checkBlackPixel(char[][] image, int x, boolean horizontal){
        if(horizontal){//check if colum x has black pixel
            for(int i = 0; i < image.length; i++){
                if(image[i][x] == '1') return true;
            }
            return false;
        } else {//check if row x has black pixel
            for(int j = 0; j < image[0].length; j++){
                if(image[x][j] == '1') return true;
            }
            return false;
        }
    }


    // private char[][] image;
    // public int minArea(char[][] iImage, int x, int y) {
    //     image = iImage;
    //     int m = image.length, n = image[0].length;
    //     int left = searchColumns(0, y, 0, m, true);
    //     int right = searchColumns(y + 1, n, 0, m, false);
    //     int top = searchRows(0, x, left, right, true);
    //     int bottom = searchRows(x + 1, m, left, right, false);
    //     return (right - left) * (bottom - top);
    // }
    // private int searchColumns(int i, int j, int top, int bottom, boolean opt) {
    //     while (i < j) {
    //         int k = top, mid = (i + j) / 2;//let k = top, 从每一行=top起，扫描所有行
    //         while (k < bottom && image[k][mid] == '0') ++k;
    //         if (k < bottom == opt)
    //             j = mid;
    //         else
    //             i = mid + 1;
    //     }
    //     return i;
    // }
    // private int searchRows(int i, int j, int left, int right, boolean opt) {
    //     while (i < j) {
    //         int k = left, mid = (i + j) / 2;
    //         while (k < right && image[mid][k] == '0') ++k;
    //         if (k < right == opt)
    //             j = mid;
    //         else
    //             i = mid + 1;
    //     }
    //     return i;
    // }
}
