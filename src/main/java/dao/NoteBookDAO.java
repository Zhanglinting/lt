package dao;

import java.util.List;
import java.util.Map;

import entity.NoteBook;

public interface NoteBookDAO {
        List<Map<String,Object>> findNoteBooksByUserId(String userId);
		NoteBook findNotebookById(String notebookId);
		int deleteNotebookByid(String id);
		List<Map<String,Object>> findNoteBooksByParam(Map<String,Object> param);
}
