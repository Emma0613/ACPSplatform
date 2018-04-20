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
	private Application application ;//此task所属的application

	private Boolean isEntry =false;//此task是否是一个入口task
	private Boolean isExit = false;//此task是否是一个出口task
	//此task在各个ECU上的平均计算开销
	private double AvgCompCost;
	//此task

	//此task在它所属的g中的ranku值
	private double Ranku=0.0;
    //存储此task在各个cpu上的wcrt
	private LinkedHashMap<Processor, Integer> processor$CompCostMap = new LinkedHashMap<Processor, Integer>();

	//此task的前驱
	LinkedHashMap<Task, Integer> succTask$CommCostMap  = new  LinkedHashMap<Task, Integer>();
	//此task的后继
	LinkedHashMap<Task, Integer> predTask$CommCostMap  = new  LinkedHashMap<Task, Integer>();

    //构造函数，由名字构造出task
	public Task(String name) {
		this.name=name;
	}

	//get和set函数

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
