package com.xl.mr.index_format;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class OneIndexMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	private String name;
	Text k = new Text();
	IntWritable v = new IntWritable();
	
	//1 初始化参数
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		FileSplit fileSplit = (FileSplit)context.getInputSplit();
		this.name = fileSplit.getPath().getName();
	}
	
	//2 map核心逻辑
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] fields = line.split(" ");
		for(String field:fields) {
			k.set(field+"--"+name);
			v.set(1);
			context.write(k, v);
		}
	}
}
