package com.xl.mr.keyValueTextInputFormat;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class KVMapper extends Mapper<Text, Text, Text, LongWritable> {
	
	LongWritable v = new LongWritable();
	
	@Override
	protected void map(Text key, Text value, Context context)throws IOException, InterruptedException {
		
		System.out.println(key+"----"+value);
		v.set(1);
		context.write(key, v);
		
	}
	
}
