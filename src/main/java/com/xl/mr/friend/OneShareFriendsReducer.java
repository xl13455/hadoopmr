package com.xl.mr.friend;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class OneShareFriendsReducer extends Reducer<Text, Text, Text, Text>{
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)	throws IOException, InterruptedException {
		
		StringBuffer sb = new StringBuffer();
		//1 ƴ��
		for(Text value: values){
			sb.append(value).append(",");
		}
		//2 д��
		context.write(key, new Text(sb.toString()));
	}
}
