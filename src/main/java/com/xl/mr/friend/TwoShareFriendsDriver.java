package com.xl.mr.friend;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TwoShareFriendsDriver {
	
	public static void main(String[] args) throws Exception{
		// 1 ��ȡjob����
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		// 2 ָ��jar�����е�·��
		job.setJarByClass(TwoShareFriendsDriver.class);

		// 3 ָ��map/reduceʹ�õ���
		job.setMapperClass(TwoShareFriendsMapper.class);
		job.setReducerClass(TwoShareFriendsReducer.class);

		// 4 ָ��map�������������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		// 5 ָ�������������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// 6 ָ��job������ԭʼ����Ŀ¼
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// 7 �ύ
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);

	}
	
}
