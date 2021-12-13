package math;

/**
 * Given an integer array data representing the data, return whether it is a valid UTF-8 encoding.
 *
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 *
 *     For a 1-byte character, the first bit is a 0, followed by its Unicode code.
 *     For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes with the most significant 2 bits being 10.
 *
 * This is how the UTF-8 encoding would work:
 *
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 *
 * Note: The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.
 *
 *
 *
 * Example 1:
 *
 * Input: data = [197,130,1]
 * Output: true
 * Explanation: data represents the octet sequence: 11000101 10000010 00000001.
 * It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
 */
public class UTF_8Validation {
    //   Char. number range  |        UTF-8 octet sequence
    //      (hexadecimal)    |              (binary)
    //   --------------------+---------------------------------------------
    //   0000 0000-0000 007F | 0xxxxxxx
    //   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
    //   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
    //   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
    //
    //Rule 2:
    //Record the count of consecutive of 1.
    //Move the number 5 bit right, if it equals "110" means there is one '1'.
    //Move the number 4 bit right, if it equals "1110" means there are two '1'.
    //...
    //Move the number 7 bit right, if it equals "1" means it is "10000000" which has no meaning, return false.
    //
    //Rule 1:
    //If it is not started with "10", return false;
    public boolean validUtf8(int[] data) {
        int bytesRemaining = 0;
        for(int d : data){
            if(bytesRemaining == 0) {
                if (d >> 5 == 0b110) bytesRemaining = 1;
                else if (d >> 4 == 0b1110) bytesRemaining = 2;
                else if (d >> 3 == 0b11110) bytesRemaining = 3;
                else if (d >> 7 != 0) return false;
            } else {
                if(d >> 6 != 0b10) return false;
                bytesRemaining--;
            }
        }
        return bytesRemaining == 0;//一定要判断是否为0. 例如237（11101101），for循环之后后面bytesRemaining为2，然而后面并没有别的data了，所以此时==0的判断非常有必要
    }
}
