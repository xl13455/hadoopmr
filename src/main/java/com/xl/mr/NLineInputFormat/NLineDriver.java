package com.xl.mr.NLineInputFormat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NLineDriver {
	
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
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
			    // 1 ��ȡjob����
				Configuration configuration = new Configuration();
		        Job job = Job.getInstance(configuration); 
		          
		        // 2����jar��λ�ã�����mapper��reducer
		        job.setJarByClass(NLineDriver.class);  
		        job.setMapperClass(NLineMapper.class);  
		        job.setReducerClass(NLineReducer.class);  
		        
		        // 3����map���kv����
		        job.setMapOutputKeyClass(Text.class);  
		        job.setMapOutputValueClass(LongWritable.class);  
		        
		        // 4�����������kv����
		        job.setOutputKeyClass(Text.class);  
		        job.setOutputValueClass(LongWritable.class);  
		          
		        // 5���������������·��
		        FileInputFormat.setInputPaths(job, new Path(args[0]));
		        job.setInputFormatClass(NLineInputFormat.class); 
		        NLineInputFormat.setNumLinesPerSplit(job, 3);
		        FileOutputFormat.setOutputPath(job, new Path(args[1]));  
		          
		        // 6�ύjob
		        job.waitForCompletion(true);  

		
	}
}
