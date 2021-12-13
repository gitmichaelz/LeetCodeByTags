package bfs;

import java.util.*;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 *     Every adjacent pair of words differs by a single letter.
 *     Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 *     sk == endWord
 *
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 */
public class WordLadderII {
    //优化：bidirectional bfs + dfs
//use two set, beginSet and endSet to store the ladders from begin and end word separately. we do the bfs search simutaneously, if beginSet is smaller than endSet, we search from begin to end, if endSet is smaller than beginSet, we do the opposite way. finnally, they meet at some point in the middle, which will reduce the whole searching time.

    boolean found = false;//注意这里我们把她定义在方法外面，供两个方法引用。主方法里用于判断!found时可以提前返回空list，不用再继续下面的dfs！！！！！！！
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        dict.remove(beginWord);

        Map<String, List<String>> map = new HashMap<>();

        bfs(dict, beginSet, endSet, map, false);
        if (!found) return res;//terminate early if not found

        dfs(res, new ArrayList<>(), map, beginWord, endWord);
        return res;
    }

    private void bfs(Set<String> dict, Set<String> beginSet, Set<String> endSet, Map<String, List<String>> map, boolean reverse) {//由于需要建立neighborsMap,要保证ladder的方向一致性，需要判断方向
        if (beginSet.isEmpty()) return;

        dict.removeAll(beginSet);
        Set<String> nextSet = new HashSet<>();//坑一！！！！！！之所以需要新建一个set而不是clear之前的beiginSet,是因为接下来我们要用到beginSet里的节点。
        for (String word : beginSet) {
            for (int i = 0; i < word.length(); i++) {
                char[] arr = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    arr[i] = c;
                    String newWord = String.valueOf(arr);
                    if (!dict.contains(newWord)) continue;
                    nextSet.add(newWord);
                    if (endSet.contains(newWord)) {
                        found = true;
                    }
                    String key = reverse? newWord : word;
                    String value = reverse? word : newWord;
                    List<String> neighbors = map.computeIfAbsent(key, v -> new ArrayList<>());
                    neighbors.add(value);
                }
            }
        }
        if (!found) {
            if (nextSet.size() > endSet.size()) {
                bfs(dict, endSet, nextSet, map, !reverse);
            } else {
                bfs(dict, nextSet, endSet, map, reverse);
            }
        }
    }

    private void dfs(List<List<String>> res, List<String> path, Map<String, List<String>> map, String beginWord, String endWord) {
        path.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (map.get(beginWord) != null) {
            for (String neighbor : map.get(beginWord)) {
                dfs(res, path, map, neighbor, endWord);
                path.remove(path.size() - 1);
            }
        }
    }





    //注意，这个题在bfs阶段，和wordladder I 不太一样。I里面，我们只要找到符合条件的单词，就可以返回长度。但是II里面，我们需要找出所有可能的邻居，
    // 即如果单词cur的邻居是endWord,我们不能马上将endWord从dict里删掉，因为还要判断本层的其他单词的邻居是否也有endWord,因此，我们需要在每层结束后
    // 才将访问过的单词从dict 中 remove 掉。这样我们需要一个visited的set，

    //bfs + dfs
    //bfs find out the shortest paths, and meanwhile building the adjacent list for each node
    //dfs create the paths by the adjacent list
    //We need an unvisited set to store unvisited words, need an visited set to store all all neighbrs of a cur node, and remove them
    //from unvisited set before next we searching level.and once we find a neighbor of cur node equals to endWord, we will check other
    //neighbors in this level, and after doing that, we will be done with searching.
    //the remaining work is to dfs or backtrack the all elligible paths through the adjacent list
//     public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//         List<List<String>> res = new ArrayList<>();
//         Set<String> unvisited = new HashSet<>(wordList);
//         if (!unvisited.contains(endWord)) return res;

//         Set<String> visited = new HashSet<>();
//         Deque<String> queue = new ArrayDeque<>();
//         queue.offer(beginWord);
//         unvisited.remove(beginWord);//坑一！！！！！！！这个一定要移除，因为自己可以生成自己，建立neighbor list时会把自己也放进list里，这样dfs时会无限循环，TLE

//         Map<String, List<String>> neighborsMap = new HashMap<>();
//         boolean found = false;

//         while (!queue.isEmpty()) {
//             for (int size = queue.size(); size > 0; size--) {
//                 String cur = queue.poll();
//                 for (int i = 0; i < cur.length(); i++) {
//                     char[] arr = cur.toCharArray();//注意，写在for里的好处就是不用每次for循环完了归位。
//                     for (char c = 'a'; c <= 'z'; c++) {
//                         arr[i] = c;
//                         String newWord = String.valueOf(arr);
//                         if (!unvisited.contains(newWord)) continue;//我们只需要判断在unvisited里面的，所以一开始unvisited里面不能包含beginword
//                         if (!visited.contains(newWord)) {//坑二！！！！！！！！这个一定要判断，neighbor不能在visited里，因为bfs的本层是指：当前queue里所有的节点，而他们生成的neighbor有可能是相同的，这样的话，queue里会有可能放进两个相同的节点，例如“ted, rex” 的共同邻居是tex,如果不做visited判断的话，我们在bfs的本层中会enqueue两次“tex”，这样tex往后的所有路径都会有两份。会有重复路径。不代表错，但是结果集有重复的。
//                             visited.add(newWord);
//                             queue.offer(newWord);
//                         }
//                         List<String> neighbors = neighborsMap.computeIfAbsent(cur, k -> new ArrayList<>());
//                         neighbors.add(newWord);

//                         if(newWord.equals(endWord)) {
//                             found = true;
//                         }
//                     }//end of char replacement
//                 }//end of string scan
//             }//end of current level
//             if (found) break;
//             unvisited.removeAll(visited);
//             visited.clear();
//         }

//         if (!found) return res;//terminate early if not found.

//         dfs(res, new ArrayList<>(), neighborsMap, beginWord, endWord);

//         return res;
//     }

//     private void dfs(List<List<String>> res, List<String> path, Map<String, List<String>> neighborsMap, String beginWord, String endWord) {
//         path.add(beginWord);
//         if (beginWord.equals(endWord)) {
//             res.add(new ArrayList<>(path));
//             return;
//         }

//         if (neighborsMap.get(beginWord) != null) {//这个坑要记住。并不是路径上的所有点都能到达最后的end(我们当初build map的时候所有可能的邻居都加进去了),所以不一定有下一层，这里需要一个判断。
//             for (String neighbor : neighborsMap.get(beginWord)) {
//                 dfs(res, path, neighborsMap, neighbor, endWord);
//                 path.remove(path.size() - 1);//要在每一次dfs结束后，reset path然后进行下一次递归
//             }
//         }
//     }
}
