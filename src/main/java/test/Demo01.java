package test;
/**
 * ����ģʽ
 * @author Administrator
 *
 */
public class Demo01 {
     public static void main(String[] args) {
		//Friend f1 = new Friend();
    	//Friend f2 = new Friend();
    	//Friend f1 = Friend.f;
    	//Friend f2 = Friend.f;
    	//Friend.f=null;
    	 Friend f1=Friend.getF();
    	 Friend f2=Friend.getF();
    	 System.out.println(f1==f2);
	}
}
//����ʽ,����ʽ,����Ҫʱ���ڴ�������, ������˽��,����˽��,��д�����̲߳�����ȫ�����synchronized
//
class Foo{
	private static Foo foo;
	private Foo(){
		
	}
	public synchronized static Foo getFoo() {
		if(foo==null){
			foo = new Foo();
		}
		return foo;
	}
}
//������ʽ,����ʽ,���紴������    ������˽��,����˽��,����ģʽ,�Ӿ�̬���Կ����������ñ���
//��get����ֻ�ܶ�ȡ,�����޸�,ֻ��û���̰߳�ȫ����
class Friend{
    private static Friend f = new Friend();
	public static Friend getF() {
		return f;
	}
    private Friend(){
		
	}
}