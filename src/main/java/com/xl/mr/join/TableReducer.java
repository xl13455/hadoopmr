package com.xl.mr.join;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable>{
	@Override
	protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {
		
		ArrayList<TableBean> orderBeans = new ArrayList<TableBean>();
		
		TableBean pdBean = new TableBean();
		
		for (TableBean value : values) {
			if("order".equals(value.getFlag())) {		
				try {
					TableBean tmpBean = new TableBean();
					BeanUtils.copyProperties(tmpBean, value);
					orderBeans.add(tmpBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {	
				try {
					BeanUtils.copyProperties(pdBean, value);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		
		for(TableBean bean:orderBeans){
			bean.setPname (pdBean.getPname());
			context.write(bean, NullWritable.get());
		}	
	}
}
