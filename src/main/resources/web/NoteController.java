package web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Note;
import service.NoteService;
import util.JsonResult;

@Controller
@RequestMapping("note")
public class NoteController extends AbstractController {
     @Resource
     private NoteService service;
     
     @RequestMapping("list.do")
     @ResponseBody
     public JsonResult list(String notebookId){
    	// Object obj = noteService.listNotes(notebookId);
    	 //return new JsonResult(obj);
    	 List<Map<String,Object>> list = service.listNotes(notebookId);
    	 return new JsonResult(list);
     }
    @RequestMapping("get.do")
    @ResponseBody
    public JsonResult getNote(String noteId){
    	Note note = service.getNote(noteId);
		return new JsonResult(note);
    }
    @RequestMapping("save.do")
    @ResponseBody
    public JsonResult updateNote(String id,String title,String body){
		//¼ì²é²ÎÊý    
    	Boolean b = service.saveNote(id, title, body);   
		return new JsonResult(b);    
    }
    @RequestMapping("add.do")
    @ResponseBody
    public JsonResult addNote(String notebookId,String userId,String title){
    	String newTitle= service.addNote(notebookId, userId, title);
		return new JsonResult(newTitle);
    	
    }
}
