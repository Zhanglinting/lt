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
		//�ȼ�����
		if(userId==null || userId.trim().isEmpty()){
			throw new UserNotFoundException("User ID Ϊ��");		
			}
		User user = userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("�û�������");
		}
		
		return notebookDao.findNoteBooksByUserId(userId);
	}
	@Resource
	private NoteService service;
	@Transactional//��ʾ����Ĵ���
	public int deleteNotebooks(String... ary) {
		for(String id:ary){
			NoteBook book = notebookDao.findNotebookById(id);
			if(book==null){
				throw new NotebookNotFoundException("�ʼǱ�û�ҵ�");
			}
			List<Map<String,Object>> list = service.listNotes(id);
			String[] idList = new String[list.size()];
			int i=0;
			for(Map<String,Object> map:list){
				idList[i++]=(String) map.get("id");
			}
			//ɾ���ʼ�
			service.deleteNotes(idList);
			//ɾ���ʼǱ�
			notebookDao.deleteNotebookByid(id);
		}
		return 0;
	}
	public List<Map<String, Object>> listNotebooks(String userId, int pageNum) throws UserNotFoundException {
	  if(userId==null || userId.trim().isEmpty()){
		  throw new UserNotFoundException("idΪ��");
	  }
	  User user  = userDao.findUserById(userId);
	  if(user==null){
		  throw new UserNotFoundException("�û�������");
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
