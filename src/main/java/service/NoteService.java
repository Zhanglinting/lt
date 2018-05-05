package service;

import java.util.List;
import java.util.Map;

import entity.Note;

public interface NoteService {
    List<Map<String,Object>> listNotes(String notebookId)throws NotebookNotFoundException;
    Note getNote(String id)throws NoteNotFoundException;
    boolean saveNote(String id,String title,String body)throws NoteNotFoundException;
    Note addNote(String notebookId,String userId,String title);
    String deleteNote(String noteId)throws NoteNotFoundException;
    List<Map<String,Object>> showNote()throws NoteNotFoundException;
    //String...id Ïàµ±ÓÚString[] id
    int deleteNotes(String...id);
}
