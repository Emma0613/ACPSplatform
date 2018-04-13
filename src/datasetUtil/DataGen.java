package datasetUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import bean.*;
import cn.edu.hnu.esnl.bean.Application;
import cn.edu.hnu.esnl.bean.Processor;
import cn.edu.hnu.esnl.bean.Task;


/**
 * 生成处理器列表、生成计算矩阵和通信矩阵、生成
 * 全是服务函数。没有新的类。
 * */
public class DataGen {
	/**
	 * 指定DataSample的名字 大小 起始时间，随机初始化一个实例
	 * @author Emma
	 * @param name
	 * @param processorAmount
	 * @param applicationAmount
	 * @param startInstant
	 * @param endInstant
	 * @return
	 */
	public static DataSample genDataSample_Random(String name, int processorAmount, int applicationAmount) {
		//new一个数据集
		DataSample dataSample = new DataSample(name, processorAmount, applicationAmount);
		//初始化它
		List<Processor> givenProcessorList = DataGen.genProcessorList(processorAmount);
		List<Application> givenApplicationList = DataGen.genApplicationList(processorAmount, applicationAmount);
		dataSample.initDataSample(givenProcessorList, givenApplicationList);
		//序列化它
		/*
		 * 待补充序列化函数
		 */
		
		return dataSample;	//返回值类型另行修改
	}
	
	/**
	 * 测试函数
	 * @author Emma
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	 String name = "testSample";
	 int processorAmount = 6;
	 int applicationAmount = 15;
     DataSample testSample = DataGen.genDataSample_Random(name, processorAmount, applicationAmount);
  
     System.out.println("-----------test begin--------- ");   
     System.out.println("test Sample name is: "+ testSample.getName());
     List<Processor> processorList = testSample.getProcessorList();
     List<Application> applicationList = testSample.getApplicationList();
     
     System.out.println("-----------test processorList--------- ");   
     System.out.println("处理器数量 ="+processorList.size());
     System.out.println("处理器名字");
      for(Processor p: processorList) {
    	  System.out.println("处理器"+p.getName());
      }
      
      System.out.println("-----------test applicationList--------- ");   
      System.out.println("application数量 ="+applicationList.size());    
	
	  for(Application app: applicationList) {
  	  System.out.println("功能"+app.getName());
  	  System.out.println("	所含任务数量"+app.getComputingMartrix().length +"	"+app.getCommunicationMatrix().length);     
  	  Integer[][] compMatrix =app.getComputingMartrix();
  	  System.out.println("	处理器数量"+compMatrix[0].length);
  	  
	  }
	  
      }
	
	/**
	 * 生成处理器列表
	 * @param processorAmount
	 * @return 处理器列表
	 */
    public  static List<Processor> genProcessorList(int processorAmount){
    	List<Processor> givenProcessorList = new ArrayList<Processor>();
		for (int i = 0; i < processorAmount; i++) {
			Processor p = new Processor("p" + (i + 1));
			givenProcessorList.add(p);
		}
    	return  givenProcessorList;
    }

    /**
     * 生成Application列表，对属性数据随机初始化.
     * @warning Application数量100个起，否则不好形成并发
     * @warning 默认仿真起始时间0到10000us
     * @param processorAmount
     * @param applicationAmount
     * @return
     */
   public static List<Application> genApplicationList(int processorAmount, int applicationAmount){
	   //new出givenApplication List并命名Application
	   List<Application> givenApplicationList = new ArrayList<Application>();
	   
	   //随机初始化出填充Application_List需要的一列必要参数
	   
	   //计算矩阵 通信矩阵 到达时间列表 关键级列表
		List<Integer[][]> compMatrix_List = new ArrayList<Integer[][]>();
		List<Integer[][]> commMatrix_List = new ArrayList<Integer[][]>();
		List<Integer> arrivltime_List = new ArrayList<Integer>();
		List<Integer> criticality_List = new ArrayList<Integer>();
        
		//定下随机初始化中所使用到的基本参数
		Integer taskAmount = 4; // 改了任务数量，变成小的了
		int cost_lower = 100;//计算矩阵和通信矩阵通用的cost_upper和cost_lower用于随机生成数据用
		int cost_upper = 300;
		int arrival_span = 100;
		int arrivalInstant_number =100;   //arrival interval
		List<Integer> criticalities = new ArrayList<Integer>();
			criticalities.add(Criticality.S0);
			criticalities.add(Criticality.S1);
			criticalities.add(Criticality.S2);
			criticalities.add(Criticality.S3);
	
		
		//开始生成随机化参数数据的列表 以便下一步进行对Application列表的初始化赋值
		for(int index=1; index<=applicationAmount; index++) {
			//1.单个application含的任务数初始化
			taskAmount = 7 + new Random().nextInt(taskAmount);//这里变了任务数量的生成方法，变成小的应用了
			//2.application的计算矩阵初始化wcrt矩阵
			Integer[][] compMatrix= new Integer[taskAmount][processorAmount];
			for (int i = 0; i < taskAmount; i++) {
				for (int j = 0; j < processorAmount; j++) {
					compMatrix[i][j] = cost_lower + new Random().nextInt(cost_upper);
				}
			}
			
			//3.此application的通信矩阵初始化，taski 和 task j在不同处理器上执行时需要的通信开销
			Integer[][] commMatrix= new Integer[taskAmount][taskAmount];
			LinkedHashMap<Integer, Integer> ssMap = new LinkedHashMap<Integer, Integer>();//用来设置后继节点的个数
			for (int i = 0; i < taskAmount; i++) {
				for (int j = 0; j < taskAmount; j++) {
					
					commMatrix[i][j] = 0;
					if (i < j && (j - i <= 10)) {
						int r = new Random().nextInt(cost_upper);
						Integer v = ssMap.get(i);
						if (v == null) {	
							commMatrix[i][j] = cost_lower + new Random().nextInt(cost_upper);
							ssMap.put(i, 1);
						} else {
							int num = ssMap.get(i);
							if (num < 3 && ((r % 3 == 0) || (j + 1 == taskAmount))) {
								commMatrix[i][j] = cost_lower + new Random().nextInt(cost_lower);
								ssMap.put(i, num + 1);
							} else {
								commMatrix[i][j] = 0;
							}
						}
					}
					if (i == taskAmount - 1) {//出口任务
						commMatrix[i][j] = 0;
					}
					
				}
			}
			
			//4.将为单个Application的已经随机初始化的一组参数存进为ApplicationList准备的一组参数列表里
			compMatrix_List.add(compMatrix);
			commMatrix_List.add(commMatrix);
			
			int arrivltime = arrival_span*new Random().nextInt(arrivalInstant_number);
			arrivltime_List.add(arrivltime);
			
			int criticality = index % criticalities.size();
			criticality_List.add(criticality);

		}
		
		//用以上构造好了的参数列表，填充Application列表
		for (Integer index = 1; index <= applicationAmount; index++) {
			Integer[][] compMatrix = compMatrix_List.get(index-1);
		  	Integer[][] commMatrix = commMatrix_List.get(index-1);
			int arrivaltime = arrivltime_List.get(index-1);
			int criticality = criticality_List.get(index-1);
			
			Application g = new Application("F_" + index);
			//只做了赋值没有对task进行初始化
			g.initApplication_withoutDeadline(compMatrix, commMatrix, criticality, arrivaltime);
			givenApplicationList.add(g);
		}
		
	   return givenApplicationList;
   }
   
