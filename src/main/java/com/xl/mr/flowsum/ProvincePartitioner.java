package com.xl.mr.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class ProvincePartitioner extends Partitioner<Text, FlowBean>{

	@Override
	public int getPartition(Text key, FlowBean value, int numPartitions) {
		String str_key = key.toString();
		int partition = 4;
		if(str_key.startsWith("136")) {
			partition = 0;
		}else if(str_key.startsWith("137")) {
			partition = 1;
		}else if(str_key.startsWith("138")) {
			partition = 2;
		}else if(str_key.startsWith("139")) {
			partition = 3;
		}
		return partition;
	}

}
