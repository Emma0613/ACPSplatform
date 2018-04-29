/**
 * 
 */
package bean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import util.AST_AFT;

/**
 * @author Emma
 *
 */
public class Processor {
	private String name;
	private LinkedHashMap<AST_AFT,Task> AST_AFT$TaskMap = new LinkedHashMap<AST_AFT,Task>();//为了输出调度结果用
	private LinkedHashMap<Task, AST_AFT> Task$AST_AFTMap = new LinkedHashMap<Task, AST_AFT>();//为了计算新任务的插入slot用
	private List<AST_AFT> busySlotlist = new ArrayList<AST_AFT>();//存储procesor上的忙时段
	
	public Processor(String string) {
		name=string;
    	AST_AFT$TaskMap.clear();
    	Task$AST_AFTMap.clear();
	}
	//
    public void clear() {
    	AST_AFT$TaskMap.clear();
    	Task$AST_AFTMap.clear();
    }
    //往busySlotlist里加入slot，加入之后自动排序
	public void addBusySlot_to_BusySlotList(AST_AFT busySlot) {
		this.getBusySlotlist().add(busySlot);
        Collections.sort(this.getBusySlotlist(), new Comparator<AST_AFT>() {
     	   public int compare(AST_AFT slot1,AST_AFT slot2) {
     		   return slot1.getAST().compareTo(slot2.getAST());//升序排列
     	   }
        });
	}
	
	//getter和setter函数
	public String getName() {
		return name;
	}
		
	public LinkedHashMap<AST_AFT, Task> getAST_AFT$TaskMap() {
		return AST_AFT$TaskMap;
	}

	public LinkedHashMap<Task, AST_AFT> getTask$AST_AFTMap() {
		return Task$AST_AFTMap;
	}

	public List<AST_AFT> getBusySlotlist() {
		return busySlotlist;
	}
	


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	

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
		final Processor other = (Processor) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
