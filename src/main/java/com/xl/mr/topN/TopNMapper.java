package com.xl.mr.topN;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopNMapper extends Mapper<LongWritable, Text, FlowBean, Text>{
	
	FlowBean k = new FlowBean();
	Text v = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] fields = line.split("\t");
		k.setUpAndDown(Integer.parseInt(fields[1]),Integer.parseInt(fields[2]));
		v.set(fields[0]);
		context.write(k, v);	
	}
}
