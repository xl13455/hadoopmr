package com.xl.mr.order;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class OrderBean implements WritableComparable<OrderBean>{
	
	private int order_id;
	private double price;
	
	
	public OrderBean() {
		super();
	}

	public OrderBean(int order_id, double price) {
		this.order_id = order_id;
		this.price = price;
	}
	
	public int getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(order_id);
		out.writeDouble(price);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.order_id = in.readInt();
		this.price = in.readDouble();
	}

	@Override
	public int compareTo(OrderBean o) {
		int result = 0;
		if(this.order_id > o.getOrder_id()) {
			result = 1;
		}else if(this.order_id < o.getOrder_id()) {
			result = -1;
		}else {
			// ¼Û¸ñµ¹ÐòÅÅÐò
			result = this.price > o.getPrice() ? -1 : 1;

		}
		return result;
	}

	@Override
	public String toString() {
		return order_id+"\t"+price;
	}
	
	

}
