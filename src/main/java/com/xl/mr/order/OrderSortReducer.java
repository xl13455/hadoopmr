package com.xl.mr.order;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class OrderSortReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {
	
	@Override
	protected void reduce(OrderBean key, Iterable<NullWritable> values,Context context) throws IOException, InterruptedException {
		System.out.println(key+"***********");
		//≤‚ ‘
		for(NullWritable value:values) {
			context.write(key, value);
			System.out.println(key+"\t"+value);
		}
		System.out.println(key+"***********");
		System.out.println("--------∑÷∏Óœﬂ-----------------");
		
		//System.out.println(key+"***********");
		//context.write(key, NullWritable.get());
		//System.out.println(key+"***********");
	}

}
