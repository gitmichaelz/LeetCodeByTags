package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth
 * characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra
 * spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not
 * divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified and no extra space is inserted between words.
 *
 * Note:
 *
 *     A word is defined as a character sequence consisting of non-space characters only.
 *     Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 *     The input array words contains at least one word.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 */
public class TextJustification {
    //1. first we need to calculate the number of words for each row
    //2. calculate the space ' ' needed for each row
    //3. note the last line of text, it just left justified. means only one single space between words, and filled the remaining part of the row with spaces.
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        for(int start = 0; start < words.length; ) {
            int end = findEndIndex(words, start, maxWidth);
            res.add(justifyLine(words, start, end, maxWidth));
            start = end + 1;
        }
        return res;
    }
    //对于每一行，先算出第一个单词的width, 然后剩下的就是判断+空格+下一个单词长度是否满足条件
    private int findEndIndex(String[] words, int start, int maxWidth) {
        int width = words[start++].length();
        while(start < words.length && width + 1 + words[start].length() <= maxWidth) {//dont forget <=
            width += (1 + words[start++].length());
        }
        return start - 1;//-1 because when breaking while loop, start is illegal, so need to -1
    }
    //需要注意三点。1. lastline对spaceForSlot的影响 2.lastline对remainder的影响 3.lastline对最后一行后面space的影响
    private String justifyLine(String[] words, int start, int end, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        if(start == end) {
            return sb.append(words[start]).append(padSpaces(maxWidth - words[start].length())).toString();
        }
        boolean lastLine = end == words.length - 1;
        int numOfSpaceSlots = end - start;
        int totalSpacesLength = maxWidth - wordsLength(words, start, end);
        String spaceForSlot = lastLine? " " : padSpaces(totalSpacesLength / numOfSpaceSlots);
        int remainder = lastLine? 0 : totalSpacesLength % numOfSpaceSlots;
        while(start <= end) {
            sb.append(words[start]).append(start == end ? "" : spaceForSlot).append(remainder-- > 0? " " : "");
            start++;
        }
        return lastLine? sb.append(padSpaces(maxWidth - sb.length())).toString() : sb.toString();//返回时别忘判断是不是lastline
    }

    private int wordsLength(String[] words, int start, int end) {
        int len = 0;
        while(start <= end) {
            len += words[start++].length();
        }
        return len;
    }

    private String padSpaces(int len) {
        return String.valueOf(new char[len]).replace('\0', ' '); //'\0'是字符'0'， 不是数字0
    }
}
