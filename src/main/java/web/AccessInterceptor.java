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
	//��ȡcookie��userId,token
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
		String json ="{\"state\":1,\"data\":null,\"message\":\"�����µ�¼!\"}";
		if(userId==null || token==null){
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().print(json);
			return false;
		}
    //����ҵ���Ƚ�,���ͨ��,��return true,��������,�����ͨ��return false,
    //����response����һ������������Ϣ��JSON����
	if(service.checkToken(userId, token)){
		return true;
	}else{
		res.setContentType("text/html;charset=utf-8");
		res.getWriter().print(json);
		return false;
	}
	}

}
