package com.xl.mr.order;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/*0000001	Pdt_01	222.8
  0000002	Pdt_05	722.4
  0000001	Pdt_02	33.8
  0000003	Pdt_06	232.8
  0000003	Pdt_02	33.8
  0000002	Pdt_03	522.8
  0000002	Pdt_04	122.4*/

public class OrderSortMapper extends Mapper<LongWritable, Text, OrderBean,NullWritable>{
	
	private OrderBean k = new OrderBean();
	
	@Override
	protected void map(LongWritable key, Text value,Context context) throws IOException, InterruptedException {
		String[] fields = value.toString().split("\t");
		k.setOrder_id(Integer.parseInt(fields[0]));
		k.setPrice(Double.parseDouble(fields[2]));
		context.write(k, NullWritable.get());
	}
}
