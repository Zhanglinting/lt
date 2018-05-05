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
 * �������   �̰߳�ȫ��������
 * @author Administrator
 *
 */
@Component
@Aspect
public class TimerAspect {
     //����һ���ļ�,��һ����������
	
	private File file;
	//�������ļ�config�л�ȡlogfile����
	//���һ�������ļ�config.properties,�����ļ���������logfile
	//spring�ж�ȡ�����ļ�ΪBean config
	@Value("#{config.logfile}")
	public void setFileName(String fileName){
		file = new File(fileName);
		System.out.println(file);
	}
	//��������  500�ռ��С
	private BlockingQueue<String> queue = new LinkedBlockingQueue<String>(500);
		
	 @Around("bean(*Service)")
	 public Object test(ProceedingJoinPoint pjp) throws Throwable{
		 long t1 = System.currentTimeMillis();
		 //����ҵ�񷽷�
		 Object obj = pjp.proceed();
		 long t2 = System.currentTimeMillis();
		 //����ҵ������ʱ��
		 long t = t2-t1;
		 //��ȡ��ǰ�߳�(Tomcat�������߳�)
		 Thread th = Thread.currentThread();
		  //�߳�����
		 //Thread.sleep(5000);
		 System.out.println(t+":"+th);
		 //��ȡ����ǩ��
		 Signature s = pjp.getSignature();
		 //����Ҫ���� ��������ӵ�������
		 String str = th+","+s+","+t;
		 //���ݴ������
		 queue.offer(str);
		 return obj;
	 }
	 @PostConstruct//��ʼ��
	 //����bean����Ժ�����ִ�еķ���
	 public void start(){
		 //bean���TimerAspect�����Ժ����������߳�
		 t.start();//�����߳�
	 }
	 //�̶߳���
	 private Thread t = new Thread(){
		 public void run(){
			 //System.out.println("ok");
			 try{
			 while(true){
				 //�Ӷ�������ȡ����   take()�߳���������
				 //���û������,��ȵ�������Ϊֹ
				 String s = queue.take();
				 //���ļ�(׷�ӷ�ʽ)
				 PrintWriter out = new PrintWriter(new FileOutputStream(file,true));
				 //д����
				 out.println(s);
				 System.out.println(s);
				 //�Ż�����,���ļ�����д
				while(!queue.isEmpty()){
					 //д����
			     s=queue.take();
			     System.out.println(s);
			     out.println(s);
				}
				
				 //�ر��ļ�
				out.close();
			 }
			 }catch(Exception e){
				 
			 }
		 }
	 };
}
