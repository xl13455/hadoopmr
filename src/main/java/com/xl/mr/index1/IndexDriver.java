package com.xl.mr.index1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class IndexDriver {
	
	static {
	    try {
	        System.setProperty("hadoop.home.dir", "E:/hadoop-2.7.2");
	        System.load("E:/hadoop-2.7.2/bin/hadoop.dll");
	    } catch (UnsatisfiedLinkError e) {
	        System.err.println("Native code library failed to load.\n" + e);
	        System.exit(1);
	    }
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		
		job.setJarByClass(IndexDriver.class);
		
		job.setMapperClass(IndexMapper.class);
		job.setReducerClass(IndexReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
