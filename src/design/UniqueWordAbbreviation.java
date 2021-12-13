package design;

import java.util.HashMap;

public class UniqueWordAbbreviation {
    //这题需要注意的一点就是如果dict里有多个词他们的abbr相同，则把abbr 和 空串""存进去，这样如果判断word时，如果存在abbr相同的情况，这两个词肯定不相同。返回false.
    // abbreviation, word
    private HashMap<String, String> map;

    public UniqueWordAbbreviation(String[] dictionary) {
        map = new HashMap();
        for (String word : dictionary) {
            String abbr = getAbbr(word);
            if (map.containsKey(abbr) && !map.get(abbr).equals(word)) {//这里不要忘记判断equals word.
                map.put(abbr, "");//
            } else {
                map.put(abbr, word);
            }
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        return !map.containsKey(abbr) || map.get(abbr).equals(word);
    }

    private String getAbbr(String word) {
        if (word.length() < 3) {
            return word;
        }

        StringBuilder sb = new StringBuilder();

        sb.append(word.charAt(0));
        sb.append(word.length() - 2);
        sb.append(word.charAt(word.length() - 1));

        return sb.toString();
    }
}
