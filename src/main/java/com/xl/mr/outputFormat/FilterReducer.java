package com.xl.mr.outputFormat;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FilterReducer extends Reducer<Text, NullWritable, Text, NullWritable>{
	
	Text k = new Text();
	
	@Override
	protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
		String line = key.toString();
		line = line+"\r\n";
		k.set(line);
		for(NullWritable value:values) {
			context.write(k, value);
		}
	}
	
}
