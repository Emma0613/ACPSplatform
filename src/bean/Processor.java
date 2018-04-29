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
	private LinkedHashMap<AST_AFT,Task> AST_AFT$TaskMap = new LinkedHashMap<AST_AFT,Task>();//Ϊ��������Ƚ����
	private LinkedHashMap<Task, AST_AFT> Task$AST_AFTMap = new LinkedHashMap<Task, AST_AFT>();//Ϊ�˼���������Ĳ���slot��
	private List<AST_AFT> busySlotlist = new ArrayList<AST_AFT>();//�洢procesor�ϵ�æʱ��
	
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
    //��busySlotlist�����slot������֮���Զ�����
	public void addBusySlot_to_BusySlotList(AST_AFT busySlot) {
		this.getBusySlotlist().add(busySlot);
        Collections.sort(this.getBusySlotlist(), new Comparator<AST_AFT>() {
     	   public int compare(AST_AFT slot1,AST_AFT slot2) {
     		   return slot1.getAST().compareTo(slot2.getAST());//��������
     	   }
        });
	}
	
	//getter��setter����
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
