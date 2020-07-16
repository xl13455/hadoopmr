package mr;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		FlowBean[] flowBeans = new FlowBean[4];
		
		FlowBean flowBean0 = new FlowBean(100,100);
		flowBeans[0] = flowBean0;
		
		
		flowBean0.setUpFlow(888);
		FlowBean flowBean1 = flowBean0;
		flowBeans[1] = flowBean1;
		
		flowBean0.setUpFlow(88);
		FlowBean flowBean2 = flowBean0;
		flowBeans[2] = flowBean2;
		
		flowBean0.setUpFlow(8);
		FlowBean flowBean3 = flowBean0;
		flowBeans[3] = flowBean3;
		
		
		System.out.println(Arrays.toString(flowBeans));
	}
}
