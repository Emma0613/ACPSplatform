/**
 * 
 */
package bean;

import java.util.LinkedHashMap;


/**
 * @author Emma
 *
 */
public class Task {
	private String name ;
	private Application application ;//此task所属的application

	private Boolean isEntry =false;
	private Boolean isExit = false;

	private double Ranku=0.0;

	private LinkedHashMap<Processor, Integer> processor$CompCostMap = new LinkedHashMap<Processor, Integer>();


	//task的前驱
	LinkedHashMap<Task, Integer> succTask$CommCostMap  = new  LinkedHashMap<Task, Integer>();

	//task的后继
	LinkedHashMap<Task, Integer> predTask$CommCostMap  = new  LinkedHashMap<Task, Integer>();

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

	public void setIsExit(Boolean isExit) {
		this.isExit = isExit;
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
