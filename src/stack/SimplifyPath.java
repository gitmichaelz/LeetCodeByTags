package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 *     The path starts with a single slash '/'.
 *     Any two directories are separated by a single slash '/'.
 *     The path does not end with a trailing '/'.
 *     The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 *
 * Return the simplified canonical path.
 *
 * Example 1:
 *
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 *
 * Example 2:
 *
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 *
 * Example 3:
 *
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 * Example 4:
 *
 * Input: path = "/a/./b/../../c/"
 * Output: "/c"
 */
public class SimplifyPath {
    //这个题的进阶follow up看这里https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=732189&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
    public String simplifyPath(String path) {
        if(path == null || path.isEmpty()) return path;
        Deque<String> stack = new ArrayDeque<>();
        for(String dir : path.split("/")) {
            if(dir.equals(".") || dir.isEmpty()) continue;
            if(dir.equals("..")) {
                if(!stack.isEmpty()) {//不要忘记判断stack不能为空
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }
        if(stack.isEmpty()) return "/";
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append("/").append(stack.pollLast());//note we are using pollLast() method here to get the bottom of stack
        }
        return sb.toString();
    }
}
