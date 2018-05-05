package web;

import javax.annotation.Resource;

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
@RequestMapping("user")
public class UserContoller extends AbstractController {
	@Resource
	private UserService service;
    @ResponseBody // 处理JSON返回值
    @RequestMapping("login.do")
	public Object login(String name,String password) {
		 User user = service.login(name, password);
		return new JsonResult(user);
	}
    
    @RequestMapping("/regist.do")
    @ResponseBody
    public JsonResult regist(String name,String password,String confirm,String descr){ 	
    		User user  = service.regist(name, descr, password, confirm);
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