	public static void initTask_in_Application(Application g, List<Processor> commonProcessorList, Integer[][] computingCostMartrix, Integer[][] communicationCostMartrix) {
		/**
		 * 对于computingCostMartrix（WCRT矩阵）的每一行，创建并初始化一些task对象，用这些task来填充对象g中的TaskList和TaskOriginalList，
		 * 并将第一行设为task1且设置为g的入口任务并存入TaskPriorityQueue中，
		 * 将第一行的task1设为g的入口任务，将最后一行的task设置为g的出口任务
		 *
		 */
		for (int i = 0; i < computingCostMartrix.length; i++) {

			Task t = new Task(g.getName() + ".n_" + i);//对wcrt矩阵的每一行，创建task对象
			//并将第一行设为task1且设置为g的入口任务并存入TaskPriorityQueue中
			if (i == 1) {
				t.setIsEntry(true);
				g.getTaskPriorityQueue().in(t);
				g.setEntryTask(t);
			}
			//将最后一行的task设置为g的出口任务
			if (i == computingCostMartrix.length - 1) {
				t.setIsExit(true);
				g.setExitTask(t);
			}

			g.getTaskList().add(t);//在wcrt矩阵中按照行序对任务命名，并存进TaskList中
			g.getTaskOrigialList().add(t);//在wcrt矩阵中按照行序对任务命名，并存进TaskOrigialList中
			t.setApplication(g);//将任务和g进行绑定
		}

	
		for (int t = 1; t < g.getTaskList().size(); t++) {
			Task task = g.getTaskList().get(t);
			LinkedHashMap<Processor, Integer> processor$compCostsMap = new LinkedHashMap<Processor, Integer>();
			Double totalW = 0.00;
			for (int p = 1; p < commonProcessorList.size(); p++) {
				
				processor$compCostsMap.put(commonProcessorList.get(p), computingCostMartrix[t][p]);
				totalW = totalW + computingCostMartrix[t][p];
			}
			task.setProcessor$CompCostMap(processor$compCostsMap);
			//task.setAvgW(totalW / (commonProcessorList.size() - 1) );
		}

		
		for (int t = 1; t < g.getTaskList().size(); t++) {
			Task task = g.getTaskList().get(t);
			Integer[] comm = communicationCostMartrix[t];
			LinkedHashMap<Task, Integer> succTask$CommCostMap = task.getSuccTask$CommCostMap();
			for (int i = 1; i < comm.length; i++) {
				if (comm[i] != 0.000) {

					Task succTask = g.getTaskList().get(g.getTaskList().indexOf(new Task(g.getName() + ".n_" + i))); //ѭ���е����¼���
					succTask$CommCostMap.put(succTask, comm[i]);
					//task.setOutd(task.getOutd() + 1); 
				}
			}
			task.setSuccTask$CommCostMap(succTask$CommCostMap);

			
		}
		
		for (int t = 1; t < g.getTaskList().size(); t++) {

			Task task = g.getTaskList().get(t);
			LinkedHashMap<Task, Integer> succTask$CommCostMap = task.getSuccTask$CommCostMap();
			for (Task succTask : succTask$CommCostMap.keySet()) {
				Integer commCost = succTask$CommCostMap.get(succTask);
				succTask = g.getTaskList().get(g.getTaskList().indexOf(succTask));
				succTask.getPredTask$CommCostMap().put(task, commCost);
			}

		}
		
		setRanku(g);//计算每个task在其所在的function上的ranku值
		setOct( g, commonProcessorList);
		
		for (int p = 1; p < commonProcessorList.size(); p++) {
			Processor pro = commonProcessorList.get(p);
			double processorSL = 0d;
			for (int t = 1; t < g.getTaskList().size(); t++) {
				Task task = g.getTaskList().get(t);
				processorSL = processorSL+ task.getProcessor$CompCostMap().get(pro);
				task.setF(pro.getF_max()); //设置最大频率
			}
			
		}
		

	}
   

	   
   }
