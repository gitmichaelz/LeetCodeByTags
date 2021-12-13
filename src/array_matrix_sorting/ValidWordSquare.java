package array_matrix_sorting;

import java.util.List;

/**
 * Given an array of strings words, return true if it forms a valid word square.
 *
 * A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","bnrt","crmy","dtye"]
 * Output: true
 */
public class ValidWordSquare {
    //4ms  94.33%
    public boolean validWordSquare(List<String> words) {
        // for each word in the list
        for(int i=0;i<words.size();i++) {
            String row = words.get(i);
            String colWord = getColumnWord(words, i);
            //System.out.println(row + " " + colWord);
            if(!row.equals(colWord))
                return false;
        }
        return true;
    }

    // returns word in index'th column
    private String getColumnWord(List<String> words, int index) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<words.size();i++) {       // for each row
            //check if that
            String rowWord = words.get(i);
            if(rowWord.length() >= index+1)
                sb.append(rowWord.charAt(index));
        }
        return sb.toString();
    }
    //10ms  53.9%
    // public boolean validWordSquare(List<String> words) {
    //     if(words == null || words.size() == 0){
    //         return true;
    //     }
    //     int n = words.size();
    //     for(int i=0; i<n; i++){
    //         for(int j=0; j<words.get(i).length(); j++){
    //             if(j >= n || words.get(j).length() <= i || words.get(j).charAt(i) != words.get(i).charAt(j))
    //                 return false;
    //         }
    //     }
    //     return true;
    // }
}
