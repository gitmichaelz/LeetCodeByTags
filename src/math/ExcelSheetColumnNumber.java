package math;

/**
 * Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * Example 1:
 *
 * Input: columnTitle = "A"
 * Output: 1
 *
 * Example 2:
 *
 * Input: columnTitle = "AB"
 * Output: 28
 */
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int num = 0;
        for(char c : s.toCharArray()){
            num = num * 26 + (c - 'A' + 1);
        }
        return num;
    }
}
