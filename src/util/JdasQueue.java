/**
 * 
 */
package util;

import java.util.LinkedList;

/**
 * @author Emma
 *
 */

public class JdasQueue<T> extends LinkedList<T> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void in(T o) {
		super.addLast(o);
	}

	public T out() {
		T t = null ;
		try{
		t = super.removeFirst();
		}catch(Exception e){
			
		}
		return t ;
	}

	public void getQueue() {
		for (int i = 0; i < super.size(); i++) {
			System.out.println(super.get(i));
		}
	}

	
}
