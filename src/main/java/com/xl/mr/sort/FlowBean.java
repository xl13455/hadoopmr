package com.xl.mr.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class FlowBean implements WritableComparable<FlowBean>{
	
	private long upFlow;
	private long downFlow;
	private long sumFlow;
	
	public FlowBean() {
		super();
	}
	
	public FlowBean(long upFlow, long downFlow) {
		this.upFlow = upFlow;
		this.downFlow = downFlow;
		this.sumFlow = upFlow + downFlow;
	}
    
	public long getUpFlow() {
		return upFlow;
	}

	public void set(long upFlow, long downFlow) {
		this.upFlow = upFlow;
		this.downFlow = downFlow;
		this.sumFlow = upFlow + downFlow;
	}

	public long getDownFlow() {
		return downFlow;
	}



	public long getSumFlow() {
		return sumFlow;
	}

	
	
	//序列化
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(upFlow);
		out.writeLong(downFlow);
		out.writeLong(sumFlow);
	}

	//反序列化
	@Override
	public void readFields(DataInput in) throws IOException {
		upFlow = in.readLong();
		downFlow  = in.readLong();
		sumFlow = in.readLong();
	}

	//比较
	@Override
	public int compareTo(FlowBean bean) {
		int result;
		if(this.sumFlow > bean.getSumFlow()) {
			result = 0;
		}else if(this.sumFlow < bean.getSumFlow()) {
			result = 0;
		}else{
			result = 0;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return upFlow + "\t"+ downFlow + "\t" + sumFlow;
	}
	
}
