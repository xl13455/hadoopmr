package com.xl.mr.NLineInputFormat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NLineDriver {
	
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
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
			    // 1 获取job对象
				Configuration configuration = new Configuration();
		        Job job = Job.getInstance(configuration); 
		          
		        // 2设置jar包位置，关联mapper和reducer
		        job.setJarByClass(NLineDriver.class);  
		        job.setMapperClass(NLineMapper.class);  
		        job.setReducerClass(NLineReducer.class);  
		        
		        // 3设置map输出kv类型
		        job.setMapOutputKeyClass(Text.class);  
		        job.setMapOutputValueClass(LongWritable.class);  
		        
		        // 4设置最终输出kv类型
		        job.setOutputKeyClass(Text.class);  
		        job.setOutputValueClass(LongWritable.class);  
		          
		        // 5设置输入输出数据路径
		        FileInputFormat.setInputPaths(job, new Path(args[0]));
		        job.setInputFormatClass(NLineInputFormat.class); 
		        NLineInputFormat.setNumLinesPerSplit(job, 3);
		        FileOutputFormat.setOutputPath(job, new Path(args[1]));  
		          
		        // 6提交job
		        job.waitForCompletion(true);  

		
	}
}
