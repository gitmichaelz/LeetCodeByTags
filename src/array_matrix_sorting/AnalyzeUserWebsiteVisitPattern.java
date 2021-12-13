package array_matrix_sorting;

import java.util.*;

/**
 * You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].
 *
 * A pattern is a list of three websites (not necessarily distinct).
 *
 *     For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
 *
 * The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.
 *
 *     For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
 *     Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
 *     Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
 *
 * Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.
 *
 *
 *
 * Example 1:
 *
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 */
public class AnalyzeUserWebsiteVisitPattern {
    class Visit {
        String username;
        int timestamp;
        String website;
        Visit(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Visit> visitList = new ArrayList<>();
        int n = username.length;
        for(int i = 0; i < n; i++) {
            visitList.add(new Visit(username[i], timestamp[i], website[i]));
        }
        //sort visits by timestamp
        Collections.sort(visitList, Comparator.comparingInt(v -> v.timestamp));
        Map<String, List<String>> userWebsiteMap = new HashMap<>();
        for(Visit v : visitList) {
            List<String> websites = userWebsiteMap.computeIfAbsent(v.username, a -> new ArrayList<>());
            websites.add(v.website);//sorted by timestamp for specific user
        }
        //get each 3_sequence and their frequency
        Map<String, Integer> visitCount = new HashMap<>();
        String mostFrequent = "";
        for(List<String> websites : userWebsiteMap.values()) {
            if(websites.size() < 3) continue;
            Set<String> sequences = get3_Sequences(websites);
            for(String s : sequences) {
                visitCount.put(s, visitCount.getOrDefault(s, 0) + 1);
                if(mostFrequent.equals("") || visitCount.get(mostFrequent) < visitCount.get(s) ||
                        (visitCount.get(mostFrequent) == visitCount.get(s) && mostFrequent.compareTo(s) > 0)) {
                    mostFrequent = s;
                }
            }
        }

        String[] mostFrequentSequence = mostFrequent.split(" ");
        return Arrays.asList(mostFrequentSequence);

    }
    //use a set to avoid duplicated 3 sequences for a specific user
    private Set<String> get3_Sequences(List<String> list) {
        Set<String> res = new HashSet<>();
        for(int i = 0; i < list.size() - 2; i++) {
            for(int j = i + 1; j < list.size() - 1; j++) {
                for(int k = j + 1; k < list.size(); k++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(list.get(i)).append(" ").append(list.get(j)).append(" ").append((list.get(k)));
                    res.add(sb.toString());
                }
            }
        }
        return res;
    }
}
