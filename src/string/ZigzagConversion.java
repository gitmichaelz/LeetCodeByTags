package string;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 */
public class ZigzagConversion {
    //each zig zag has 2 * row - 1 chars
    //for each row, we first add the char in col that goes down. that is sb.append(s.charAt(j)); and j+= size, where size = 2 * row -2
    //for those chars in slope, we we do sp.append(s.charAt(j + size - 2 * i)), where i is row number
    //for first row and last row, we do sb.append(s.charAt(i))
    //time: O(N) space: O(1), sb is not counted becuase it is result set
    public String convert(String s, int numRows) {
        if(s == null || s.length() <= 2 || numRows == 1) return s;
        StringBuilder sb = new StringBuilder();
        int size = 2 * numRows - 2;
        for(int i = 0; i < numRows; i++) {
            for(int j = i; j < s.length(); j += size) {
                sb.append(s.charAt(j));
                if(i != 0 && i != numRows -1 && j + size - 2 * i < s.length()) {//slop, excluding first/last row
                    sb.append(s.charAt(j + size - 2 * i));
                }
            }
        }
        return sb.toString();
    }
}
