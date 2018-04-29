/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.AST_AFT;

/**
 * @author Emma
 *
 */
public class testforPointer {

     public static  void main(String[] args){
         AST_AFT  a = new AST_AFT(1.0,2.0);
         AST_AFT  b = new AST_AFT(3.0,4.0);
         AST_AFT  x = a;
         System.out.println("a: ast "+a.getAST()+" aft"+a.getAFT());
         System.out.println("b: ast "+b.getAST()+" aft"+b.getAFT());
         List<AST_AFT> list = new ArrayList<AST_AFT>();
         list.add(a);
         list.add(b);
         System.out.println("列表长度"+list.size());
         System.out.println("输出列表数据");
         for(AST_AFT temp:list) {
             System.out.println("("+temp.getAST() + ","+temp.getAFT()+")");
         }
         x.setAST(5.5); 
         System.out.println("set x之后的a: ast "+a.getAST()+" aft"+a.getAFT());
         Collections.sort(list);
         for(AST_AFT temp:list) {
             System.out.println("("+temp.getAST() + ","+temp.getAFT()+")");
         }


 
     }

     
}
