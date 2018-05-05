package util;

import java.io.Serializable;

/**
 * 用于封装AJAX调用以后的JSON返回值
 * 其中正确返回值:
 * {state:0,data:返回数据,message:错误消息}
 * 错误返回值:
 * {state:1,data:null,message:错误信息}
 * @author Administrator
 *
 */
public class JsonResult implements Serializable {


	private static final long serialVersionUID = 1L;
      public static final int SUCCESS=0;
      public static final int ERROR=1;
      /**
       * 返回是否成功的状态,0表示成功,1或其他值表示失败
       */
      private int state;
      //成功时候,返回JSON数据
      private Object data;
      //错误时候.返回错误消息
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
