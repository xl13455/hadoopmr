package com.xl.mr.inputFormat;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class SequenceFileDriver {
	
	//为了解决org.apache.hadoop.io.nativeio.NativeIO$Windows.access0(Ljava/lang/String;I)Z
	static {
			  try {
			      // 设置 HADOOP_HOME 目录
			      System.setProperty("hadoop.home.dir", "E:/hadoop-2.7.2");
			      // 加载库文件
			      System.load("E:/hadoop-2.7.2/bin/hadoop.dll");
			  } catch (UnsatisfiedLinkError e) {
			      System.err.println("Native code library failed to load.\n" + e);
			      System.exit(1);
			  }
	}
			
	public static void main(String[] args) throws  Exception {

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		job.setJarByClass(SequenceFileDriver.class);
		job.setMapperClass(SequenceFileMapper.class);
		job.setReducerClass(SequenceFileReducer.class);

		job.setInputFormatClass(WholeFileInputformat.class);
	    job.setOutputFormatClass(SequenceFileOutputFormat.class);
       
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BytesWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BytesWritable.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);

	}
}
