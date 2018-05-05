package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.junit.Test;

public class MybatisTest {
	@Test
      public void testReg(){
    	  String str ="<p>你好</p><p>你干嘛呢,吃饭了么</P>p>你干嘛呢,吃饭了么</P>";
          //[^<>]+  ^除了<>至少任意一个字符 
    	  String reg = "<p>[^<>]+<\\/p>";
          //创建正则表达式对象
          Pattern p = Pattern.compile(reg);
          //利用正则表达式匹配字符串 m代表匹配结果
          Matcher m = p.matcher(str);
          //m.find() 返回值表示是否找到了要匹配的数据
          if(m.find()){
        	  //m.group()获取找到的子字符串
        	  String s=m.group();
        	  System.out.println(s);
        	  //截取字符串  三元运算
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
