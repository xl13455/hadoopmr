package com.xl.mr.friend;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TwoShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// A	I,K,C,B,G,F,H,O,D,
		String line = value.toString();
		String[] friend_persons = line.split("\t");
		String friend = friend_persons[0];
		String[] persons = friend_persons[1].split(",");
		Arrays.sort(persons);

		for (int i = 0; i < persons.length - 1; i++) {

			for (int j = i + 1; j < persons.length; j++) {
				// ���� <��-�ˣ�����> ����������ͬ�ġ���-�ˡ��Ե����к��Ѿͻᵽͬ1��reduce��ȥ
				context.write(new Text(persons[i] + "-" + persons[j]), new Text(friend));
			}
		}
	}
}
