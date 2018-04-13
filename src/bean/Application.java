/**
 * 
 */
package bean;
import system.SystemParameters;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import bean.Criticality;
import bean.Application;
import bean.Task;
import util.*;

/**
 * @author Emma
 *
 */
public class Application {

	/**Application的基本数据**/
	private String name;//app的名字
	private Integer criticality;//应用的关键级
	private double arrivalTime=0d;//应用的到达时刻,默认值为0时刻
	private double relativeDeadline=0d;//application的截止时长（绝对）
	private List<Task> taskList=new ArrayList<Task>(); ;//application所包含的任务集合
	private Integer[][] communicationMatrix;//任务图，其中边上的权值表示通信时间
	private Integer[][] computingMatrix;//WCRT矩阵，存储某个任务i在ECUj上的wcrt
	
	/**Application的衍生数据*/
	private double absDeadline;//是arriveTime与deadline的和，在生成数据的函数里进行计算
	private Task entryTask;//application任务图的入口任务
	private Task exitTask;//application任务图的出口任务
	private List<Task> taskOrigialList = new ArrayList<Task>(); //按照communicationMatrix来的原始任务列表
	private JdasQueue<Task> taskPriorityQueue = new JdasQueue<Task>(); //根据ranku排的优先级列表
	
	//构造函数1：
	 public Application(String name){
		this.name=name;
	}


/**
 * 初始化方法1，由指定的参数对一个Application进行初始化，此时deadline还未计算（要通过lowerboud进行设置，即heft算法之后）。
 * 本方法初始化application中的task列表
 * @param computingMartrix   计算矩阵，即Application的wcrt矩阵
 * @param communicationMartrix 通信矩阵，即Application以任务(点)的邻接矩阵表示。communicationMartrix[i][j]的值表示任务i到任务j的通信时间（不在同一ecu上时）
 * @param criticality
 * @param arrivalTime

 */
public void initApplication_withoutDeadline(Integer[][] computingMatrix, Integer[][] communicationMatrix,Integer criticality, double arrivalTime) {
	
	   setComputingMatrix(computingMatrix);
	   setCommunicationMatrix(communicationMatrix);
	   setCriticality(criticality);
	   setArrivalTime(arrivalTime);   
	   }

	

/**一些set和get函数*自动生成*/

   //名称
	public String getName() {
		return name;
	}

    //关键级
	public Integer getCriticality() {
		return criticality;
	}

	public void setCriticality(Integer criticality) {
		this.criticality = criticality;
	}

	//arrivalTime
	public double getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	//相对截止期限
	public void setRelativeDeadline(double relativeDeadline) {
	    this.relativeDeadline = relativeDeadline;
		}
	
	public double getRelativeDeadline() {
		return relativeDeadline;
	}
	
	//入口任务
	public Task getEntryTask() {
		return entryTask;
	}


	public void setEntryTask(Task entryTask) {
		this.entryTask = entryTask;
	}

    //出口任务
	public Task getExitTask() {
		return exitTask;
	}

	public void setExitTask(Task exitTask) {
		this.exitTask = exitTask;
	}

	
	//任务列表
	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}
	
	//任务优先级队列
	public JdasQueue<Task> getTaskPriorityQueue() {
		return taskPriorityQueue;
	}

	//任务原始队列
	public List<Task> getTaskOrigialList() {
		return taskOrigialList;
	}

    //通信矩阵
	public Integer[][] getCommunicationMatrix() {
		return communicationMatrix;
	}

	public void setCommunicationMatrix(Integer[][] communicationMatrix) {
		this.communicationMatrix = communicationMatrix;
	}
   //计算矩阵
	public Integer[][] getComputingMartrix() {
		return computingMatrix;
	}

	public void setComputingMatrix(Integer[][] computationMatrix) {
		this.computingMatrix = computationMatrix;
	}

}
