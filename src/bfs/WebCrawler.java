package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the same hostname as startUrl.
 *
 * Return all urls obtained by your web crawler in any order.
 *
 * Your crawler should:
 *
 *     Start from the page: startUrl
 *     Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
 *     Do not crawl the same link twice.
 *     Explore only the links that are under the same hostname as startUrl.
 *
 * As shown in the example url above, the hostname is example.org. For simplicity sake, you may assume all urls use http protocol without any port specified. For example, the urls http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname, while urls http://example.org/test and http://example.com/abc are not under the same hostname.
 *
 * The HtmlParser interface is defined as such:
 *
 * interface HtmlParser {
 *   // Return a list of all urls from a webpage of given url.
 *   public List<String> getUrls(String url);
 * }
 *
 * Below are two examples explaining the functionality of the problem, for custom testing purposes you'll have three variables urls, edges and startUrl. Notice that you will only have access to startUrl in your code, while urls and edges are not directly accessible to you in code.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * urls = [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.google.com",
 *   "http://news.yahoo.com/us"
 * ]
 * edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
 * startUrl = "http://news.yahoo.com/news/topics/"
 * Output: [
 *   "http://news.yahoo.com",
 *   "http://news.yahoo.com/news",
 *   "http://news.yahoo.com/news/topics/",
 *   "http://news.yahoo.com/us"
 * ]
 */

 // This is the HtmlParser's API interface.
 // You should not implement it, or speculate about its implementation
 interface HtmlParser {
     public List<String> getUrls(String url);
 }

public class WebCrawler {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> set = new HashSet<>();
        String hostname = startUrl.split("/")[2];

        set.add(startUrl);

        dfs(startUrl, hostname, set, htmlParser);

        return new ArrayList<String>(set);
    }

    private void dfs(String currentUrl, String hostname, Set<String> visited, HtmlParser hp) {
        for (String url : hp.getUrls(currentUrl)) {
            if (url.contains(hostname) && !visited.contains(url)) {
                visited.add(url);
                dfs(url, hostname, visited, hp);
            }
        }

    }
    //BFS
//     public List<String> crawl(String startUrl, HtmlParser htmlParser) {
//         Set<String> set = new HashSet<>();
//         Queue<String> queue = new LinkedList<>();
//         String hostname = getHostname(startUrl);

//         queue.offer(startUrl);
//         set.add(startUrl);

//         while (!queue.isEmpty()) {
//             String currentUrl = queue.poll();
//             for (String url : htmlParser.getUrls(currentUrl)) {
//                 if (url.contains(hostname) && !set.contains(url)) {
//                     queue.offer(url);
//                     set.add(url);
//                 }
//             }
//         }

//         return new ArrayList<String>(set);
//     }

//     private String getHostname(String Url) {
//         String[] ss = Url.split("/");
//         return ss[2];
//     }
}
