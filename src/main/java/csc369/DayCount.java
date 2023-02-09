package csc369;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


public class
DayCount {

    public static final Class OUTPUT_KEY_CLASS = Text.class;
    public static final Class OUTPUT_VALUE_CLASS = IntWritable.class;

    public static class MapperImpl extends Mapper<LongWritable, Text, Text, IntWritable> {
        private final IntWritable num = new IntWritable();
        private Text date = new Text();

        private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm");

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String dateStr = value.toString().split(" ")[3];
            String byteStr = value.toString().split(" ")[9];

            String pattern = "[dd/MMM/yyyy:HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            String year = null;
            String month = null;
            String day = null;

            try {
                Date dateInput = format.parse(dateStr);


                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                year = yearFormat.format(dateInput);

                SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
                month = monthFormat.format(dateInput);

                SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
                day = dayFormat.format(dateInput);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            String dateForCount = year + "-" + month + "-" + day;

            date.set(dateForCount);
            num.set(Integer.parseInt(byteStr));

            context.write(date, num);

        }
    }

        public static class ReducerImpl extends Reducer<Text, IntWritable, Text, IntWritable> {
            private IntWritable result = new IntWritable();


            protected void reduce(Text word, Iterable<IntWritable> intOne, Context context) throws IOException, InterruptedException {
                int sum = 0;
                Iterator<IntWritable> itr = intOne.iterator();

                while (itr.hasNext()) {
                    sum += itr.next().get();
                }
                result.set(sum);
                context.write(word, result);
            }
        }

    }
