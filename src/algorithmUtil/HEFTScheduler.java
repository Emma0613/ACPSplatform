/**
 * 
 */
package algorithmUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import util.AST_AFT;
import util.EST_EFT;
import bean.*;
import util.JdasQueue;
/**
 * HEFT调度器是针对单个Application的，对单个DAG进行异构多处理器调度。
 * 对多DAG动态调度来说，本工程主要调用其中的scheduling函数，以完成对Application的ranku计算、任务优先级队列初始化、Application的lowerbound的获取（以便设计deadline）
 * @author Emma
 *
 */
public class HEFTScheduler {

	private static JdasQueue<Task> unscheduledTaskQueue = new JdasQueue<Task>();//调度器维护的调度队列，各个未调度任务按ranku降序排列
	
	/**
	 * 总的调度函数
	 * @param application
	 */
	public static void scheduling(Application application, List<Processor> givenProcessorList) {
		
		setRanku(application);
		initUnscheduledTaskQueue(application);
		allocationTask_toProcessors(givenProcessorList);
		
	}

	
	/**
     * 1.针对一个Application，计算task里面的task的ranku
     * @param application
     */
	public static  void setRanku(Application application) {
            //由出口任务往上计算,
			for (int i = application.getTaskList().size() - 1; i >= 1; i--) {
				Task currentTask = application.getTaskList().get(i);
				LinkedHashMap<Task, Integer> succCommCosts = currentTask.getSuccTask$CommCostMap();
				Set<Task> succTaskSet = succCommCosts.keySet();//取出后继任务的集合

				double tempRanku = 0.00;//中间变量，存储计算中的ranku值
				if (succTaskSet != null && succTaskSet.size() > 0) {

					for (Task succTask : succTaskSet) {
						Double r = succTask.getRanku();//后继任务的ranku
						Integer c = currentTask.getSuccTask$CommCostMap().get(succTask); //当前任务到后继任务的通信开销
                        //去掉了源代码里面r和c的为null判断
						if (r + c > tempRanku) {
							tempRanku = (r + c);
						}
					}
				}
				//ranku向上取整
				//currentTask.setRanku(Math.ceil(currentTask.getAvgCompCost() + tempRanku * 1) / 1.0); 
			   tempRanku+=currentTask.getAvgCompCost();
			   currentTask.setRanku(tempRanku);//不用小数
				/*这里有点问题，原来的，注释掉了
				if(application.getExitTask().getName().endsWith("n_10"))//HEFT原例子修正
					currentTask.setRanku(Math.ceil(currentTask.getAvgCompCost() + tempRanku * 1) / 1.0); 
				else
					currentTask.setRanku(currentTask.getAvgCompCost() + tempRanku);
					*/
			}
			//计算完后，全部的值都向上取整
			/*
			for(Task t:application.getTaskList()) {
				t.setRanku(Math.ceil(t.getRanku())/1.0);		
			}*/

		}
	
	
	
	/**
	 * 2.初始化任务优先级队列:把app里的任务入队到调度器的unscheduledTaskQueue
	 * @param application
	 */
	public static void initUnscheduledTaskQueue(Application app) {
		unscheduledTaskQueue.clear();//把调度器的未调度队列清空
		app.getTaskList().remove(0);//把tasklist里的第一个任务 即空任务排空
		//将调度器的未调度队列填充并初始化，即按照ranku降序排列
		for(Task t:app.getTaskList()) {
			unscheduledTaskQueue.add(t);
		}
		Collections.sort(unscheduledTaskQueue, new Comparator<Task>() {//按照ranku降序排列
			public int compare(Task o1, Task o2) {
				return -o1.getRanku().compareTo(o2.getRanku()); // 
			}
		});
	}
	
	
	/**
	 * 3.处理器选择阶段即任务分配阶段：对调度器的unscheduledTaskQueue中的任务，顺序进行分配
	 * @param givenProcessorList
	 * 本函数计算出一个app的task在给定的processor集合上的分配，并计算出此app的lowerbound
	 */
	public static void allocationTask_toProcessors(List<Processor> givenProcessorList) {
		givenProcessorList.remove(0);//index为0的处理器是虚的，为了循环方便 排空
		//当调度器的待调度队列不为空，从中取出任务 并进行分配处理器
		while(!unscheduledTaskQueue.isEmpty()) {
			//出队
			Task currentTask= unscheduledTaskQueue.out();
			Double minEFT=Double.MAX_VALUE;
			Processor assignedProcessor=null;
			//计算arg min EFT(currentTask,pk)的EFT和相关的处理器pk,赋值给ast aft
			for(Processor p : givenProcessorList) {
				Double x =computeEST_EFT(currentTask,p).getEFT();//还没实现完
				if(x<minEFT) {//找使得最早完成时间最小的那个处理器
					minEFT=x;
					assignedProcessor=p;//选定处理器
				}
			}
			  //中间变量赋值
			Integer compCost_currentTask$p =currentTask.getProcessor$CompCostMap().get(assignedProcessor);//W ni->pj
			//对任务currentTask的参数填充
			currentTask.setAssignedProcessor_heft(assignedProcessor);//标定currentTask最终被分配给的处理器
		    currentTask.getAst_aft().setAFT(minEFT);//标定current实际结束时间
			currentTask.getAst_aft().setAST(minEFT - compCost_currentTask$p);//标定实际开始时间
		    //对处理器assignedProcessor的参数填充
			assignedProcessor.getTask$AST_AFTMap().put(currentTask, currentTask.getAst_aft());
            assignedProcessor.getAST_AFT$TaskMap().put(currentTask.getAst_aft(), currentTask);
            assignedProcessor.addBusySlot_to_BusySlotList(currentTask.getAst_aft());//标记出p上的busy时间段,此时list有序的
            //对currentTask所在Application参数填充（设置lowerbound）
            if(currentTask.getIsExit()) {
            	currentTask.getApplication().setLowerBound(currentTask.getAst_aft().getAFT());
            }
		    
		}
	}
	
