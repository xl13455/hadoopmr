package com.xl.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	IntWritable v = new IntWritable();
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		
		int sum=0;
		
		//Ñ­»·ÀÛ¼Ó
		for(IntWritable value:values) {
			sum+=value.get();
		}
		
		//Êä³ö
		v.set(sum);
		context.write(key, v);
	}
	
}
