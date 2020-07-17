package com.xl.mr.topN;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class FlowBean implements WritableComparable<FlowBean>{
	
	private int upFlow;
	private int downFlow;
	private int sumFlow;
		
	public FlowBean() {
		super();
	}
	
	public FlowBean(int upFlow, int downFlow) {
		this.upFlow = upFlow;
		this.downFlow = downFlow;
		this.sumFlow = upFlow + downFlow;
	}

	public int getUpFlow() {
		return upFlow;
	}

	public void setUpFlow(int upFlow) {
		this.upFlow = upFlow;
	}

	public int getDownFlow() {
		return downFlow;
	}

	public void setDownFlow(int downFlow) {
		this.downFlow = downFlow;
	}

	public int getSumFlow() {
		return sumFlow;
	}

	public void setSumFlow(int sumFlow) {
		this.sumFlow = sumFlow;
	}
	
	public void setUpAndDown(int upFlow,int downFlow) {
		this.upFlow = upFlow;
		this.downFlow = downFlow;
		this.sumFlow = upFlow + downFlow;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(upFlow);
		out.writeInt(downFlow);
		out.writeInt(sumFlow);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		upFlow = in.readInt();
		downFlow = in.readInt();
		sumFlow = in.readInt();
	}

	@Override
	public int compareTo(FlowBean o) {
		int result;
		if(this.sumFlow > o.getSumFlow()) {
			result = -1;
		}else if(this.sumFlow < o.getSumFlow()) {
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}

	@Override
	public String toString() {
		return upFlow + "\t" + downFlow + "\t" + sumFlow ;
	}
	
	

}
