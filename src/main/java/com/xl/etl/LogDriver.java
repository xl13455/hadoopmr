package com.xl.etl;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LogDriver {

	// Ϊ�˽��org.apache.hadoop.io.nativeio.NativeIO$Windows.access0(Ljava/lang/String;I)Z
	static {
		try {
			// ���� HADOOP_HOME Ŀ¼
			System.setProperty("hadoop.home.dir", "E:/hadoop-2.7.2");
			// ���ؿ��ļ�
			System.load("E:/hadoop-2.7.2/bin/hadoop.dll");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}
		
	public static void main(String[] args) throws Exception {
		// �������·����Ҫ�����Լ�������ʵ�ʵ��������·������
        args = new String[] { "e:/inandout/input8", "e:/inandout/output8" };

		// 1 ��ȡjob��Ϣ
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		// 2 ����jar��
		job.setJarByClass(LogDriver.class);

		// 3 ����map
		job.setMapperClass(LogMapper.class);

		// 4 ���������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		// ����reducetask����Ϊ0
		job.setNumReduceTasks(0);

		// 5 ������������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// 6 �ύ
		job.waitForCompletion(true);

	}
}
