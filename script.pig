register $jar_location/mysql-connector-java-5.1.35-bin.jar;
register $jar_location/pig-herder.jar;

DEFINE Parser io.avi.LineParser();

lines = LOAD '$input_location/$file_pattern*' USING TextLoader() as (line:chararray);
item_impressions = FOREACH lines GENERATE FLATTEN(Parser(line)) as (date:chararray, item_id:chararray);

grouped = GROUP item_impressions BY (date, item_id);

group_counts = FOREACH grouped GENERATE group.date, group.item_id, COUNT(item_impressions.item_id);
