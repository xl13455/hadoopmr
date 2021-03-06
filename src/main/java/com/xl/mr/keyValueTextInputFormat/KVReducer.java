package com.xl.mr.keyValueTextInputFormat;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KVReducer extends Reducer<Text, LongWritable, Text, LongWritable>{
	
	LongWritable v = new LongWritable();
	
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		for(LongWritable value:values) {
			sum += Integer.parseInt(value.toString());
		}
		v.set(sum);
		context.write(key,v);
		
	}
	
}
