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
 * HEFT�㷨ԭ�����õ�DAG���ӣ����ļ��������HEFTԭ�ĵ�����
 * @author Emma
 *
 */
public class exampleinHEFT {

public static void main(String[] args) throws IOException, ClassNotFoundException {
 
	//1.�������б�
	List<Processor> givenProcessorList = new ArrayList<Processor>();
	for (int i = 0; i < 4; i++) {
		Processor p = new Processor("p" + i);
		givenProcessorList.add(p);
	}
	//2.��������ͨ�ž���11*4,11*11, 10������3�������� heftԭ�ĵ�����
    Integer[][] compMartrix2 = new Integer[][] {{0,0,0,0},{0,14,16,9},{0,13,19,18},{0,11,13,19},{0,13,8,17},{0,12,13,10},{0,13,16,9},{0,7,15,11},{0,5,11,14},{0,18,12,20},{0,21,7,16}};
    Integer[][] commMartrix2 = new Integer[][] {{0,0,0,0,0,0,0,0,0,0,0},{0,0,18,12,9,11,14,0,0,0,0},{0,0,0,0,0,0,0,0,19,16,0},
    	{0,0,0,0,0,0,0,23,0,0,0},{0,0,0,0,0,0,0,0,27,23,0},{0,0,0,0,0,0,0,0,0,13,0},
    	{0,0,0,0,0,0,0,0,15,0,0},{0,0,0,0,0,0,0,0,0,0,17},{0,0,0,0,0,0,0,0,0,0,11},{0,0,0,0,0,0,0,0,0,0,13},{0,0,0,0,0,0,0,0,0,0,0}};
    
    //3.��ʼ��Application	
    Application app = new Application("F2");
    app.initApplication_withoutDeadline(compMartrix2, commMartrix2, Criticality.S1, 10.0);
    DataGen.initTask_in_Application(app, givenProcessorList, compMartrix2, commMartrix2);
    //4������
	  HEFTScheduler.setRanku(app);
	  //Task testTask =app.getTaskList().get(3);
	 // testTask.setRanku(81);
  	 // System.out.println(testTask.getName());
	  HEFTScheduler.initUnscheduledTaskQueue(app);
  	  //���Եڶ����ĵ�������
  	  System.out.println("�������ĵ��ȶ���:");
  	  for(Task t : HEFTScheduler.getUnscheduledTaskQueue()) {
  		System.out.println(t.getName() + ", " + "��rankuΪ: " + t.getRanku());
  	  }
  	 
	  HEFTScheduler.allocationTask_toProcessors(givenProcessorList);
	  
    
    //5.��������ʾ
	  System.out.println("����"+app.getName());
	  Integer[][] compMatrix = app.getComputingMartrix();
	  System.out.println("	����������" + compMatrix[0].length);
	  System.out.println("	������������" + app.getComputingMartrix().length + "	" +app.getCommunicationMatrix().length +" "+app.getTaskList().size()); 
	  System.out.println("	�������ͳ�������" + app.getEntryTask().getName() + " " +app.getExitTask().getName());
	  System.out.println("	lowerbound��" + app.getLowerBound());
	  System.out.println("	����������Ϊ��");
	  
	  for(Task task:app.getTaskList()) {
		System.out.print(task.getName() + ", " + "��rankuΪ: " + task.getRanku());
		System.out.print(", ��������Ϊ��");
		for(Task t :task.getSuccTask$CommCostMap().keySet()) {
			System.out.print(t.getName()+", ");
		}
		System.out.print(" ��ǰ������Ϊ��");
		for(Task t:task.getPredTask$CommCostMap().keySet()) {
			System.out.print(t.getName()+", ");
		}
  	  System.out.println("  ");
	  }
	  

  	  //���Եڶ����ĵ�������
  	  System.out.println("�������ĵ��ȶ��� ֮��:");
  	  for(Task t : HEFTScheduler.getUnscheduledTaskQueue()) {
  		System.out.println(t.getName() + ", " + "��rankuΪ: " + t.getRanku());
  	  }
  	  //����lowerbound
  	    System.out.println("app lowerbound = "+app.getLowerBound());
  	  //��ʾ��������
  	  for(Processor p : givenProcessorList) {
  		System.out.println(p.getName()+"����������У�");
  		for(Task t : p.getTask$AST_AFTMap().keySet()) {
  			System.out.print("  " + t.getName() + " " + p.getTask$AST_AFTMap().get(t).toString() );
  		}
    	System.out.println("  ");
  		System.out.println(p.getName()+"����������У�");
  		for(AST_AFT ast_aft : p.getAST_AFT$TaskMap().keySet()) {
  			System.out.print("  " +ast_aft.toString() + " " + p.getAST_AFT$TaskMap().get(ast_aft).getName());
  		}
  	 	System.out.println("  ");
  	  }
	  


}
}
