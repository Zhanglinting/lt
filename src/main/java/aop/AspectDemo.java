package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * Aspect ����    aop����������
 * �������,�����Ҫ�ܹ�ʹ��,������spring�����ļ�����
 */
@Component
@Aspect
public class AspectDemo {
	//��������λ��ΪuserService�����з���֮ǰ
	@Before("bean(userService)") 
	public void test(){
		System.out.println("����֮ǰhello java");
	}
     @AfterReturning("bean(userService)") 
	public void hello(){
		System.out.println("������������֮��hello java");
	}
     @AfterThrowing("bean(userService)") 
 	public void hi(){
 		System.out.println("�����쳣����֮��hi java");
 	}
     @After("bean(userService)") 
 	public void h(){
 		System.out.println("������������ִ�� java");
 	}
     @Around("bean(userService)")
     public Object test1(ProceedingJoinPoint pjp)throws Throwable{
		 //ҵ�񷽷�֮ǰ
    	 System.out.println("ҵ�񷽷�֮ǰ");
    	 long t1 = System.currentTimeMillis();
    	 //���õײ��ҵ�񷽷�:login/regist��
    	 Object obj = pjp.proceed();
         
    	 //ҵ�񷽷���������֮��ִ��,���쳣����try-catch
    	 System.out.println("ҵ�񷽷�֮��"+obj);
    	 long t2 = System.currentTimeMillis();
    	 //Signature:����ǩ��
    	 Signature s = pjp.getSignature();
    	 System.out.println(s+":"+(t2-t1));
    	 return obj;
    	 
     }
    
}
