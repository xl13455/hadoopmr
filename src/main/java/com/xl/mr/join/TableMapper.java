package com.xl.mr.join;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean>{
	
	private String name;
	private Text k = new Text();
	private TableBean v = new TableBean();
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		FileSplit inputSplit = (FileSplit)context.getInputSplit();
		this.name = inputSplit.getPath().getName();
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		//1001	01	1
		//01	ะกรื
		String line = value.toString();
		
		String[] fields = line.split("\t");
		
		if(name.startsWith("order")) {
			k.set(fields[1]);
			v.setOrder_id(fields[0]);
			v.setP_id(fields[1]);
			v.setAmount(Integer.parseInt(fields[2]));
			v.setPname("");
			v.setFlag("order");
			
		}else if(name.startsWith("pd")) {
			k.set(fields[0]);
			v.setOrder_id("");
			v.setP_id(fields[0]);
			v.setAmount(-1);
			v.setPname(fields[1]);
			v.setFlag("pd");
			
		}
		context.write(k, v);
	}
}
