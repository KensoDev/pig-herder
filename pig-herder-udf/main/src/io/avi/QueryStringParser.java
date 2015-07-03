package io.avi;

import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by avitzurel on 6/19/15.
 */
public class QueryStringParser {
    private String url;

    public QueryStringParser(String _url) {
        url = _url;
    }

    public String getQueryStringValue(String queryStringName) {
        String _default = "unknown";

        String regex = String.format("%s=([^&]{1,})", queryStringName);
        Matcher m = Pattern.compile(regex).matcher(url);

        String result = m.find() ? m.group(1) : _default;

        if (result == "" || result == null) {
            result = _default;
        }

        return unescapeString(result);
    }

    public String unescapeString(String value) {
        String decoded = value;
        try {
            decoded = URLDecoder.decode(value, "UTF-8");
        } catch (Exception e) {

        }
        return decoded;
    }
}
