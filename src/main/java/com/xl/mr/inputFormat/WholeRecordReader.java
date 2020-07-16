package com.xl.mr.inputFormat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;



public class WholeRecordReader extends RecordReader<Text, BytesWritable>{
	
	FileSplit split = null;
	Configuration configuration = null;
	Text k = new Text();
	BytesWritable v = new BytesWritable();
	boolean isProgress = true;
	
	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
		//��ʼ��
		this.split = (FileSplit) split;
		configuration = context.getConfiguration();
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		//����ҵ���߼�
		if(isProgress) {
			byte[] buf = new byte[(int) split.getLength()];
			//1 ��ȡFs����
			Path path = split.getPath(); 
			FileSystem fs = path.getFileSystem(configuration);
			//2 ��ȡ������
			FSDataInputStream fis = fs.open(path);
			//3 ����
			IOUtils.readFully(fis, buf, 0, buf.length);
			//4 ��װv
			v.set(buf, 0, buf.length);
			//5 ��װk
			k.set(path.toString());
			//6 �ر���Դ
			IOUtils.closeStream(fis);
			isProgress = false;
			return true;
		}
		return false;
	}

	@Override
	public Text getCurrentKey() throws IOException, InterruptedException {
		return k;
	}

	@Override
	public BytesWritable getCurrentValue() throws IOException, InterruptedException {
		return v;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		return 0;
	}

	@Override
	public void close() throws IOException {
		
	}

}
