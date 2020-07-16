package com.xl.mr.index_format;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TwoIndexMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	Text k = new Text();
	Text v = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		// 1 ��ȡ1������
		String line = value.toString();
		
		// 2�á�--���и�
		String[] fields = line.split("--");
		
		k.set(fields[0]);
		v.set(fields[1]);
		
		// 3 �������
		context.write(k, v);
	}


}
