package com.xl.etl;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMapper extends Mapper<LongWritable,Text,Text,NullWritable>{
	
	Text k = new Text();
	
	@Override
	protected void map(LongWritable key, Text value,Context context)throws IOException, InterruptedException {
		String line = value.toString();
		boolean result = parseLog(line,context);
		if(!result) {
			return;
		}
		k.set(line);
		context.write(k, NullWritable.get());
	}

	public boolean parseLog(String line, Mapper<LongWritable, Text, Text, NullWritable>.Context context) {
		// 1 截取
		String[] fields = line.split(" ");
		// 2 日志长度大于11的为合法
		if (fields.length > 11) {
			context.getCounter("map", "true").increment(1);
			return true;
		}else {
			context.getCounter("map", "false").increment(1);
			return false;
		}	
	}
}
