/**
 * 
 */
package algorithmUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import bean.*;
/**
 * HEFT调度器是针对单个Application的，对单个DAG进行异构多处理器调度。
 * 对多DAG动态调度来说，本工程主要调用其中的scheduling函数，以完成对Application的ranku计算、任务优先级队列初始化、Application的lowerbound的获取（以便设计deadline）
 * @author Emma
 *
 */
public class HEFTScheduler {
	/**
	 * 总的调度函数
	 * @param application
	 */
	public static void scheduling(Application application, List<Processor> givenProcessorList) {
		
		setRanku(application);
		initTaskPrioriyQueue_in_App(application);
		allocationTask_inApp_toProcessors(application,givenProcessorList);
		
	}
	
	/**
	 * 2.初始化任务优先级队列
	 * @param application
	 */
	public static void initTaskPrioriyQueue_in_App(Application app) {
		app.getTaskPriorityQueue().clear();
		app.getTaskPriorityQueue().in(app.getEntryTask());
	}
	
	
    /**
     * 1.针对一个Application，计算task里面的
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
						/**
						 * @TODO c本来该用平均值进行计算呀
						 */
						Integer c = currentTask.getSuccTask$CommCostMap().get(succTask); //当前任务到后继任务的通信开销
                        //去掉了源代码里面r和c的为null判断
						if (r + c > tempRanku) {
							tempRanku = (r + c);
						}
					}
				}
				if(application.getExitTask().getName().endsWith("n_10"))//这里是一个比较tricky的处理方法，为啥要这样处理
					currentTask.setRanku(Math.ceil(currentTask.getAvgCompCost() + tempRanku * 1) / 1.0); 
				else
					currentTask.setRanku(currentTask.getAvgCompCost() + tempRanku);
			}

		}

	/**
	 * 3.处理器选择阶段即任务分配阶段
	 * @param application
	 * @param givenProcessorList
	 * 本函数计算出一个app的task在给定的processor集合上的分配，并计算出此app的lowerbound
	 */
	public static void allocationTask_inApp_toProcessors(Application application, List<Processor> givenProcessorList) {
		
	}

}
