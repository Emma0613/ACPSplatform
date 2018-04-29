/**
 * 
 */
package bean;
import java.util.LinkedHashMap;
import util.AST_AFT;

/**
 * @author Emma
 *
 */
public class Task implements Comparable<Task>{
	private String name ;
	private Application application ;//此task所属的application
    private Processor assignedProcessor_heft;//单DAG heft阶段,此task在计算式被分配给的processor
    private Processor assignedProcessor_dynamic;//多DAG动态实际调度阶段,此task在计算式被分配给的processor

	private Boolean isEntry =false;//此task是否是一个入口task
	private Boolean isExit = false;//此task是否是一个出口task
	//此task在各个ECU上的平均计算开销
	private double AvgCompCost;
	//此task在被分配的cpu上的最早完成时间
	//private Double EFT;
	//此task在被分配的ECU上的的实际开始时间和实际完成时间
	private AST_AFT ast_aft = new AST_AFT();

	//此task在它所属的applicaiton中的ranku值
	private Double Ranku=0.0;
    //存储此task在各个cpu上的wcrt
	private LinkedHashMap<Processor, Integer> processor$CompCostMap = new LinkedHashMap<Processor, Integer>();
    
	//此task的直接前驱： 前驱task->task的通信时间
	LinkedHashMap<Task, Integer> succTask$CommCostMap  = new  LinkedHashMap<Task, Integer>();
	//此task的直接后继：task->后继task的通信时间
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
	
	public Processor getAssignedProcessor_heft() {
		return assignedProcessor_heft;
	}

	public void setAssignedProcessor_heft(Processor assignedProcessor_heft) {
		this.assignedProcessor_heft = assignedProcessor_heft;
	}

	public Processor getAssignedProcessor_dynamic() {
		return assignedProcessor_dynamic;
	}

	public void setAssignedProcessor_dynamic(Processor assignedProcessor_dynamic) {
		this.assignedProcessor_dynamic = assignedProcessor_dynamic;
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
    
	public void setAst_aft(AST_AFT ast_aft) {
		this.ast_aft = ast_aft;
	}

	public AST_AFT getAst_aft() {
		return ast_aft;
	}

	public Double getRanku() {
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
	


 //以下overide函数从老谢的代码里粘贴的
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Task other = (Task) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int compareTo(Task o) {
		int result =0;
		if(result==0)
			 result = -this.getRanku().compareTo(o.getRanku());
		return result;
	}

}
