package topologicalSort;

import java.util.*;

/**
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 *
 * Example 2:
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 */
public class AlienDictionary {
    //这题就是个拓扑排序，找出相邻单词第一个不同的字符，即是字符的顺序，然后以此构造图和入度表。最后构造res
    public String alienOrder(String[] words){
        if(words == null || words.length == 0) return "";
        Set<Character>[] children = new Set[26];
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);

        int uniq_chars = 0;//用来比较最后生成的字符串的长度和uniq_c的数量的大小
        for(String word : words){
            for(char c : word.toCharArray()){
                if(children[c - 'a'] == null){
                    uniq_chars++;
                    children[c - 'a'] = new HashSet<>();
                    indegree[c - 'a'] = 0;
                }
            }
        }

        for(int i = 0; i < words.length - 1; i++){
            String cur = words[i], next = words[i + 1];
            int len = Math.min(cur.length(), next.length());
            for(int j = 0; j < len; j++){
                char cur_c = cur.charAt(j);
                char next_c = next.charAt(j);
                if(cur_c != next_c){//注意这里，这俩if要分开写，否则会出错，只要cur_c != next_c 则要break
                    if(!children[cur_c - 'a'].contains(next_c)){//这个判断主要是准确计算入度。否则可以每次直接 children[cur_c].add(next_c);
                        children[cur_c - 'a'].add(next_c);
                        indegree[next_c - 'a']++;
                    }
                    break;//退出当前循环，比较下一组单词。
                }
                if(j == len - 1 && cur.length() > next.length()) return "";//特殊情况。to pass "abc", "ab" case,
            }
        }

        Deque<Character> queue = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.offer((char)('a' + i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            char cur = queue.poll();
            sb.append(cur);
            for(char child : children[cur - 'a']){
                if(--indegree[child - 'a'] == 0){
                    queue.offer(child);
                }
            }
        }
        return sb.length() == uniq_chars? sb.toString() : "";
    }
}
