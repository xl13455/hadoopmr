package com.xl.mr.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordcountDriver {
	
	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		//1 ��ȡJob����
		Job job = Job.getInstance(conf);
		
		//2 ����jar�洢λ��
		job.setJarByClass(WordcountDriver.class);
		
		//3 ����Mapper��Reducer
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);
		
		//4 ����Mapper�׶ε������������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//5 �����������ݵ������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		
		//6 ��������·�������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		//7 �ύJob
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);

	}
	
}
