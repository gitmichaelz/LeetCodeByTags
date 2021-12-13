package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
 *
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 *
 * The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
 *
 *     For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 *
 *
 *
 * Example 1:
 *
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 */
public class RemoveSubfoldersFromTheFileSystem {
    class Trie {
        Trie[] children;
        boolean end;//end indicates if folder is a complete parent folder

        public Trie() {
            children = new Trie[27];
        }
    }
    public List<String> removeSubfolders(String[] folders) {
        Arrays.sort(folders, Comparator.comparingInt(a -> a.length()));//按照folder的长度来排序，可以确保parent folder在前面
        List<String> result = new ArrayList<>();
        Trie root = new Trie();
        for (String folder : folders) {
            Trie node = root;
            boolean skip = false;
            for (int i = 0; i < folder.length(); i++) {
                int idx = folder.charAt(i) - 'a';
                if (idx < 0) idx = 26;
                if (node.children[idx] == null) node.children[idx] = new Trie();
                node = node.children[idx];

                if (node.end && folder.charAt(i + 1) == '/') {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;
            node.end = true;
            result.add(folder);
        }
        return result;
    }


    // public List<String> removeSubfolders(String[] folder){
    //     List<String> res = new ArrayList<>();
    //     if(folder.length == 0) return new ArrayList<>();
    //     Arrays.sort(folder);
    //     res.add(folder[0]);
    //     for(int i = 1; i < folder.length; i++){
    //         String parent = res.get(res.size() - 1);
    //         if(!folder[i].startsWith(parent + "/")) {
    //             res.add(folder[i]);
    //         }
    //     }
    //     return res;
    // }
}
