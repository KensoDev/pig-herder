package io.avi.tests;

import io.avi.QueryStringParser;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by avitzurel on 6/20/15.
 */
public class QueryStringParserTest extends TestCase {

    final String url = "/stats/item/impression?item_id=XXXXX";

    @Test
    public void testQuestionStringValueCorrect() {
        QueryStringParser queryStringParser = new QueryStringParser(url);
        assertEquals(queryStringParser.getQueryStringValue("item_id"), "XXXXX");
    }

    @Test
    public void testQueryStringUnknown() {
        QueryStringParser queryStringParser = new QueryStringParser(url);
        assertEquals(queryStringParser.getQueryStringValue("invalidQueryStringName"), "unknown");
    }
}