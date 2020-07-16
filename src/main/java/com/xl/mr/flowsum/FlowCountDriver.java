package com.xl.mr.flowsum;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FlowCountDriver {
	
	
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
		
		Configuration conf = new Configuration();
		//1 获取Job对象
		Job job = Job.getInstance(conf);
		
		//2 设置jar存储位置
		job.setJarByClass(FlowCountDriver.class);
		
		//3 关联Mapper和Reducer
		job.setMapperClass(FlowCountMapper.class);
		job.setReducerClass(FlowCountReducer.class);
		
		//4 设置Mapper阶段的输出数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);
		
		//5 设置最终数据的输出数据类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);

		
		//6 设置输入路径和输出路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//6...设置自定义的partitioner 和 reduceTask个数
		job.setPartitionerClass(ProvincePartitioner.class);
		job.setNumReduceTasks(5);

		//7 提交Job
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);

		
	}
	
}
