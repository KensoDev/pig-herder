package io.avi;

import org.apache.pig.EvalFunc;
import org.apache.pig.PigWarning;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.apache.pig.impl.logicalLayer.schema.Schema;

import java.io.IOException;

/**
 * Created by avitzurel on 7/2/15.
 */
public class LineParser extends EvalFunc<Tuple> {

    // The main method in question. Gets run for every 'thing' that gets sent to
    // this UDF
    public Tuple exec(Tuple input) throws IOException {
        Object o = input.get(0);
        String line = (String) o;

        LogLineParser logLineParser = new LogLineParser();
        QueryStringParser queryStringParser = new QueryStringParser(logLineParser.getURL(line));

        try {
            TupleFactory tf = TupleFactory.getInstance();
            Tuple t = tf.newTuple();

            String itemId = queryStringParser.getQueryStringValue("item_id");

            t.append(logLineParser.getDate(line));
            t.append(itemId);

            if (itemId.equals("unknown")){
                return null;
            }

            return t;
        } catch (Exception e) {
            warn("Failed Parsing line", PigWarning.UDF_WARNING_1);
            throw e;
        }
    }

    public Schema outputSchema(Schema input) {
        Schema tupleSchema = new Schema();

        tupleSchema.add(new Schema.FieldSchema("impression_date", DataType.CHARARRAY));
        tupleSchema.add(new Schema.FieldSchema("item_id", DataType.CHARARRAY));

        try {
            return new Schema(new Schema.FieldSchema(getSchemaName(this.getClass().getName().toLowerCase(), input), tupleSchema, DataType.TUPLE));
        } catch (Exception e) {
            warn("Failed Parsing line", PigWarning.UDF_WARNING_1);

            e.printStackTrace();
            return null;
        }
    }
}