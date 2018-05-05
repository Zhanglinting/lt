package web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.User;

import service.NameException;
import service.PasswordException;
import service.UserService;
import util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{
     @Resource
     private UserService service;
     
     @ResponseBody
     @RequestMapping("/login.do")
	public Object login(String name,String password,HttpServletResponse res){
		//将Cookie从服务器下发到客户端
    	 Cookie cookie = new Cookie("token","12345");
		 cookie.setPath("/");//高级别路径
		 User user = service.login(name, password);
		 //将用户表中的token下发到浏览器中
		 cookie.setValue(user.getToken());
		 res.addCookie(cookie);
		 return new JsonResult(user);
	}
     
     @ResponseBody
     @RequestMapping("/regist.do")
     public JsonResult regist(String name,String password,String descr,String confirm){
    		 User user = service.regist(name, descr, password, confirm);
        	 return new JsonResult(user);	 
     }
     
     @ExceptionHandler(NameException.class)
     @ResponseBody
     public Object nameExp(NameException e){
    	 e.printStackTrace();
		return new JsonResult(2,e);
    	 
     }
     @ExceptionHandler(PasswordException.class)
     @ResponseBody
     public Object pwdExp(PasswordException e){
    	 e.printStackTrace();
		return new JsonResult(3,e);
    	 
     }
 
}
