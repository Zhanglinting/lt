package dao;

import java.util.List;
import java.util.Map;

import entity.Note;

public interface NoteDao {
   List<Map<String,Object>> findNotesByNotebookId(String notebookId);
   List<Map<String,Object>> findNotes();
   Note findNoteById(String id);
   /**
    * ��̬Note���ݸ���
    * ����note�а�����������
    * ����Ĳ���:
    *         id:�ʼ�Id
    * ��ѡ����:
    *        notebookId:�ʼǱ�Id
    *        title:����
    *        body:�ʼ�����
    *        userId:����Id
    *        lastModifyTime:���༭ʱ��
    *        ....
    * @param note
    * @return ��������
    */
   int updateNote(Map<String,Object> note);
   int addNote(Note note);
   int deleteNoteById(String id);
   //��̬������ѯ
   List<Map<String,Object>> findNotesByParam(Map<String,Object> map);
   //����ɾ������
   int deleteNotes(Map<String,Object> param);
   List<Map<String,Object>> findNoteByParam(Map<String,Object>map);
}
