package dfs_backtracking_Recursion;
/**
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 *     For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 *
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 */

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public List<String> restoreIpAddresses(String s){
        List<String> res = new ArrayList<>();
        if(s == null || s.length() < 4 || s.length() > 12) return res;
        restore(s, res, 0, 0, new StringBuilder());
        return res;
    }

    private void restore(String s, List<String> res, int count, int idx, StringBuilder sb){
        if(count > 4) return;
        if(count == 4 && idx == s.length()) {
            res.add(sb.toString());
            return;
        }
        int len = sb.length();
        for(int i = 1; i <= 3; i++){
            if(idx + i > s.length()) break;//dont forget this, or it may cast StringIndexOutOfBoundsException
            String section = s.substring(idx, idx + i);
            if(section.startsWith("0") && section.length() > 1 || Integer.valueOf(section) > 255) break;
            if(count == 0){
                sb.append(section);
            }else {
                sb.append('.').append(section);
            }
            restore(s, res, count + 1, idx + i, sb);
            sb.setLength(len);//同一层，恢复原状，尝试下一个可能的分割
        }
    }
}
