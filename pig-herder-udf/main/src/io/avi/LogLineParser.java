package io.avi;

import com.gogobot.util.DateExtractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by avitzurel on 6/19/15.
 */
public class LogLineParser {
    private String line;
    private String matchPattern = "(^\\S+) \\[([^\\]]+)\\] (\\S+) (\\S+) \"(\\S*)\\s? (\\S+) (\\S+)\" \\\"((?:\\\\.|[^\"\\\\])*)\\\" (\\S+)?";
    private Matcher matcher;
    final String UNKNOWN = "unknown";

    public LogLineParser() {

    }

    public String getURL(String line) {
        return getIndexFromLineRegex(line, 6);
    }

    public String getLandingPage(String line) {
        return getIndexFromLineRegex(line, 9);
    }

    public String getDate(String line) {
        DateExtractor dateExtractor = new DateExtractor(line);
        return dateExtractor.getDate();
    }

    private String getIndexFromLineRegex(String line, int index) {
        matcher = Pattern.compile(matchPattern).matcher(line);
        String result = matcher.find() ? matcher.group(index) : UNKNOWN;

        if (result == null || result == "") {
            result = UNKNOWN;
        }

        return result;
    }
}