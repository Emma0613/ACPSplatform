/**
 * 
 */
package bean;


import java.util.LinkedHashMap;




/**
 * @author Emma
 *
 */
public class Task{
	private String name ;
	private Application application ;//��task������application

	private Boolean isEntry =false;//��task�Ƿ���һ�����task
	private Boolean isExit = false;//��task�Ƿ���һ������task
	//��task�ڸ���ECU�ϵ�ƽ�����㿪��
	private double AvgCompCost;
	//��task

	//��task����������g�е�rankuֵ
	private double Ranku=0.0;
    //�洢��task�ڸ���cpu�ϵ�wcrt
	private LinkedHashMap<Processor, Integer> processor$CompCostMap = new LinkedHashMap<Processor, Integer>();

	//��task��ǰ��
	LinkedHashMap<Task, Integer> succTask$CommCostMap  = new  LinkedHashMap<Task, Integer>();
	//��task�ĺ��
	LinkedHashMap<Task, Integer> predTask$CommCostMap  = new  LinkedHashMap<Task, Integer>();

    //���캯���������ֹ����task
	public Task(String name) {
		this.name=name;
	}

	//get��set����

	public String getName() {
		return name;
	}
	
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	public Boolean getIsEntry() {
		return isEntry;
	}
	
	public void setIsEntry(Boolean isEntry) {
		this.isEntry = isEntry;
	}

	public Boolean getIsExit() {
		return isExit;
	}

	public void setIsExit(Boolean isExit) {
		this.isExit = isExit;
	}
	
	public double getAvgCompCost() {
		return AvgCompCost;
	}

	public void setAvgCompCost(double avgCompCost) {
		AvgCompCost = avgCompCost;
	}

	public double getRanku() {
		return Ranku;
	}

	public void setRanku(double ranku) {
		Ranku = ranku;
	}

	public LinkedHashMap<Processor, Integer> getProcessor$CompCostMap() {
		return processor$CompCostMap;
	}

	public void setProcessor$CompCostMap(LinkedHashMap<Processor, Integer> processor$CompCostMap) {
		this.processor$CompCostMap = processor$CompCostMap;
	}
	
	public LinkedHashMap<Task, Integer> getSuccTask$CommCostMap() {
		return succTask$CommCostMap;
	}

	public void setSuccTask$CommCostMap(LinkedHashMap<Task, Integer> succTask$CommCostMap) {
		this.succTask$CommCostMap = succTask$CommCostMap;
	}

	public LinkedHashMap<Task, Integer> getPredTask$CommCostMap() {
		return predTask$CommCostMap;
	}

	public void setPredTask$CommCostMap(LinkedHashMap<Task, Integer> predTask$CommCostMap) {
		this.predTask$CommCostMap = predTask$CommCostMap;
	}




}
