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
	private Application application ;//��task������application
    private Processor assignedProcessor_heft;//��DAG heft�׶�,��task�ڼ���ʽ���������processor
    private Processor assignedProcessor_dynamic;//��DAG��̬ʵ�ʵ��Ƚ׶�,��task�ڼ���ʽ���������processor

	private Boolean isEntry =false;//��task�Ƿ���һ�����task
	private Boolean isExit = false;//��task�Ƿ���һ������task
	//��task�ڸ���ECU�ϵ�ƽ�����㿪��
	private double AvgCompCost;
	//��task�ڱ������cpu�ϵ��������ʱ��
	//private Double EFT;
	//��task�ڱ������ECU�ϵĵ�ʵ�ʿ�ʼʱ���ʵ�����ʱ��
	private AST_AFT ast_aft = new AST_AFT();

	//��task����������applicaiton�е�rankuֵ
	private Double Ranku=0.0;
    //�洢��task�ڸ���cpu�ϵ�wcrt
	private LinkedHashMap<Processor, Integer> processor$CompCostMap = new LinkedHashMap<Processor, Integer>();
    
	//��task��ֱ��ǰ���� ǰ��task->task��ͨ��ʱ��
	LinkedHashMap<Task, Integer> succTask$CommCostMap  = new  LinkedHashMap<Task, Integer>();
	//��task��ֱ�Ӻ�̣�task->���task��ͨ��ʱ��
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
	


 //����overide��������л�Ĵ�����ճ����
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
