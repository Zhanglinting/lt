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
      @ResponseBody
      @RequestMapping("list.do")
      public JsonResult listNotes(String notebookId){
    	  List<Map<String,Object>> list = service.listNotes(notebookId);
		     return new JsonResult(list);
      }
      @ResponseBody
      @RequestMapping("get.do")
      public JsonResult get(String noteId){
    	  Note note = service.getNote(noteId);
		return new JsonResult(note);
      }
      @ResponseBody
      @RequestMapping("save.do")
      public JsonResult save(String id,String title,String body){
    	  boolean b = service.saveNote(id, title, body);
    	  return new JsonResult(b);
      }
      @ResponseBody
      @RequestMapping("add.do")
      public JsonResult addNote(String notebookId,String userId,String title){
    	  Note note = service.addNote(notebookId, userId, title);
    	  return new JsonResult(note);
      }
      @ResponseBody
      @RequestMapping("delete.do")
      public JsonResult deleteNote(String noteId){
    	  String title = service.deleteNote(noteId);
		return new JsonResult(title);
      }
      @ResponseBody
      @RequestMapping("show.do")
      public JsonResult showNote(){
    	  List<Map<String,Object>> list = service.showNote();
		return new JsonResult(list);
      }
}
