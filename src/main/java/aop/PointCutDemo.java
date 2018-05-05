package aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointCutDemo {
    //��ĳ�����巽��֮ǰִ�� execution(����ֵ ����.����.������(����))
	//@Before("execution(* service.UserService.*(..))")
	//@Before("execution(* service.UserService.login(..))")
	//@Before("bean(*Service)")
	//@Before("within(service.UserServiceImpl)")
	@Before("within(service.*ServiceImpl)")
	public void test(){
		System.out.println("PointCut");
	}

}
