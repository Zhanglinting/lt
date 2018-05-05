package test;
/**
 * 单例模式
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
//懒惰式,饱汉式,在需要时候在创建对象, 构造器私有,变量私有,有写就有线程并发安全问题加synchronized
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
//非懒惰式,饥饿式,及早创建对象    构造器私有,变量私有,单例模式,加静态属性可以类名点获得变量
//加get方法只能读取,不能修改,只读没有线程安全问题
class Friend{
    private static Friend f = new Friend();
	public static Friend getF() {
		return f;
	}
    private Friend(){
		
	}
}