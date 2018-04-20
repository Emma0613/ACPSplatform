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
 *һ�����������������app���Ϻʹ��������ϡ�
 */
public class DataSample {
	private String name;
	private int processorAmount=0;
	private int applicationAmount=0;
	private List<Processor> processorList= new ArrayList<Processor>();//
	private List<Application> applicationList= new ArrayList<Application>();//


	/**
	 * DataSample�Ĺ��캯��2
	 * @param name
	 * @param ����������
	 * @param Application����
	 */
	DataSample(String name,int processorAmount,int applicationAmount){
		this.name=name;
		this.processorAmount=processorAmount;
		this.applicationAmount=applicationAmount;
		this.processorList=new ArrayList<Processor>(processorAmount);
		this.applicationList=new ArrayList<Application>(applicationAmount);
		
	}
	

	/**
	 * ��ʼ������1����ָ���Ĳ������г�ʼ������DataSample���ʵ������
	 * @param givenProcessorList
	 * @param givenApplicationList
	 */
	public void initDataSample(List<Processor> givenProcessorList,List<Application> givenApplicationList){
		this.setProcessorList(givenProcessorList);
		this.setApplicationList(givenApplicationList);
	}
	
	/**
	 * ��ʼ������2����������ķ�����ʼ�����ݼ�
	 */
	/*
	public void initDataSample_Random() {
		//ָ�����������������������������ʼ��givenprocessorlist
		List<Processor> givenProcessorList=DataGen.genProcessorList(this.processorAmount);
		//ָ������������Ӧ���������������������еĺ��������ʼ��givenApplicationlist
		List<Application> givenApplicationList=DataGen.genApplicationList(this.processorAmount, this.applicationAmount);
		//��ָ���Ĳ������г�ʼ��
		this.initDataSample(givenProcessorList,givenApplicationList);
		
	}
	*/

	//get&set function
	//���³�Ա����ֻ���ڹ���ʱ���õı�����ֻ��get����
	public String getName() {
		return name;
	}
	public int getProcessorAmount() {
		return processorAmount;
	}
	public int getApplicationAmount() {
		return applicationAmount;
	}
	

	//���³�Ա�����ڳ����캯��֮��ĳ�ʼ�������б�set
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
