package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.NoteBookDAO;
import dao.UserDAO;
import entity.NoteBook;
import entity.User;
@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {
     @Resource
     private NoteBookDAO notebookDao;
     @Resource
     private UserDAO userDao;
	public List<Map<String, Object>> listNotebooks(String userId) throws UserNotFoundException {
		//先检查参数
		if(userId==null || userId.trim().isEmpty()){
			throw new UserNotFoundException("User ID 为空");		
			}
		User user = userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		
		return notebookDao.findNoteBooksByUserId(userId);
	}
	@Resource
	private NoteService service;
	@Transactional//演示事务的传播
	public int deleteNotebooks(String... ary) {
		for(String id:ary){
			NoteBook book = notebookDao.findNotebookById(id);
			if(book==null){
				throw new NotebookNotFoundException("笔记本没找到");
			}
			List<Map<String,Object>> list = service.listNotes(id);
			String[] idList = new String[list.size()];
			int i=0;
			for(Map<String,Object> map:list){
				idList[i++]=(String) map.get("id");
			}
			//删除笔记
			service.deleteNotes(idList);
			//删除笔记本
			notebookDao.deleteNotebookByid(id);
		}
		return 0;
	}
	public List<Map<String, Object>> listNotebooks(String userId, int pageNum) throws UserNotFoundException {
	  if(userId==null || userId.trim().isEmpty()){
		  throw new UserNotFoundException("id为空");
	  }
	  User user  = userDao.findUserById(userId);
	  if(user==null){
		  throw new UserNotFoundException("用户不存在");
	  }
	  int size = 6;
	  int start = pageNum*size;
	  Map<String,Object> param = new HashMap<String, Object>();
	  param.put("tableName","cn_notebook");
	  param.put("userId", userId);
	  param.put("start", start);
	  param.put("size", size);
	  List<Map<String,Object>> list = notebookDao.findNoteBooksByParam(param);
		return list;
	}

}
