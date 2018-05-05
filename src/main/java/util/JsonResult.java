package util;

import java.io.Serializable;

/**
 * ���ڷ�װAJAX�����Ժ��JSON����ֵ
 * ������ȷ����ֵ:
 * {state:0,data:��������,message:������Ϣ}
 * ���󷵻�ֵ:
 * {state:1,data:null,message:������Ϣ}
 * @author Administrator
 *
 */
public class JsonResult implements Serializable {


	private static final long serialVersionUID = 1L;
      public static final int SUCCESS=0;
      public static final int ERROR=1;
      /**
       * �����Ƿ�ɹ���״̬,0��ʾ�ɹ�,1������ֵ��ʾʧ��
       */
      private int state;
      //�ɹ�ʱ��,����JSON����
      private Object data;
      //����ʱ��.���ش�����Ϣ
      private String message;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static int getSuccess() {
		return SUCCESS;
	}
	public static int getError() {
		return ERROR;
	}
	public JsonResult() {
		
	}
	public JsonResult(int state, Object data, String message) {
		super();
		this.state = state;
		this.data = data;
		this.message = message;
	}
	public JsonResult(Throwable e){
		state = ERROR;
		data = null;
		message = e.getMessage();
				
	}
	public JsonResult(Object data){
		state = SUCCESS;
		this.data = data;
		message="";
	}
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", data=" + data + ", message=" + message + "]";
	}
      public JsonResult(int state,Throwable e){
    	  this.state = state;
    	  data =null;
    	  message=e.getMessage();
    	  
      }
	
	

}
