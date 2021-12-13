package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 */
public class SearchSuggestionsSystem {
    /**
     Intuition

     In a sorted list of words,
     for any word A[i],
     all its sugested words must following this word in the list.

     For example, if A[i] is a prefix of A[j],
     A[i] must be the prefix of A[i + 1], A[i + 2], ..., A[j]

     Explanation

     With this observation,
     we can binary search the position of each prefix of search word,
     and check if the next 3 words is a valid suggestion.

     Complexity

     Time O(NlogN) for sorting
     Space O(logN) for quick sort.

     Time O(logN) for each query
     Space O(query) for each query
     where I take word operation as O(1)

     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        String key = "";
        for (char c : searchWord.toCharArray()) {
            key += c;
            String ceiling = map.ceilingKey(key);//大于等于key中最小的，在本题中即sugestion list中的起始word,
            String floor = map.floorKey(key + "~");//小于等于"key~"中最大的，即suggestion list中的最后一个word.
            if (ceiling == null || floor == null) break;
            res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
        }

        while (res.size() < searchWord.length()) res.add(new ArrayList<>());
        return res;
    }




    //time: O(NlogN) for sorting, N is number of products, O(Sum(len(product[i]))) for building trie
    //query: O(Len(searchWord))
    //space: O(Sum(len(product[i])))
    //这种只是常规做法，缺点是建树比较花费时间，而且需要额外空间来建树，如果我们的searcWord有很多的话，这种做法比较推荐，但本题只有一个searchWord，所以还是要找其他更优的方法，比如二分。
    // class Trie {
    //     Trie[] sub = new Trie[26];
    //     List<String> suggestion = new ArrayList<>();
    // }
    // public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    //     Trie root = new Trie();
    //     Arrays.sort(products);
    //     for (String p : products) { // build Trie.
    //         Trie t = root;
    //         for (char c : p.toCharArray()) { // insert current product into Trie.
    //             int idx = c - 'a';
    //             if (t.sub[idx] == null)
    //                 t.sub[idx] = new Trie();
    //             t = t.sub[idx];
    //             if (t.suggestion.size() < 3) // maintain 3 lexicographically minimum strings.
    //                 t.suggestion.add(p); // put products with same prefix into suggestion list.
    //         }
    //     }
    //     List<List<String>> ans = new ArrayList<>();
    //     for (char c : searchWord.toCharArray()) { // search product.
    //         if (root != null) // if current Trie is NOT null.
    //             root = root.sub[c - 'a'];
    //         ans.add(root == null ? new ArrayList<>() : root.suggestion); // add it if there exist products with current prefix.
    //     }
    //     return ans;
    // }
}
