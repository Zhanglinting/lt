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
 * Aspect 切面    aop面向切面编程
 * 切面组件,如果需要能够使用,必须在spring配置文件配置
 */
@Component
@Aspect
public class AspectDemo {
	//切面切入位置为userService的所有方法之前
	@Before("bean(userService)") 
	public void test(){
		System.out.println("方法之前hello java");
	}
     @AfterReturning("bean(userService)") 
	public void hello(){
		System.out.println("方法正常结束之后hello java");
	}
     @AfterThrowing("bean(userService)") 
 	public void hi(){
 		System.out.println("方法异常结束之后hi java");
 	}
     @After("bean(userService)") 
 	public void h(){
 		System.out.println("方法结束最终执行 java");
 	}
     @Around("bean(userService)")
     public Object test1(ProceedingJoinPoint pjp)throws Throwable{
		 //业务方法之前
    	 System.out.println("业务方法之前");
    	 long t1 = System.currentTimeMillis();
    	 //调用底层的业务方法:login/regist等
    	 Object obj = pjp.proceed();
         
    	 //业务方法正常结束之后执行,有异常可以try-catch
    	 System.out.println("业务方法之后"+obj);
    	 long t2 = System.currentTimeMillis();
    	 //Signature:方法签名
    	 Signature s = pjp.getSignature();
    	 System.out.println(s+":"+(t2-t1));
    	 return obj;
    	 
     }
    
}
