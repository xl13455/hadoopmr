package com.xl.mr.index1;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IndexReducer extends Reducer<Text, Text, Text, Text>{
	
	//输入
	//atguigu 1.txt 
	//atguigu 2.txt
	
	Text v = new Text();
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		//1 创建hashmap存储键值对，比如 1.txt  3
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		//2 填充map
		for(Text text:values) {
			if(map.containsKey(text.toString())) {
				map.put(text.toString(), map.get(text.toString())+1);
			}else {
				map.put(text.toString(), 1);
			}
		}
		
		//3 拼接字符串
		String vv = "";
		for(String s:map.keySet()) {
			vv += s + "-->" + map.get(s)+" ";
		}
		v.set(vv);
		context.write(key, v);
	}
}
