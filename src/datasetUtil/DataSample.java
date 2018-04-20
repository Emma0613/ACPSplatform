/**
 * 
 */
package datasetUtil;

import java.util.ArrayList;
import java.util.List;

import bean.Application;
import bean.Processor;

/**
 * @author Emma
 *一个数据样本，组合了app集合和处理器集合、
 */
public class DataSample {
	private String name;
	private int processorAmount=0;
	private int applicationAmount=0;
	private List<Processor> processorList= new ArrayList<Processor>();//
	private List<Application> applicationList= new ArrayList<Application>();//


	/**
	 * DataSample的构造函数2
	 * @param name
	 * @param 处理器个数
	 * @param Application个数
	 */
	DataSample(String name,int processorAmount,int applicationAmount){
		this.name=name;
		this.processorAmount=processorAmount;
		this.applicationAmount=applicationAmount;
		this.processorList=new ArrayList<Processor>(processorAmount);
		this.applicationList=new ArrayList<Application>(applicationAmount);
		
	}
	

	/**
	 * 初始化方法1，由指定的参数进行初始化。被DataSample类的实例调用
	 * @param givenProcessorList
	 * @param givenApplicationList
	 */
	public void initDataSample(List<Processor> givenProcessorList,List<Application> givenApplicationList){
		this.setProcessorList(givenProcessorList);
		this.setApplicationList(givenApplicationList);
	}
	
	/**
	 * 初始化方法2，用随机化的方法初始化数据集
	 */
	/*
	public void initDataSample_Random() {
		//指定处理器数，调用其他函数随机初始化givenprocessorlist
		List<Processor> givenProcessorList=DataGen.genProcessorList(this.processorAmount);
		//指定处理器数和应用数，调用数据生成类中的函数随机初始化givenApplicationlist
		List<Application> givenApplicationList=DataGen.genApplicationList(this.processorAmount, this.applicationAmount);
		//由指定的参数进行初始化
		this.initDataSample(givenProcessorList,givenApplicationList);
		
	}
	*/

	//get&set function
	//以下成员变量只能在构造时设置的变量，只有get方法
	public String getName() {
		return name;
	}
	public int getProcessorAmount() {
		return processorAmount;
	}
	public int getApplicationAmount() {
		return applicationAmount;
	}
	

	//以下成员变量在除构造函数之外的初始化函数中被set
	public List<Application> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}
	public List<Processor> getProcessorList() {
		return processorList;
	}
	public void setProcessorList(List<Processor> processorList) {
		this.processorList = processorList;
	}
}
