package web;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.NotebookService;
import util.JsonResult;

@Controller
@RequestMapping("notebook")
public class NotebookController extends AbstractController {
	@Resource
	private NotebookService service;
	
	@RequestMapping("list.do")
	@ResponseBody
	public JsonResult list(String userId){
		List<Map<String,Object>> list = service.listNotebooks(userId);
		return new JsonResult(list);		
	}
	
	@RequestMapping("list0.do")
	@ResponseBody
	public JsonResult list(String userId,int pageNum){
		List<Map<String,Object>> list = service.listNotebooks(userId, pageNum);		
		return new JsonResult(list);		
	}

}
