package com.xl.mr.friend;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TwoShareFriendsReducer extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)	throws IOException, InterruptedException {
		
		StringBuffer sb = new StringBuffer();

		for (Text value : values) {
			sb.append(value).append(" ");
		}
		
		context.write(key, new Text(sb.toString()));
	}

}
