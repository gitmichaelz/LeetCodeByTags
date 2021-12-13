package bfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 *     Every adjacent pair of words differs by a single letter.
 *     Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 *     sk == endWord
 *
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 */
public class WordLadder {
    //这个做法比传统的BFS快，因为这个总是从一个小的集合到大的集合查找，可以减少不必要的搜索，例如当我们中途某个生成的set的size大于endSet,我们可以从endSet往回找。这样总能用最短时间找出最短的距离。当然，下面的传统的BFS也必须掌握。
    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        Set<String> dict = new HashSet(wordList);
        if(!dict.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        return search(beginSet, endSet, dict, 1);
    }

    private int search(Set<String> beginSet, Set<String> endSet, Set<String> dict, int count){
        if(beginSet.isEmpty() || endSet.isEmpty()) return 0;
        count++;
        dict.removeAll(beginSet);//remove already visited words from dict
        Set<String> next = new HashSet<>();
        for(String word : beginSet){
            for(int i = 0; i < word.length(); i++){
                char[] chs = word.toCharArray();
                for(char c = 'a'; c <= 'z'; c++){
                    chs[i] = c;
                    String newWord = new String(chs);
                    if(!dict.contains(newWord)) continue;
                    if(endSet.contains(newWord)) return count;
                    next.add(newWord);
                }
            }
        }
        return next.size() > endSet.size()? search(endSet, next, dict, count) : search(next, endSet, dict, count);
    }


    //BFS
    //for each cur node, we find all its neighbors( which only has 1 different character) by two for loops, for every position of the node,
    //we try 26 different characters, if such neighbor is equal to end, we return len(map.get(curNode) + 1),
    // if the curNode already in map, we skip it, otherwise if its not in map but in wordlist, we enqueue.
    // public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    //     Set<String> set = new HashSet<>(wordList);
    //     if(!set.contains(endWord)) return 0;
    //     Deque<String> queue = new LinkedList<>();
    //     queue.offer(beginWord);
    //     int step = 1;
    //     while(!queue.isEmpty()){
    //         step++;
    //         for(int size = queue.size(); size > 0; size--) {
    //             String cur = queue.poll();
    //             for (int i = 0; i < cur.length(); i++) {
    //                 char[] newStr = cur.toCharArray();
    //                 for (char j = 'a'; j <= 'z'; j++) {
    //                     newStr[i] = j;
    //                     String newWord = new String(newStr);
    //                     if (newWord.equals(endWord)) {
    //                         return step;
    //                     }
    //                     if (set.contains(newWord)) {
    //                         set.remove(newWord);//we already reached newWord at this step, can be removed from set这里要注意，一定要注意，已经访问过的可以删除掉，因为visited word的下一个还可以transform回来，这样会有很多不必要的重复计算，另外，对于某些特殊的test case,"hot, dog, [hot, dog]"会产生TLE
    //                         queue.offer(newWord);
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return 0;//if no such sequence found
    // }
}
