/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.io.IOException;

/**
 * @author Emma
 *
 */
public class genCommMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int task_number = 15;
		//task_number = 15 + new Random().nextInt(task_number);
		//int task_number =  new Random().nextInt(10);
		int cost_lower = 1;//计算矩阵和通信矩阵通用的cost_upper和cost_lower用于随机生成数据用
		int cost_upper = 20;
		
		System.out.println("任务数量"+ task_number);
		Integer[][] commMartrix = new Integer[task_number][task_number];//taski 和 task j在不同处理器上执行时需要的通信开销
		LinkedHashMap<Integer, Integer> ssMap = new LinkedHashMap<Integer, Integer>();

		for (int i = 0; i < task_number; i++) {
			for (int j = 0; j < task_number; j++) {

				commMartrix[i][j] = 0;

				if (i < j && (j - i <= 10)) {
					int r = new Random().nextInt(cost_upper);

					Integer v = ssMap.get(i);
					if (v == null) {
						commMartrix[i][j] = cost_lower + new Random().nextInt(cost_upper);
						ssMap.put(i, 1);
					} else {
						int num = ssMap.get(i);
						if (num < 3 && ((r % 3 == 0) || (j + 1 == task_number))) {

							commMartrix[i][j] = cost_lower + new Random().nextInt(cost_lower);
							ssMap.put(i, num + 1);
						} else {

							commMartrix[i][j] = 0;

						}
					}

				}

				if (i == task_number - 1) {//出口任务
					commMartrix[i][j] = 0;
				}

				//System.out.print("commMartrix[" +i +"]["+j +"]="+ commMartrix[i][j]+" ");
				System.out.print(commMartrix[i][j]+" ");
			}
			System.out.print("\n");
		}

		for(Entry<Integer, Integer> entry : ssMap.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		List<Integer> cijs = new ArrayList<Integer>();
		for (int i = 0; i < task_number; i++) {
			for (int j = 0; j < task_number; j++) {

				if (commMartrix[i][j] > 0)
					cijs.add(commMartrix[i][j]);

			}
		}
       for(int i=0;i<cijs.size();i++ ){
			System.out.print(cijs.get(i)+" ");
       }

	}
	
}
