package com.xl.mr.keyValueTextInputFormat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class KVDriver {
	//Ϊ�˽��org.apache.hadoop.io.nativeio.NativeIO$Windows.access0(Ljava/lang/String;I)Z
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
	//--------------------------�ָ���-----------------------------
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		// ��ȡconf
		Configuration conf = new Configuration();
		
		// �����и��
		conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, "\t");

		//��ȡjob����
		Job job = Job.getInstance(conf);
		
		//����jar�Ĵ洢λ��
		job.setJarByClass(KVDriver.class);
		
		//����mapper��reducer
		job.setMapperClass(KVMapper.class);
		job.setReducerClass(KVReducer.class);
		
		//����Mapper�������������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		//���������������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		//���������������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
				
		//���������ʽ
		job.setInputFormatClass(KeyValueTextInputFormat.class);
				
		//�����������·��
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
				
		//�ύjob
		job.waitForCompletion(true);

		
	}
}
