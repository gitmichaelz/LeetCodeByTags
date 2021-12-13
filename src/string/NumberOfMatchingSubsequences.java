package string;

import java.util.LinkedList;

/**
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 *     For example, "ace" is a subsequence of "abcde".
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 */
public class NumberOfMatchingSubsequences {
    //这个版本最快。 n + m * n  (m = s.length, n = words.length)
    public int numMatchingSubseq(String S, String[] words) {

        LinkedList<Item>[] arr = new LinkedList[26];
        for (int i = 0; i < 26; i++)
            arr[i] = new LinkedList<Item>();

        for (String w : words)
            arr[w.charAt(0) - 'a'].addLast(new Item(w, 0));

        int res = 0;
        for (char c : S.toCharArray()) {
            LinkedList<Item> list = arr[c - 'a'];
            for (int i = 0, len = list.size(); i < len; i++) {
                Item item = list.removeFirst();
                if (item.index == item.word.length() - 1)
                    res++;
                else {
                    item.index++;
                    arr[item.word.charAt(item.index) - 'a'].addLast(item);
                }

            }
        }
        return res;
    }

    class Item {
        String word;
        int index;

        public Item(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
    //binary search  m + n * k * log(m/26)  (k = words[i].length())
//     public int numMatchingSubseq(String s, String[] words){
//         List<Integer>[] indices = new List[26];
//         for(int i = 0; i < s.length(); i++){
//             int pos = s.charAt(i) - 'a';
//             if(indices[pos] == null) indices[pos] = new ArrayList<>();
//             indices[pos].add(i);
//         }
//         int res = 0;
//         for(String word : words){
//             int p = -1;//the index sequence of char in word must be increasing
//             for(char c : word.toCharArray()){
//                 p = findLowerBound(indices[c - 'a'], p);
//                 if(p == -1) break;
//                 p++;
//             }
//             if(p != -1) res++;
//         }
//         return res;
//     }

//     //find the first element in arr that is equal to or greater than m
//     private int findLowerBound(List<Integer> arr, int m){//remember, arr stores the indices of chars
//         if(arr == null) return -1;
//         int low = 0, hi = arr.size() - 1;
//         while(low < hi){
//             int mid = low + (hi - low)/2;
//             if(arr.get(mid) < m){
//                 low = mid + 1;
//             } else {
//                 hi = mid;
//             }
//         }
//         if(low == arr.size() - 1 && arr.get(low) < m) return -1;//means all indices are in front of m;
//         return arr.get(low);
//     }


    //brute force, TLE
//     public int numMatchingSubseq(String s, String[] words) {
//         int res = 0;
//         for(String word : words){
//             if(isSubseq(s.toCharArray(), word.toCharArray())){
//                 res++;
//             }
//         }
//         return res;
//     }

//     private boolean isSubseq(char[] sequences, char[] subsequence){
//         int i = 0, j = 0;
//         while(i < sequences.length && j < subsequence.length){
//             if(sequences[i] == subsequence[j]){
//                 i++;
//                 j++;
//             }else {
//                 i++;
//             }
//         }
//         return j == subsequence.length;
//     }
}
