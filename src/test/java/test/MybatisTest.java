package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.junit.Test;

public class MybatisTest {
	@Test
      public void testReg(){
    	  String str ="<p>���</p><p>�������,�Է���ô</P>p>�������,�Է���ô</P>";
          //[^<>]+  ^����<>��������һ���ַ� 
    	  String reg = "<p>[^<>]+<\\/p>";
          //����������ʽ����
          Pattern p = Pattern.compile(reg);
          //����������ʽƥ���ַ��� m����ƥ����
          Matcher m = p.matcher(str);
          //m.find() ����ֵ��ʾ�Ƿ��ҵ���Ҫƥ�������
          if(m.find()){
        	  //m.group()��ȡ�ҵ������ַ���
        	  String s=m.group();
        	  System.out.println(s);
        	  //��ȡ�ַ���  ��Ԫ����
        	  s=s.substring(3,s.length()>13? 13:s.length()-4);
        	  System.out.println(s);
          }
      }
   @Test
   public void test(){
	   int[] ary = new int[100];
	   ary[1]=2;
	   ary[2]=2;
	   ary[3]=4;
	   int[]count=new int[10];
	   for(int i:ary){
		   count[i]++;
		   System.out.println(count[i]);
	   }
	   
	   for(int n:count){
		   System.out.println(n);
	   }
	   
   }
  
}
