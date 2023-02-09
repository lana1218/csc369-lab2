package csc369;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class HadoopApp {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "Hadoop example");
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

	if (otherArgs.length < 3) {
	    System.out.println("Expected parameters: <job class> <input dir> <output dir>");
	    System.exit(-1);
	} else if ("WordCount".equalsIgnoreCase(otherArgs[0])) {
	    job.setReducerClass(URLCount.ReducerImpl.class);
	    job.setMapperClass(URLCount.MapperImpl.class);
	    job.setOutputKeyClass(URLCount.OUTPUT_KEY_CLASS);
	    job.setOutputValueClass(URLCount.OUTPUT_VALUE_CLASS);
	} else if ("ReverseKeyValue".equalsIgnoreCase(otherArgs[0])) {
		job.setReducerClass(ReverseKeyValue.ReducerImpl.class);
		job.setMapperClass(ReverseKeyValue.MapperImpl.class);
		job.setOutputKeyClass(ReverseKeyValue.OUTPUT_KEY_CLASS);
		job.setOutputValueClass(ReverseKeyValue.OUTPUT_VALUE_CLASS);
	} else if ("HTTPCount".equalsIgnoreCase(otherArgs[0])) {
		job.setReducerClass(HTTPCount.ReducerImpl.class);
		job.setMapperClass(HTTPCount.MapperImpl.class);
		job.setOutputKeyClass(HTTPCount.OUTPUT_KEY_CLASS);
		job.setOutputValueClass(HTTPCount.OUTPUT_VALUE_CLASS);
	} else if ("HostnameCount".equalsIgnoreCase(otherArgs[0])) {
		job.setReducerClass(HostnameCount.ReducerImpl.class);
		job.setMapperClass(HostnameCount.MapperImpl.class);
		job.setOutputKeyClass(HostnameCount.OUTPUT_KEY_CLASS);
		job.setOutputValueClass(HostnameCount.OUTPUT_VALUE_CLASS);
	} else if ("URLClientCount".equalsIgnoreCase(otherArgs[0])) {
		job.setReducerClass(URLClientCount.ReducerImpl.class);
		job.setMapperClass(URLClientCount.MapperImpl.class);
		job.setOutputKeyClass(URLClientCount.OUTPUT_KEY_CLASS);
		job.setOutputValueClass(URLClientCount.OUTPUT_VALUE_CLASS);
	} else if ("DateCount".equalsIgnoreCase(otherArgs[0])) {
		job.setReducerClass(DateCount.ReducerImpl.class);
		job.setMapperClass(DateCount.MapperImpl.class);
		job.setOutputKeyClass(DateCount.OUTPUT_KEY_CLASS);
		job.setOutputValueClass(DateCount.OUTPUT_VALUE_CLASS);
	} else if ("DayCount".equalsIgnoreCase(otherArgs[0])) {
		job.setReducerClass(DayCount.ReducerImpl.class);
		job.setMapperClass(DayCount.MapperImpl.class);
		job.setOutputKeyClass(DayCount.OUTPUT_KEY_CLASS);
		job.setOutputValueClass(DayCount.OUTPUT_VALUE_CLASS);
	} else {
	    System.out.println("Unrecognized job: " + otherArgs[0]);
	    System.exit(-1);
	}

        FileInputFormat.addInputPath(job, new Path(otherArgs[1]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));

        System.exit(job.waitForCompletion(true) ? 0: 1);
    }

}
