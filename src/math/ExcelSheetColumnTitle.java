package math;

/**
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
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
 *
 *
 * Example 1:
 *
 * Input: columnNumber = 1
 * Output: "A"
 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            sb.append((char)('A' + --n%26));//我们在求某一个字符相对于A的相对位置，应该先自减，比如第三个字符C，相对于A就是2
            n/=26;
        }
        return sb.reverse().toString();//我们是逆序添加的，即先添加的remainder部分，所以我们最后应该reverse
    }
}
