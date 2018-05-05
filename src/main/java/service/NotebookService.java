package service;

import java.util.List;
import java.util.Map;

public interface NotebookService {
  List<Map<String,Object>> listNotebooks(String userId)
   throws UserNotFoundException;
  int deleteNotebooks(String...id);
  List<Map<String,Object>> listNotebooks(String userId,int pageNum)
		   throws UserNotFoundException;
}
