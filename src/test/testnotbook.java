/**
 * 
 */
package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import algorithmUtil.HEFTScheduler;
import bean.Application;
import bean.Criticality;
import bean.Processor;
import bean.Task;
import datasetUtil.DataGen;
import datasetUtil.DataSample;

/**
 * @author Emma
 *
 */
public class testnotbook {
	/**
	 * 测试函数
	 * @author Emma
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	

     /**
      * 两种初始化数据的方法，测试时只选其一，注释掉另一个
      * 两种方法分别是：1.对testSample进行随机初始化 2：用指定的数据进行初始化
      */
     //1.随机初始化
	/*
	 String name = "testSample2";
	 int processorAmount = 6;
	 int applicationAmount = 15;
     DataSample testSample = new DataSample(name, processorAmount, applicationAmount);
     DataGen.initDataSample_Random(testSample);
     */
     //2.用指定的数据进行初始化
        //------指定开始--------
        //处理器集合 
		List<Processor> givenProcessorList = new ArrayList<Processor>();
		for (int i = 0; i < 4; i++) {
			Processor p = new Processor("p" + i);
			givenProcessorList.add(p);
		}
		//设置3个Application，先给出三个通信矩阵和计算矩阵
		// 6*4 6������3��������
		Integer[][] compMartrix1 = new Integer[][] { { 0, 0, 0, 0 }, { 0, 12, 8, 9 }, { 0, 9, 15, 11 }, { 0, 7, 12, 16 }, { 0, 13, 15, 18 }, { 0, 18, 10, 20 }, { 0, 15, 10, 8 } };
		// 7*7 6����������֮���ͨ�ſ���
		Integer[][] commMartrix1 = new Integer[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 8, 12, 0, 0, 0 }, { 0, 0, 0, 0, 12, 0, 0 }, { 0, 0, 0, 0, 0, 10, 0 }, { 0, 0, 0, 0, 0, 0, 7 },
				{ 0, 0, 0, 0, 0, 0, 6 }, { 0, 0, 0, 0, 0, 0, 0 } };
        /*
		// 5*4 5������3��������
		Integer[][] compMartrix2 = new Integer[][] { { 0, 0, 0, 0 }, { 0, 14, 5, 6 }, { 0, 9, 10, 11 }, { 0, 18, 17, 16 }, { 0, 21, 15, 19 }, { 0, 7, 6, 15 } };
		// 6*6 5����������֮���ͨ�ſ���
		Integer[][] commMartrix2 = new Integer[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 15, 10, 12, 0 }, { 0, 0, 0, 0, 0, 14 }, { 0, 0, 0, 0, 0, 18 }, { 0, 0, 0, 0, 0, 10 }, { 0, 0, 0, 0, 0, 0 } };
        */
		//11*4,11*11, 10个任务3个处理器 heft原文的例子
	    Integer[][] compMartrix2 = new Integer[][] {{0,0,0,0},{0,14,16,9},{0,13,19,18},{0,11,13,19},{0,13,8,17},{0,12,13,10},{0,13,16,9},{0,7,15,11},{0,5,11,14},{0,18,12,20},{0,21,7,16}};
	    Integer[][] commMartrix2 = new Integer[][] {{0,0,0,0,0,0,0,0,0,0,0},{0,0,18,12,9,11,14,0,0,0,0},{0,0,0,0,0,0,0,0,19,16,0},
	    	{0,0,0,0,0,0,0,23,0,0,0},{0,0,0,0,0,0,0,0,27,23,0},{0,0,0,0,0,0,0,0,0,13,0},
	    	{0,0,0,0,0,0,0,0,15,0,0},{0,0,0,0,0,0,0,0,0,0,17},{0,0,0,0,0,0,0,0,0,0,11},{0,0,0,0,0,0,0,0,0,0,13},{0,0,0,0,0,0,0,0,0,0,0}};
	    // 6*4 6������3��������
		Integer[][] compMartrix3 = new Integer[][] { { 0, 0, 0, 0 }, { 0, 8, 11, 9 }, { 0, 14, 13, 8 }, { 0, 9, 12, 16 }, { 0, 18, 15, 14 }, { 0, 18, 16, 20 }, { 0, 5, 10, 7 } };
		// 7*7 6����������֮���ͨ�ſ���
		Integer[][] commMartrix3 = new Integer[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 9, 12, 14, 0, 0 }, { 0, 0, 0, 16, 0, 9, 0 }, { 0, 0, 0, 0, 0, 11, 0 }, { 0, 0, 0, 0, 0, 0, 7 },
				{ 0, 0, 0, 0, 0, 0, 13 }, { 0, 0, 0, 0, 0, 0, 0 } };
        Application app1 = new Application("F1");
        Application app2 = new Application("F2");
        Application app3 = new Application("F3");
        app1.initApplication_withoutDeadline(compMartrix1, commMartrix1, Criticality.S0, 0.0);
        DataGen.initTask_in_Application(app1, givenProcessorList, compMartrix1, commMartrix1);
        app2.initApplication_withoutDeadline(compMartrix2, commMartrix2, Criticality.S1, 10.0);
        DataGen.initTask_in_Application(app2, givenProcessorList, compMartrix2, commMartrix2);
        app3.initApplication_withoutDeadline(compMartrix3, commMartrix3, Criticality.S2, 20.0);
        DataGen.initTask_in_Application(app3, givenProcessorList, compMartrix3, commMartrix3);
        
        List<Application> givenApplicationList = new ArrayList<Application>();
        givenApplicationList.add(app1);
        givenApplicationList.add(app2);
        givenApplicationList.add(app3);
        
 	String name = "testSample2";
 	int processorAmount = givenProcessorList.size();
 	int applicationAmount = 3;
    DataSample testSample = new DataSample(name, processorAmount, applicationAmount);
    testSample.initDataSample(givenProcessorList, givenApplicationList);
       //------指定结束------
    		 
	
     System.out.println("-----------test begin--------- ");   
     System.out.println("test Sample name is: "+ testSample.getName());
     List<Processor> processorList = testSample.getProcessorList();
     List<Application> applicationList = testSample.getApplicationList();
     
     System.out.println("-----------test processorList--------- ");   
     System.out.println("处理器数量 ="+processorList.size());
     System.out.println("处理器名字");
      for(Processor p: processorList) {
    	  System.out.println("处理器"+p.getName());
      }
      
      System.out.println("-----------test applicationList--------- ");   
      System.out.println("application数量 ="+applicationList.size());    
	
	  for(Application app: applicationList) {
  	  System.out.println("功能"+app.getName());
  	  Integer[][] compMatrix =app.getComputingMartrix();
  	  System.out.println("	处理器数量"+compMatrix[0].length);
  	  System.out.println("	所含任务数量"+app.getComputingMartrix().length +"	"+app.getCommunicationMatrix().length +" "+app.getTaskList().size()); 
	  HEFTScheduler.setRanku(app);
  	  System.out.println("	所含的任务为：");
	  
  	  for(Task task:app.getTaskList()) {
  		System.out.print(task.getName()+", "+"其ranku为: "+ task.getRanku());
  		System.out.print(", 其后继任务为：");
  		for(Task t :task.getSuccTask$CommCostMap().keySet()) {
  			System.out.print(t.getName()+", ");
  		}
  		System.out.print(" 其前驱任务为：");
  		for(Task t:task.getPredTask$CommCostMap().keySet()) {
  			System.out.print(t.getName()+", ");
  		}
    	  System.out.println("  ");
  	  }
  	  
  	  System.out.println("  ");
  	  System.out.println("  ");
	  }
	 
	  
      }
}
