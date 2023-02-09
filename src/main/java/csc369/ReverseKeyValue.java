package csc369;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class
ReverseKeyValue {

    public static final Class OUTPUT_KEY_CLASS = IntWritable.class;
    public static final Class OUTPUT_VALUE_CLASS = Text.class;

    public static class MapperImpl extends Mapper<LongWritable, Text, IntWritable, Text> {
	private final IntWritable one = new IntWritable();
	private Text word = new Text();

        @Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String url = value.toString().split("\t")[0];
            String countStr = value.toString().split("\t")[1];
            one.set(Integer.parseInt(countStr));
            word.set(url);
            context.write(one, word);

        }
    }

    public static class ReducerImpl extends Reducer<IntWritable, Text, IntWritable, Text> {
	private IntWritable result = new IntWritable();

    

	protected void reduce(IntWritable intOne, Text word, Context context) throws IOException, InterruptedException {

            context.write(intOne, word);
       }
    }

}
