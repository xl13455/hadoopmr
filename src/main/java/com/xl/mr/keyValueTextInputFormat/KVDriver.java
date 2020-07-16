package com.xl.mr.keyValueTextInputFormat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class KVDriver {
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
	//--------------------------分割线-----------------------------
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		// 获取conf
		Configuration conf = new Configuration();
		
		// 设置切割符
		conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, "\t");

		//获取job对象
		Job job = Job.getInstance(conf);
		
		//设置jar的存储位置
		job.setJarByClass(KVDriver.class);
		
		//关联mapper和reducer
		job.setMapperClass(KVMapper.class);
		job.setReducerClass(KVReducer.class);
		
		//设置Mapper的输出数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		//设置最终输出的数据类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		//设置输入输出数据路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
				
		//设置输入格式
		job.setInputFormatClass(KeyValueTextInputFormat.class);
				
		//设置输出数据路径
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
				
		//提交job
		job.waitForCompletion(true);

		
	}
}
