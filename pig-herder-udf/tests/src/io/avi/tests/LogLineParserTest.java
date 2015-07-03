package io.avi.tests;

import io.avi.LogLineParser;
import org.junit.Test;

/**
 * Created by avitzurel on 6/20/15.
 */
public class LogLineParserTest {

    @Test
    public void testUrl(){
        String line = "192.210.208.87 [16/Jun/2015:04:06:25 +0000] http www.gogobot.com \"GET /hotel-valamar-sanfior-rabac-hotel HTTP/1.1\" \"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.0.1) Gecko/20060111 Firefox/1.5.0.1\"  200 53555 407 miss\n";
        String expected = "/hotel-valamar-sanfior-rabac-hotel";

        LogLineParser logLineParser = new LogLineParser();
        org.junit.Assert.assertEquals(expected, logLineParser.getURL(line));
    }

    @Test
    public void TestLandingPageUnknown() {
        String line = "192.210.208.87 [16/Jun/2015:04:06:25 +0000] http www.gogobot.com \"GET /hotel-valamar-sanfior-rabac-hotel HTTP/1.1\" \"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.0.1) Gecko/20060111 Firefox/1.5.0.1\"  200 53555 407 miss\n";
        String expected = "unknown";

        LogLineParser logLineParser = new LogLineParser();
        org.junit.Assert.assertEquals(expected, logLineParser.getLandingPage(line));
    }

    @Test
    public void testLandingPage() {
        String line = "172.56.33.37 [19/Jun/2015:22:20:12 +0000] http static.gbot.me \"GET /some-url HTTP/1.1\" \"Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) GSA/5.2.43972 Mobile/12F70 Safari/600.1.4\" http://www.gogobot.com/space-needle-seattle-attraction--nearby--attractions 200 403 11 miss";
        String expected = "http://www.gogobot.com/space-needle-seattle-attraction--nearby--attractions";
        LogLineParser logLineParser = new LogLineParser();
        org.junit.Assert.assertEquals(expected, logLineParser.getLandingPage(line));
    }


    @Test
    public void testGetUrlThenLandingPage() {
        String line = "172.56.33.37 [19/Jun/2015:22:20:12 +0000] http static.gbot.me \"GET /some-url HTTP/1.1\" \"Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) GSA/5.2.43972 Mobile/12F70 Safari/600.1.4\" http://www.gogobot.com/space-needle-seattle-attraction--nearby--attractions 200 403 11 miss";
        String expected = "http://www.gogobot.com/space-needle-seattle-attraction--nearby--attractions";
        LogLineParser logLineParser = new LogLineParser();
        String url = logLineParser.getURL(line);
        org.junit.Assert.assertEquals(expected, logLineParser.getLandingPage(line));
    }

    @Test
    public void testGetDate() {
        String line = "172.56.33.37 [19/Jun/2015:22:20:12 +0000] http static.gbot.me \"GET /some-url HTTP/1.1\" \"Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) GSA/5.2.43972 Mobile/12F70 Safari/600.1.4\" http://www.gogobot.com/space-needle-seattle-attraction--nearby--attractions 200 403 11 miss";
        String expected = "06-19-2015";
        LogLineParser logLineParser = new LogLineParser();

        org.junit.Assert.assertEquals(expected, logLineParser.getDate(line));
    }
}