	/**
	 * 对于unscheduledTaskQueue里出队的一个任务task ni，计算它在各个处理器上的EFT，即使EFT（ni，pk）
	 */
    public static EST_EFT computeEST_EFT(Task currentTask, Processor p) {
    	   
    	   EST_EFT  EST_EFTt$p = new EST_EFT();//currentTask 在 p上的最早开始时间和最早结束时间，slot
  		   Double readyTime_t$p = 0.0;//任务currentTask在处理器p上的最早就绪时间

    	   //Step1.求任务currentTask在处理器p上的最早就绪时间readyTime_t$p
    	   if(currentTask.getIsEntry()==true) {//如果是入口任务，则无前驱任务，最早就绪时间为0.0，
    		   readyTime_t$p = 0.0;
    	   }
    	   else {
    		   // 非入口任务（涉及已经被调度的前驱任务的实际完成时间、通信时间；然后去最大）
                for(Task t : currentTask.getPredTask$CommCostMap().keySet() ) {
                	Integer commTime =0;//t与currentTask的实际通信开销
                	if(!t.getAssignedProcessor_heft().equals(p)) {
                		commTime = t.getSuccTask$CommCostMap().get(currentTask);//不在同一p上，加通信时间
                	}
                	//***测试
                	//+++
                	Double readyTemp = t.getAst_aft().getAFT() + commTime;//当前任务的就绪时间为前面任务的实际完成时间加实际通信开销
                	readyTime_t$p = Math.max(readyTime_t$p, readyTemp);//取最大
                	//***加一行测试代码
 
                } 		   
    	   }
    	   //Step2.从上步得到的readyTime_t$p往后开始搜索处理器的可用idleSlot，即EST_EFTt$p
    	   EST_EFTt$p = searchFirstAvalibleIdleSlot_InsertPolicy(currentTask, p, readyTime_t$p);    
    	   return EST_EFTt$p;
   }

    /**
     * 对currentTask搜索他在处理器p上的上的 使得EFT最小的那个slot
     * @param currentTask
     * @param p
     * @param readyTime_t$p
     * @return
     */
    public static EST_EFT searchFirstAvalibleIdleSlot_InsertPolicy( Task currentTask,Processor p, Double readyTime_t$p) {
    	EST_EFT firstAvalibleIdleSlot = new EST_EFT();
        Integer slotLength = currentTask.getProcessor$CompCostMap().get(p);
    	List<AST_AFT> busyTimeSlotList = p.getBusySlotlist();
    	
    	//情况1：p上目前还没有安排任何任务，忙时列表为空，无需查找空隙，直接设置EST_EFT,结束函数，return
    	if(busyTimeSlotList.isEmpty()) {
    		firstAvalibleIdleSlot.setEST(readyTime_t$p);
    		firstAvalibleIdleSlot.setEFT(readyTime_t$p + slotLength);
    	}else if(readyTime_t$p>=(busyTimeSlotList.get(busyTimeSlotList.size()-1).getAFT())) {
        //情况2：p上有任务安排，忙时列表不为空，但readyTime_t$p 在时间安排表结束时间之后
    		firstAvalibleIdleSlot.setEST(readyTime_t$p);
    		firstAvalibleIdleSlot.setEFT(readyTime_t$p + slotLength);
    	}else {
        //情况3：p上目前有任务安排，忙时列表不为空，readyTime_t$p在列表时间线以内,则设置搜索点，对每一个busySlot进行遍历，计算是否有可用
        	double searchPoint = readyTime_t$p.doubleValue();//搜索点从readyTime_t$p任务就绪时间开始
        	boolean foundIt = false;//是否在时间表内部找到firstAvalibleIdleSlot
    		for(int index=0; index < busyTimeSlotList.size(); index++) {
    			AST_AFT currentBusySlot = busyTimeSlotList.get(index);//当前busySlot
    			//3.1 searchPoint在某个busyslot结束时刻的前面
    			if(searchPoint < currentBusySlot.getAFT()) {//找到such a busySlot,
    				if((currentBusySlot.getAST()-searchPoint)>=slotLength) {//Q1：看看searchPoint是否在此busySlot的开始时间前面，且直接idleslot可以放置本任务？
    					firstAvalibleIdleSlot.setEST(searchPoint);//Q1yes,找到第一个可用的idleSlot，标记此slot
    					firstAvalibleIdleSlot.setEFT(searchPoint+slotLength);
    					foundIt = true;
    					break;//结束搜索
    				}else {//Q1 no,意味着searchPoint在此busySlot的中间
    					searchPoint = currentBusySlot.getAFT();
    					continue;//此busySlot前方的idleslot长度不符合要求，跳过以下语句，在for循环中继续检查下一个busyslot
    				}        
    			}
    	    }
    		//情况3中，for循环搜遍了时间表，也无可用空隙，此时searchPoint被置于了时间表末尾，只能将currentTask接到最后
    		if(foundIt==false) {
        		firstAvalibleIdleSlot.setEST(searchPoint);
        		firstAvalibleIdleSlot.setEFT(searchPoint+slotLength);
    		}
    	}
    	    	
    	//返回firstAvalibleIdleSlot
		return firstAvalibleIdleSlot;
	
    }


	public static JdasQueue<Task> getUnscheduledTaskQueue() {
		return unscheduledTaskQueue;
	}
    
    //getter和setter
    

}


