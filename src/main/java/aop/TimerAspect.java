package aop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 性能审计   线程安全并发问题
 * @author Administrator
 *
 */
@Component
@Aspect
public class TimerAspect {
     //管理一个文件,和一个阻塞对列
	
	private File file;
	//从配置文件config中获取logfile属性
	//添加一个配置文件config.properties,配置文件包含属性logfile
	//spring中读取配置文件为Bean config
	@Value("#{config.logfile}")
	public void setFileName(String fileName){
		file = new File(fileName);
		System.out.println(file);
	}
	//阻塞队列  500空间大小
	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>(500);
		
	 @Around("bean(*Service)")
	 public Object test(ProceedingJoinPoint pjp) throws Throwable{
		 long t1 = System.currentTimeMillis();
		 //调用业务方法
		 Object obj = pjp.proceed();
		 long t2 = System.currentTimeMillis();
		 //计算业务消耗时间
		 long t = t2-t1;
		 //获取当前线程(Tomcat创建的线程)
		 Thread th = Thread.currentThread();
		  //线程阻滞
		 //Thread.sleep(5000);
		 System.out.println(t+":"+th);
		 //获取方法签名
		 Signature s = pjp.getSignature();
		 //将需要保存 的数据添加到队列中
		 String str = th+","+s+","+t;
		 //数据存入队列
		 queue.offer(str);
		 return obj;
	 }
	 @PostConstruct//初始化
	 //创建bean组件以后立即执行的方法
	 public void start(){
		 //bean组件TimerAspect创建以后立即启动线程
		 t.start();//启动线程
	 }
	 //线程对象
	 private Thread t = new Thread(){
		 public void run(){
			 //System.out.println("ok");
			 try{
			 while(true){
				 //从队列中拉取数据   take()线程阻塞方法
				 //如果没有数据,则等到有数据为止
				 String s = queue.take();
				 //打开文件(追加方式)
				 PrintWriter out = new PrintWriter(new FileOutputStream(file,true));
				 //写数据
				 out.println(s);
				 System.out.println(s);
				 //优化性能,有文件持续写
				while(!queue.isEmpty()){
					 //写数据
			     s=queue.take();
			     System.out.println(s);
			     out.println(s);
				}
				
				 //关闭文件
				out.close();
			 }
			 }catch(Exception e){
				 
			 }
		 }
	 };
}
