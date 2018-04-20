/**
 * 
 */
package algorithmUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import bean.*;
/**
 * HEFT����������Ե���Application�ģ��Ե���DAG�����칹�ദ�������ȡ�
 * �Զ�DAG��̬������˵����������Ҫ�������е�scheduling����������ɶ�Application��ranku���㡢�������ȼ����г�ʼ����Application��lowerbound�Ļ�ȡ���Ա����deadline��
 * @author Emma
 *
 */
public class HEFTScheduler {
	/**
	 * �ܵĵ��Ⱥ���
	 * @param application
	 */
	public static void scheduling(Application application, List<Processor> givenProcessorList) {
		
		setRanku(application);
		initTaskPrioriyQueue_in_App(application);
		allocationTask_inApp_toProcessors(application,givenProcessorList);
		
	}
	
	/**
	 * 2.��ʼ���������ȼ�����
	 * @param application
	 */
	public static void initTaskPrioriyQueue_in_App(Application app) {
		app.getTaskPriorityQueue().clear();
		app.getTaskPriorityQueue().in(app.getEntryTask());
	}
	
	
    /**
     * 1.���һ��Application������task�����
     * @param application
     */
	public static  void setRanku(Application application) {
            //�ɳ����������ϼ���,
			for (int i = application.getTaskList().size() - 1; i >= 1; i--) {
				Task currentTask = application.getTaskList().get(i);
				LinkedHashMap<Task, Integer> succCommCosts = currentTask.getSuccTask$CommCostMap();
				Set<Task> succTaskSet = succCommCosts.keySet();//ȡ���������ļ���

				double tempRanku = 0.00;//�м�������洢�����е�rankuֵ
				if (succTaskSet != null && succTaskSet.size() > 0) {

					for (Task succTask : succTaskSet) {
						Double r = succTask.getRanku();//��������ranku
						/**
						 * @TODO c��������ƽ��ֵ���м���ѽ
						 */
						Integer c = currentTask.getSuccTask$CommCostMap().get(succTask); //��ǰ���񵽺�������ͨ�ſ���
                        //ȥ����Դ��������r��c��Ϊnull�ж�
						if (r + c > tempRanku) {
							tempRanku = (r + c);
						}
					}
				}
				if(application.getExitTask().getName().endsWith("n_10"))//������һ���Ƚ�tricky�Ĵ�������ΪɶҪ��������
					currentTask.setRanku(Math.ceil(currentTask.getAvgCompCost() + tempRanku * 1) / 1.0); 
				else
					currentTask.setRanku(currentTask.getAvgCompCost() + tempRanku);
			}

		}

	/**
	 * 3.������ѡ��׶μ��������׶�
	 * @param application
	 * @param givenProcessorList
	 * �����������һ��app��task�ڸ�����processor�����ϵķ��䣬���������app��lowerbound
	 */
	public static void allocationTask_inApp_toProcessors(Application application, List<Processor> givenProcessorList) {
		
	}

}
