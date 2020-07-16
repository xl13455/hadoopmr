package com.xl.mr.flowsum;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean>{
	
	Text k = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		
		String[] fields = line.split("\t");
		
		String phoneNum = fields[1];
		
		long upFlow = Long.parseLong(fields[fields.length - 3]);
		
		long downFlow = Long.parseLong(fields[fields.length - 2]);
		
		k.set(phoneNum);
			
		context.write(k, new FlowBean(upFlow,downFlow));
		
	}
}
