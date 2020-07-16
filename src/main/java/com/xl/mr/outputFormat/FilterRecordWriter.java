package com.xl.mr.outputFormat;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class FilterRecordWriter extends RecordWriter<Text, NullWritable>{
	
	FSDataOutputStream fsDataOutputStream1 = null;
	FSDataOutputStream fsDataOutputStream2 = null;
	
	public FilterRecordWriter(TaskAttemptContext job) {
		try {
			//1获取文件系统
			FileSystem fs = FileSystem.get(job.getConfiguration());
			//2创建输出指定的log文件
			fsDataOutputStream1 = fs.create(new Path("E:/inandout/output6/atguigu.log"));
			fsDataOutputStream2 = fs.create(new Path("E:/inandout/output6/other.log"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void write(Text key, NullWritable value) throws IOException, InterruptedException {
		//判断
		if(key.toString().contains("atguigu")) {
			fsDataOutputStream1.write(key.toString().getBytes());
			fsDataOutputStream1.write("\n".toString().getBytes());
		}else {
			fsDataOutputStream2.write(key.toString().getBytes());
			fsDataOutputStream2.write("\n".toString().getBytes());
		}
	}

	@Override
	public void close(TaskAttemptContext context) throws IOException, InterruptedException {
		IOUtils.closeStream(fsDataOutputStream1);
		IOUtils.closeStream(fsDataOutputStream2);
	}

}
