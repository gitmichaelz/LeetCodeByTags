package unionFind;
/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 */

import java.util.*;

public class AccountsMerge {
    int[] father;
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        father = new int[accounts.size()];
        for(int i = 0; i < father.length; i++){
            father[i] = i;
        }

        //union
        Map<String, Integer> emails = new HashMap<>();//map email to owner, if there already existing an email, union the owners
        for(int i = 0; i < accounts.size(); i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if(emails.containsKey(email)) {
                    int owner = emails.get(email);
                    int root1 = find(owner);
                    int root2 = find(i);
                    if(root1 != root2) {
                        father[root1] = root2;
                    }
                } else {
                    emails.put(email, i);
                }
            }
        }

        //put all emails belonging to user i and his/her descendants into the treeset
        Map<Integer, TreeSet<String>> users = new HashMap<>();//map user id to all his/her emails
        for(int i = 0; i < accounts.size(); i++) {
            int root = find(i);
            TreeSet set = users.computeIfAbsent(root, k -> new TreeSet<>());
            List<String> list = accounts.get(i);
            set.addAll(list.subList(1, list.size()));
        }

        //result
        List<List<String>> res = new ArrayList<>();
        for(int i : users.keySet()) {
            List<String> emailList = new LinkedList<>(users.get(i));//用linkedList，需要头部插入name
            String name = accounts.get(i).get(0);
            emailList.add(0, name);
            res.add(emailList);
        }
        return res;
    }

    private int find(int i) {
        while(i != father[i]) {
            father[i] = father[father[i]]; //path compression
            i = father[i];
        }
        return i;
    }
}
