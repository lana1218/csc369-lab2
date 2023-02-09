package csc369;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class
URLClientCount {

    public static final Class OUTPUT_KEY_CLASS = Text.class;
    public static final Class OUTPUT_VALUE_CLASS = IntWritable.class;

    public static class MapperImpl extends Mapper<LongWritable, Text, Text, IntWritable> {
	private IntWritable one = new IntWritable(1);
	private Text word = new Text();


        @Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String url = value.toString().split(" ")[6];
            String hostname = value.toString().split(" ")[0];

            // if value is equal to my hard coded hostname
            if (url.equals("/razor.html")){

                word.set(hostname);
                context.write(word, one);

            }
        }
    }

    public static class ReducerImpl extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();

    
        @Override
	protected void reduce(Text word, Iterable<IntWritable> intOne, Context context) throws IOException, InterruptedException {
            int sum = 0;
            Iterator<IntWritable> itr = intOne.iterator();
        
            while (itr.hasNext()) {
                sum  += itr.next().get();
            }
            result.set(sum);
            context.write(word, result);
       }
    }

}
