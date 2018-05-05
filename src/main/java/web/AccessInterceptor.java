package web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import service.UserService;
@Component
public class AccessInterceptor implements HandlerInterceptor {
     @Resource
     private UserService service;
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		

	}

	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3)
			throws Exception {
		

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
	//获取cookie的userId,token
		Cookie[] cookies=req.getCookies();
		String userId=null;
		String token=null;
		if(cookies!=null){
			for(Cookie c:cookies){
				if("userId".equals(c.getName())){
					userId=c.getValue();
				}
				if("token".equals(c.getName())){
					token=c.getValue();
				}
			}
		}
		String json ="{\"state\":1,\"data\":null,\"message\":\"请重新登录!\"}";
		if(userId==null || token==null){
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().print(json);
			return false;
		}
    //交给业务层比较,如果通过,则return true,继续访问,如果不通过return false,
    //利用response返回一个包含错误消息的JSON对象
	if(service.checkToken(userId, token)){
		return true;
	}else{
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().print(json);
		return false;
	}
	}

}
