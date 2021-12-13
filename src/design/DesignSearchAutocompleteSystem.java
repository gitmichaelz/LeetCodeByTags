package design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 *
 * You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.
 *
 * Here are the specific rules:
 *
 *     The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 *     The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
 *     If less than 3 hot sentences exist, return as many as you can.
 *     When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 */
public class DesignSearchAutocompleteSystem {
    class AutocompleteSystem {
        class TrieNode {
            TrieNode[] children;
            String s;
            int times;
            List<TrieNode> hot;

            public TrieNode() {
                children = new TrieNode[128];
                s = null;
                times = 0;
                hot = new ArrayList<>();
            }

            public void update(TrieNode node) {
                if(!this.hot.contains(node)) {
                    this.hot.add(node);
                }
                Collections.sort(hot, (a, b) -> a.times == b.times ? a.s.compareTo(b.s) : b.times - a.times);

                if (hot.size() > 3) {
                    hot.remove(hot.size() - 1);
                }
            }
        }

        TrieNode root;
        TrieNode cur;
        StringBuilder sb;

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            cur = root;
            sb = new StringBuilder();

            for (int i = 0; i < times.length; i++) {
                add(sentences[i], times[i]);
            }
        }

        private void add(String sentence, int times) {
            TrieNode temp = root;
            List<TrieNode> visited = new ArrayList<>();

            for (char c : sentence.toCharArray()) {
                if (temp.children[c] == null) {
                    temp.children[c] = new TrieNode();
                }
                temp = temp.children[c];
                visited.add(temp);
            }

            temp.s = sentence;
            temp.times += times;

            for (TrieNode node : visited) {
                node.update(temp);
            }
        }

        public List<String> input(char c) {
            //三种情况，1. 正在输入， 2 输入完成，新词遇到#， 3.输入完成Node里已经有这个词了
            List<String> res = new ArrayList<>();
            if (c == '#') {
                add(sb.toString(), 1);
                sb = new StringBuilder();
                cur = root;
                return res;
            } //输入完成，更新

            sb.append(c);

            if (cur != null) {
                cur = cur.children[c];
            }
            if (cur == null) {
                return res;
            }

            for (TrieNode node : cur.hot) {
                res.add(node.s);
            }

            return res;
        }
    }
}
