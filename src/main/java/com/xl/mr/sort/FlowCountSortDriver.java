package com.xl.mr.sort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class FlowCountSortDriver {
	
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
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		//1 ��ȡJob����
		Job job = Job.getInstance(conf);
		
		//2 ����jar�洢λ��
		job.setJarByClass(FlowCountSortDriver.class);
		
		//3 ����Mapper��Reducer
		job.setMapperClass(FlowCountSortMapper.class);
		job.setReducerClass(FlowCountSortReducer.class);
		
		//4 ����Mapper�׶ε������������
		job.setMapOutputKeyClass(FlowBean.class);
		job.setMapOutputValueClass(Text.class);
		
		//5 �����������ݵ������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);

		//6 ��������·�������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
		//7 �ύJob
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}

}
