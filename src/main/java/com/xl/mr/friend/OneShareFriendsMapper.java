package com.xl.mr.friend;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OneShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	//A:B,C,D,F,E,O
	//B:A,C,E,K
	
	Text k = new Text();
	Text v = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] fields = line.split(":");
		String person = fields[0];
		String[] friends = fields[1].split(",");
		for(String friend:friends) {
			k.set(friend);
			v.set(person);
			context.write(k, v);
		}
	}
}
