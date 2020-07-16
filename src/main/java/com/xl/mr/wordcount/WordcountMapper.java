package com.xl.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//map阶段
public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	Text k = new Text();
	IntWritable v = new IntWritable();
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//获取一行
		String line = value.toString();
		//切分单词
		String[] words = line.split(" ");
		//循环输出
		for(String word:words) {
			k.set(word);
			v.set(1);
			context.write(k,v);
		}
		
	}
	
}
