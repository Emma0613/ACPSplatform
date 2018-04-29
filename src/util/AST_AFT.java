/**
 * 
 */
package util;
import bean.Task;
/**
 * @author Emma
 *
 */
public class AST_AFT implements Comparable<AST_AFT>{
   private Double AST;//实际开始时间
   private Double AFT;//实际结束实际
   private Task task;

   public AST_AFT() {
	   AST = Double.MAX_VALUE;
	   AFT = Double.MAX_VALUE;
   }
   public AST_AFT(Double ast,Double aft) {
	   AST = ast;
	   AFT = aft;
   }
   
public Task getTask() {
		return task;
}
public void setTask(Task task) {
		this.task = task;
}  
public Double getAST() {
	return AST;
}
public void setAST(Double aST) {
	AST = aST;
}
public Double getAFT() {
	return AFT;
}
public void setAFT(Double aFT) {
	AFT = aFT;
}

@Override
public int compareTo(AST_AFT o) {
	// TODO Auto-generated method stub
     return (int) (this.getAST()-o.getAST());
}

@Override
public String toString() {
	String s = "("+this.getAST() +", "+this.getAFT()+")";
	return s;
	
}
   
}
