package aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointCutDemo {
    //在某个具体方法之前执行 execution(返回值 包名.类名.方法名(参数))
	//@Before("execution(* service.UserService.*(..))")
	//@Before("execution(* service.UserService.login(..))")
	//@Before("bean(*Service)")
	//@Before("within(service.UserServiceImpl)")
	@Before("within(service.*ServiceImpl)")
	public void test(){
		System.out.println("PointCut");
	}

}
