package com.xl.etl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LogDriver {

	// 为了解决org.apache.hadoop.io.nativeio.NativeIO$Windows.access0(Ljava/lang/String;I)Z
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
		
	public static void main(String[] args) throws Exception {
		// 输入输出路径需要根据自己电脑上实际的输入输出路径设置
        args = new String[] { "e:/inandout/input8", "e:/inandout/output8" };

		// 1 获取job信息
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		// 2 加载jar包
		job.setJarByClass(LogDriver.class);

		// 3 关联map
		job.setMapperClass(LogMapper.class);

		// 4 设置最终输出类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		// 设置reducetask个数为0
		job.setNumReduceTasks(0);

		// 5 设置输入和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// 6 提交
		job.waitForCompletion(true);

	}
}
