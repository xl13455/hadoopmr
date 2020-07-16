package com.xl.mr.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderSortGroupingComparator extends WritableComparator{
	
	public OrderSortGroupingComparator() {
		super(OrderBean.class,true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		// order_id相同，就是相同的key
		int result = 0;

		OrderBean aBean = (OrderBean) a;
		OrderBean bBean = (OrderBean) b;
		if (aBean.getOrder_id() > bBean.getOrder_id()) {
			result = 1;
		} else if (aBean.getOrder_id() < bBean.getOrder_id()) {
			result = -1;
		} else {
			result = 0;
		}

		return result;
	}

}
