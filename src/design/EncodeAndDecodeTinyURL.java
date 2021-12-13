package design;

import java.util.HashMap;
import java.util.Map;

public class EncodeAndDecodeTinyURL {
    private final String BASE_HOST = "http://tinyurl.com/";
    private final String SEED = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; //62 chars= 26+26+10
    private final int BASE_HOST_LEN= BASE_HOST.length();
    private final int SEED_LEN= SEED.length();
    private Map<String, String> keyToUrl = new HashMap<>();
    private Map<String, String> urlToKey = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (urlToKey.containsKey(longUrl)) {
            return BASE_HOST + urlToKey.get(longUrl); //do not waste space and time to encode same URL
        }
        String key = null;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int r = (int)(Math.random() * SEED_LEN);
                sb.append(SEED.charAt(r));
            }
            key = sb.toString();
        } while (keyToUrl.containsKey(key));

        keyToUrl.put(key, longUrl);
        urlToKey.put(longUrl, key);
        return BASE_HOST + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return keyToUrl.get(shortUrl.substring(BASE_HOST_LEN));
    }
}
