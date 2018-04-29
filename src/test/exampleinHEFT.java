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
import util.AST_AFT;

/**
 * HEFT算法原文里用的DAG例子，本文件用作存放HEFT原文的数据
 * @author Emma
 *
 */
public class exampleinHEFT {

public static void main(String[] args) throws IOException, ClassNotFoundException {
 
	//1.处理器列表
	List<Processor> givenProcessorList = new ArrayList<Processor>();
	for (int i = 0; i < 4; i++) {
		Processor p = new Processor("p" + i);
		givenProcessorList.add(p);
	}
	//2.计算矩阵和通信矩阵11*4,11*11, 10个任务3个处理器 heft原文的例子
    Integer[][] compMartrix2 = new Integer[][] {{0,0,0,0},{0,14,16,9},{0,13,19,18},{0,11,13,19},{0,13,8,17},{0,12,13,10},{0,13,16,9},{0,7,15,11},{0,5,11,14},{0,18,12,20},{0,21,7,16}};
    Integer[][] commMartrix2 = new Integer[][] {{0,0,0,0,0,0,0,0,0,0,0},{0,0,18,12,9,11,14,0,0,0,0},{0,0,0,0,0,0,0,0,19,16,0},
    	{0,0,0,0,0,0,0,23,0,0,0},{0,0,0,0,0,0,0,0,27,23,0},{0,0,0,0,0,0,0,0,0,13,0},
    	{0,0,0,0,0,0,0,0,15,0,0},{0,0,0,0,0,0,0,0,0,0,17},{0,0,0,0,0,0,0,0,0,0,11},{0,0,0,0,0,0,0,0,0,0,13},{0,0,0,0,0,0,0,0,0,0,0}};
    
    //3.初始化Application	
    Application app = new Application("F2");
    app.initApplication_withoutDeadline(compMartrix2, commMartrix2, Criticality.S1, 10.0);
    DataGen.initTask_in_Application(app, givenProcessorList, compMartrix2, commMartrix2);
    //4，调度
	  HEFTScheduler.setRanku(app);
	  //Task testTask =app.getTaskList().get(3);
	 // testTask.setRanku(81);
  	 // System.out.println(testTask.getName());
	  HEFTScheduler.initUnscheduledTaskQueue(app);
  	  //测试第二部的调度序列
  	  System.out.println("调度器的调度队列:");
  	  for(Task t : HEFTScheduler.getUnscheduledTaskQueue()) {
  		System.out.println(t.getName() + ", " + "其ranku为: " + t.getRanku());
  	  }
  	 
	  HEFTScheduler.allocationTask_toProcessors(givenProcessorList);
	  
    
    //5.测试与显示
	  System.out.println("功能"+app.getName());
	  Integer[][] compMatrix = app.getComputingMartrix();
	  System.out.println("	处理器数量" + compMatrix[0].length);
	  System.out.println("	所含任务数量" + app.getComputingMartrix().length + "	" +app.getCommunicationMatrix().length +" "+app.getTaskList().size()); 
	  System.out.println("	入口任务和出口任务：" + app.getEntryTask().getName() + " " +app.getExitTask().getName());
	  System.out.println("	lowerbound：" + app.getLowerBound());
	  System.out.println("	所含的任务为：");
	  
	  for(Task task:app.getTaskList()) {
		System.out.print(task.getName() + ", " + "其ranku为: " + task.getRanku());
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
	  

  	  //测试第二部的调度序列
  	  System.out.println("调度器的调度队列 之后:");
  	  for(Task t : HEFTScheduler.getUnscheduledTaskQueue()) {
  		System.out.println(t.getName() + ", " + "其ranku为: " + t.getRanku());
  	  }
  	  //测试lowerbound
  	    System.out.println("app lowerbound = "+app.getLowerBound());
  	  //显示分配结果：
  	  for(Processor p : givenProcessorList) {
  		System.out.println(p.getName()+"分配的任务有：");
  		for(Task t : p.getTask$AST_AFTMap().keySet()) {
  			System.out.print("  " + t.getName() + " " + p.getTask$AST_AFTMap().get(t).toString() );
  		}
    	System.out.println("  ");
  		System.out.println(p.getName()+"分配的任务有：");
  		for(AST_AFT ast_aft : p.getAST_AFT$TaskMap().keySet()) {
  			System.out.print("  " +ast_aft.toString() + " " + p.getAST_AFT$TaskMap().get(ast_aft).getName());
  		}
  	 	System.out.println("  ");
  	  }
	  


}
}
