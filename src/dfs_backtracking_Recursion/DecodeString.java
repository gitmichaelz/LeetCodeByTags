package dfs_backtracking_Recursion;

public class DecodeString {
    //dfs,这个方法非常会， 0ms, beat 100%, 重点掌握
    int idx = 0;//use global variable to track where we are at in the String s during dfs
    public String decodeString(String s) {
        if(s == null || s.isEmpty()) return s;
        int num = 0;
        StringBuilder sb = new StringBuilder();
        for(; idx < s.length(); idx++){
            char c = s.charAt(idx);
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            } else if(c == '['){
                idx++;//坑，不要忘记先++， to skip '['
                String sub = decodeString(s);
                while(num > 0){
                    sb.append(sub);
                    num--;
                }
            } else if(c == ']'){//
                return sb.toString();
            } else {//char is on the same layer
                sb.append(c);
            }
        }
        return sb.toString();//字符串并不是以']'结尾  例如s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
    }
}
