package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.UserService;

public class AccessFilter implements Filter {
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res =(HttpServletResponse) response;
		//getCookie方法可以获取客户端传送的所有Cookie对象.
		String token=null;
		String userId=null;
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
			 for(Cookie c:cookies){
					if("token".equals(c.getName())){
						token=c.getValue();
					}
					if("userId".equals(c.getName())){
						userId=c.getValue();
					}
				}
		      }    
		if(token==null){
			res.sendRedirect("log_in.html");
			return;
		}
		UserService userService =ctx.getBean("userService",UserService.class);
		//如果Cookie中的token与数据库的token不一致,则重定向到登录面
		if(userService.checkToken(userId,token)){
			//请求继续
	        chain.doFilter(req, res);
		}else{
			res.sendRedirect("log_in.html");
		}
		
	}
    private WebApplicationContext ctx;
	public void init(FilterConfig cfg) throws ServletException {
   //getWebApplicationContext方法必须与ContextLoaderListener配合才能正常获取spring容器对象ctx.
		//获取当前web应用中的spring容器
		ctx=WebApplicationContextUtils.getWebApplicationContext(cfg.getServletContext());

	}

	public void destroy() {
		
		
	}

}
