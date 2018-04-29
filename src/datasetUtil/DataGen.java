package datasetUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import bean.*;




/**
 * 生成处理器列表、生成计算矩阵和通信矩阵、生成
 * 全是服务函数。没有新的类。
 * */
public class DataGen {

	/**
	 * 对一个DataSample对象，对它的内部数据进行随机初始化
	 * @param dataSample
	 */
	public static void initDataSample_Random(DataSample dataSample) {
		int processorAmount=dataSample.getProcessorAmount();
		int applicationAmount=dataSample.getApplicationAmount();
		//构造出处理器集合和任务集合，准备为new出的数据集做初始化
		List<Processor> givenProcessorList = DataGen.genProcessorList(processorAmount);
		List<Application> givenApplicationList = DataGen.genApplicationList(processorAmount, applicationAmount,givenProcessorList);
		//初始化数据集（）
		dataSample.initDataSample(givenProcessorList, givenApplicationList);
		//序列化它
		/*
		 * 待补充序列化函数
		 */				
	}
	
	/**
	 * 测试函数
	 * @author Emma
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	

     /**
      * 两种初始化数据的方法，测试时只选其一，注释掉另一个
      * 两种方法分别是：1.对testSample进行随机初始化 2：用指定的数据进行初始化
      */
     //1.随机初始化
	/*
	 String name = "testSample2";
	 int processorAmount = 6;
	 int applicationAmount = 15;
     DataSample testSample = new DataSample(name, processorAmount, applicationAmount);
     DataGen.initDataSample_Random(testSample);
     */
     //2.用指定的数据进行初始化
        //------指定开始--------
        //处理器集合 
		List<Processor> givenProcessorList = new ArrayList<Processor>();
		for (int i = 0; i < 4; i++) {
			Processor p = new Processor("p" + i);
			givenProcessorList.add(p);
		}
		//设置3个Application，先给出三个通信矩阵和计算矩阵
		// 6*4 6������3��������
		Integer[][] compMartrix1 = new Integer[][] { { 0, 0, 0, 0 }, { 0, 12, 8, 9 }, { 0, 9, 15, 11 }, { 0, 7, 12, 16 }, { 0, 13, 15, 18 }, { 0, 18, 10, 20 }, { 0, 15, 10, 8 } };
		// 7*7 6����������֮���ͨ�ſ���
		Integer[][] commMartrix1 = new Integer[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 8, 12, 0, 0, 0 }, { 0, 0, 0, 0, 12, 0, 0 }, { 0, 0, 0, 0, 0, 10, 0 }, { 0, 0, 0, 0, 0, 0, 7 },
				{ 0, 0, 0, 0, 0, 0, 6 }, { 0, 0, 0, 0, 0, 0, 0 } };

		// 5*4 5������3��������
		Integer[][] compMartrix2 = new Integer[][] { { 0, 0, 0, 0 }, { 0, 14, 5, 6 }, { 0, 9, 10, 11 }, { 0, 18, 17, 16 }, { 0, 21, 15, 19 }, { 0, 7, 6, 15 } };
		// 6*6 5����������֮���ͨ�ſ���
		Integer[][] commMartrix2 = new Integer[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 15, 10, 12, 0 }, { 0, 0, 0, 0, 0, 14 }, { 0, 0, 0, 0, 0, 18 }, { 0, 0, 0, 0, 0, 10 }, { 0, 0, 0, 0, 0, 0 } };

		// 6*4 6������3��������
		Integer[][] compMartrix3 = new Integer[][] { { 0, 0, 0, 0 }, { 0, 8, 11, 9 }, { 0, 14, 13, 8 }, { 0, 9, 12, 16 }, { 0, 18, 15, 14 }, { 0, 18, 16, 20 }, { 0, 5, 10, 7 } };
		// 7*7 6����������֮���ͨ�ſ���
		Integer[][] commMartrix3 = new Integer[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 9, 12, 14, 0, 0 }, { 0, 0, 0, 16, 0, 9, 0 }, { 0, 0, 0, 0, 0, 11, 0 }, { 0, 0, 0, 0, 0, 0, 7 },
				{ 0, 0, 0, 0, 0, 0, 13 }, { 0, 0, 0, 0, 0, 0, 0 } };
        Application app1 = new Application("F1");
        Application app2 = new Application("F2");
        Application app3 = new Application("F3");
        app1.initApplication_withoutDeadline(compMartrix1, commMartrix1, Criticality.S0, 0.0);
        DataGen.initTask_in_Application(app1, givenProcessorList, compMartrix1, commMartrix1);
        app2.initApplication_withoutDeadline(compMartrix2, commMartrix2, Criticality.S1, 10.0);
        DataGen.initTask_in_Application(app2, givenProcessorList, compMartrix2, commMartrix2);
        app3.initApplication_withoutDeadline(compMartrix3, commMartrix3, Criticality.S2, 20.0);
        DataGen.initTask_in_Application(app3, givenProcessorList, compMartrix3, commMartrix3);
        
        List<Application> givenApplicationList = new ArrayList<Application>();
        givenApplicationList.add(app1);
        givenApplicationList.add(app2);
        givenApplicationList.add(app3);
        
 	String name = "testSample2";
 	int processorAmount = givenProcessorList.size();
 	int applicationAmount = 3;
    DataSample testSample = new DataSample(name, processorAmount, applicationAmount);
    testSample.initDataSample(givenProcessorList, givenApplicationList);
       //------指定结束------
    		 
	
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
  	  Integer[][] compMatrix =app.getComputingMartrix();
  	  System.out.println("	处理器数量"+compMatrix[0].length);
  	  System.out.println("	所含任务数量"+app.getComputingMartrix().length +"	"+app.getCommunicationMatrix().length +" "+app.getTaskList().size()); 
	  System.out.println("	所含的任务为：");
  	  for(Task task:app.getTaskList()) {
  		System.out.print(task.getName()+", "+"其后继任务为: ");
  		for(Task t :task.getSuccTask$CommCostMap().keySet()) {
  			System.out.print(t.getName()+", ");
  		}
  		System.out.print(" 其前驱任务为：");
  		for(Task t:task.getPredTask$CommCostMap().keySet()) {
  			System.out.print(t.getName()+", ");
  		}
    	  System.out.println("  ");
  	  }
  	  System.out.println("  ");
  	  System.out.println("  ");
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
   public static List<Application> genApplicationList(int processorAmount, int applicationAmount,List<Processor> givenProcessorList){
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
			
			Application app = new Application("F_" + index);
			//只做了赋值没有对task进行初始化
			app.initApplication_withoutDeadline(compMatrix, commMatrix, criticality, arrivaltime);
			DataGen.initTask_in_Application(app,givenProcessorList,compMatrix,commMatrix);
			givenApplicationList.add(app);
		}
		
	   return givenApplicationList;
   }
  
   /**
    * 对每个applicaiton，根据已给定的处理器集合、计算矩阵和通信矩阵，计算该Application内部task的详细数据，完成详细初始化
    * @param g
    * @param givenProcessorList 处理器集合
    * @param computingCostMartrix 该Application的计算矩阵
    * @param communicationCostMartrix 该Application的通信矩阵
    */
	public static void initTask_in_Application(Application g, List<Processor> givenProcessorList, Integer[][] computingCostMartrix, Integer[][] communicationCostMartrix) {
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

	    //对taskList里的每个任务t，生成它在各个处理器上的计算时间的Map，键值对为（处理器编号i，任务t在处理器i上的计算开销）;计算平均计算开销
		for (int t = 1; t < g.getTaskList().size(); t++) {//?第一个任务不算任务？是增加的入口？
			Task task = g.getTaskList().get(t);
			LinkedHashMap<Processor, Integer> processor$compCostsMap = new LinkedHashMap<Processor, Integer>();
			Double totalCompCost = 0.00;//中间变量，计算该任务在各个处理器上的计算开销之和，用于后来计算avgCompteCost
			for (int p = 1; p < givenProcessorList.size(); p++) {
				
				processor$compCostsMap.put(givenProcessorList.get(p), computingCostMartrix[t][p]);
				totalCompCost = totalCompCost + computingCostMartrix[t][p];
			}
			task.setProcessor$CompCostMap(processor$compCostsMap);
			task.setAvgCompCost(totalCompCost/(givenProcessorList.size()-1));//此任务在各个处理器上的平均计算开销
		}

		
		//对每个任务，生成它和它的各个后继的通信时间的Map，键值对为（后继任务i，任务t与后继任务i的通信开销）
		for (int t = 1; t < g.getTaskList().size(); t++) {
			Task task = g.getTaskList().get(t);
			Integer[] comm = communicationCostMartrix[t];
			LinkedHashMap<Task, Integer> succTask$CommCostMap = task.getSuccTask$CommCostMap();
			for (int i = 1; i < comm.length; i++) {
				if (comm[i] != 0.000) {//如果任务i是任务t的一个后继，
					Task succTask = g.getTaskList().get(i); //这句？？？？？
					succTask$CommCostMap.put(succTask, comm[i]);
					//task.setOutd(task.getOutd() + 1); 
				}
			}
			task.setSuccTask$CommCostMap(succTask$CommCostMap);
		}
		
		//对每个任务，生成它和它的各个后继的通信时间的Map，键值对为（后继任务i，任务t与后继任务i的通信开销）
		for (int t = 1; t < g.getTaskList().size(); t++) {

			Task task = g.getTaskList().get(t);
			LinkedHashMap<Task, Integer> succTask$CommCostMap = task.getSuccTask$CommCostMap();
			for (Task succTask : succTask$CommCostMap.keySet()) {
				Integer commCost = succTask$CommCostMap.get(succTask);
				//succTask = g.getTaskList().get(g.getTaskList().indexOf(succTask));//这句有必要吗？
				succTask.getPredTask$CommCostMap().put(task, commCost);
			}

		}
		
	//	setRanku(g);//计算每个task在其所在的function上的ranku值
	//	setOct( g, commonProcessorList);
	/*
		for (int p = 1; p < givenProcessorList.size(); p++) {
			Processor pro = givenProcessorList.get(p);
			double processorSL = 0d;
			for (int t = 1; t < g.getTaskList().size(); t++) {
				Task task = g.getTaskList().get(t);
				processorSL = processorSL+ task.getProcessor$CompCostMap().get(pro);
				//task.setF(pro.getF_max()); //设置最大频率
			}
			
		}
		*/

	}
   

	   
   }
