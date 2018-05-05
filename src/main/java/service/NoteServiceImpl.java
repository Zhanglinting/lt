package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.NoteBookDAO;
import dao.NoteDao;
import dao.UserDAO;
import entity.Note;
import entity.NoteBook;
import entity.User;
@Service("noteService")
public class NoteServiceImpl implements NoteService {
    
	@Resource
    private NoteDao notedao;
    @Resource
    private NoteBookDAO notebookdao;
    @Resource
    private UserDAO userdao;
    @Transactional(readOnly=true)
    
	public List<Map<String, Object>> listNotes(String notebookId) throws NotebookNotFoundException {
		if(notebookId==null ||notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("id不能为空");
		}
		NoteBook notebook = notebookdao.findNotebookById(notebookId);
		//countNotebookById(notebookId)
		if(notebook==null){
			throw new NotebookNotFoundException("笔记本不存在");
		}
		List<Map<String,Object>> list = notedao.findNotesByNotebookId(notebookId);
		return list;
	}
	 @Transactional(readOnly=true)//只读
	public Note getNote(String id) throws NoteNotFoundException {
		if(id==null || id.trim().isEmpty() ){
			throw new NoteNotFoundException("ID为空");
		}
		Note note = notedao.findNoteById(id);
		if(note==null){
			throw new NoteNotFoundException("ID错误");
		}
		return note;
	}
	 @Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public boolean saveNote(String id, String title, String body) throws NoteNotFoundException {
		if(id==null||id.trim().isEmpty()){
			throw new NoteNotFoundException("id为空");
		}
		if(body==null){
			body="";
		}
		if(title==null || title.trim().isEmpty()){
			//截取body中的数据
			String reg = "<p>[^<>]+<\\/p>";
			//创建正则对象
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(body);
			if(m.find()){
				String str = m.group();
				title=str.substring(3,str.length()>13?13:str.length()-4).trim();
				
			}
			
		}
		
		Map<String,Object > note = new HashMap<String,Object>();
		note.put("id",id);
		note.put("title",title);
		note.put("body", body);
		note.put("lastModifyTime", System.currentTimeMillis());
		int n = notedao.updateNote(note);
		return n==1;
	}
	 @Transactional
	public Note addNote(String notebookId, String userId, String title)throws NotebookNotFoundException,UserNotFoundException {
		if(notebookId==null || notebookId.trim().isEmpty()){
			throw new NotebookNotFoundException("id为空");
		}
	
		if(userId==null || userId.trim().isEmpty()){
			throw new UserNotFoundException("id为空");
		}
		if(title==null ||title.trim().isEmpty() ){
			title="无标题";
		}
		User user = userdao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户没找到");
		}
		NoteBook notebook = notebookdao.findNotebookById(notebookId);
		if(notebook==null){
			throw new NotebookNotFoundException("笔记本不存在");
		}
		String id = UUID.randomUUID().toString();
		long now = System.currentTimeMillis();
		Note note = new Note();
		note.setBody("");
		note.setCreatetime(now);
		note.setId(id);
		note.setLastmodifytime(now);
		note.setNotebookId(notebookId);
		note.setStatusId("1");
		note.setTitle(title);
		note.setTypeId("1");
		note.setUserId(userId);
		int n = notedao.addNote(note);
		if(n!=1){
			throw new RuntimeException("保存失败");
		}
		return note;
	}
	 @Transactional
	public String deleteNote(String noteId) throws NoteNotFoundException {
		if(noteId==null || noteId.trim().isEmpty()){
			throw new NoteNotFoundException("id为空");
		}
		Note note = notedao.findNoteById(noteId);
		if(note==null){
			throw new NoteNotFoundException("笔记不存在");
		}
		String title = note.getTitle();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", noteId);
		map.put("statusId", "2");
		map.put("lastModifyTime", System.currentTimeMillis());
		int n = notedao.updateNote(map);
		if(n!=1){
			throw new RuntimeException("删除失败");
		}
		return title;
	}
	 @Transactional(readOnly=true)
	public List<Map<String, Object>> showNote() throws NoteNotFoundException {
		List<Map<String, Object>> list = notedao.findNotes();
		return list;
	}
    @Transactional(propagation=Propagation.REQUIRED)//默认传播
	public int deleteNotes(String... ary)throws NoteNotFoundException {
		int n=0;
		for(String id:ary){
			Note note = notedao.findNoteById(id);
			if(note==null){
				throw new NoteNotFoundException("笔记不存在");
			}
			n+=notedao.deleteNoteById(id);
		}
		return n;
	}

}
