package com.xl.mr.index1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

// ‰»Î
//atguigu pingping
//atguigu ss
//atguigu ss

// ‰≥ˆ
//atguigu 1.txt 
//atguigu 2.txt


public class IndexMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	private String fileName;
	Text k = new Text();
	Text v = new Text();
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		FileSplit fileSplit = (FileSplit) context.getInputSplit();
		this.fileName = fileSplit.getPath().getName();
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] strs = line.split(" ");
		for(String str:strs) {
			k.set(str);
			v.set(fileName);
			context.write(k, v);
		}
	}
}
