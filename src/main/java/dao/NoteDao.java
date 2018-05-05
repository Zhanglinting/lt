package dao;

import java.util.List;
import java.util.Map;

import entity.Note;

public interface NoteDao {
   List<Map<String,Object>> findNotesByNotebookId(String notebookId);
   List<Map<String,Object>> findNotes();
   Note findNoteById(String id);
   /**
    * 动态Note数据更新
    * 参数note中包含更新数据
    * 必须的参数:
    *         id:笔记Id
    * 可选参数:
    *        notebookId:笔记本Id
    *        title:标题
    *        body:笔记内容
    *        userId:作者Id
    *        lastModifyTime:最后编辑时间
    *        ....
    * @param note
    * @return 更新数量
    */
   int updateNote(Map<String,Object> note);
   int addNote(Note note);
   int deleteNoteById(String id);
   //动态参数查询
   List<Map<String,Object>> findNotesByParam(Map<String,Object> map);
   //批量删除方法
   int deleteNotes(Map<String,Object> param);
   List<Map<String,Object>> findNoteByParam(Map<String,Object>map);
}
