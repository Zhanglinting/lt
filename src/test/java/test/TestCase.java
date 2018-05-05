package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.NoteBookDAO;
import dao.NoteDao;
import dao.PostDao;
import dao.UserDAO;
import entity.Note;
import entity.Post;
import entity.User;
import service.NoteService;
import service.NotebookService;
import service.UserService;
import util.JsonResult;
import web.NoteController;

public class TestCase {
    ClassPathXmlApplicationContext ctx;
    @Before
    public void init(){
    	ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml","spring-service.xml","spring-mvc.xml");
    }
    @Test
    public void testDataSource() throws SQLException{
    	DataSource ds = ctx.getBean("dataSource",DataSource.class);
    	Connection conn = ds.getConnection();
    	String sql = "SELECT 'Hello' as s";
    	Statement st = conn.createStatement();
    	ResultSet rs = st.executeQuery(sql);
    	while(rs.next()){
    		System.out.println(rs.getString("s"));
    	}
    	conn.close();
    }
    @Test
    public void testAddUser(){
    	//mapper扫描器将Mapper接口UserDAO创建为Bean对象
    	UserDAO dao = ctx.getBean("userDAO",UserDAO.class);
    	User user  = new User("123","zhang","123","","你好");
    	dao.addUser(user);
    }
    @Test
    public void testFindByUserByName(){
    	UserDAO dao = ctx.getBean("userDAO",UserDAO.class);
    	User user = dao.findUserByName("zhang");
    	System.out.println(user);
    }
    @Test
    public void testLogin(){
    	String name="zhang";
    	String pwd ="123";
    	UserService service = ctx.getBean("userService",UserService.class);
    	User user = service.login(name, pwd);
    	System.out.println(user);
    }
    @Test
    public void testMd5Salt(){
    	//计算一个字符串摘要
    	//摘要加盐
    	String str = "123";
    	String salt = "我学java";
    	String md5 = DigestUtils.md5Hex(str+salt);
    	System.out.println(md5);
    }
    @Test
    public void testFileMd5() throws IOException{
    	//计算一个文件摘要
    	String filename = "d:/sping1.txt";
    	FileInputStream in = new FileInputStream(filename);
    	String md5 = DigestUtils.md5Hex(in);
    	in.close();
    	System.out.println(md5);
    	
    }
    @Test
    public void testRegist(){
    	UserService service = ctx.getBean("userService",UserService.class);
    	User user = service.regist("peng", "", "123", "123");
    	System.out.println(user);
    }
   @Test
   public void testNotebookDAO(){
	   NoteBookDAO dao = ctx.getBean("noteBookDAO",NoteBookDAO.class);
	   String id = "03590914-a934-4da9-ba4d-b41799f917d1";
	   List<Map<String,Object>> list = dao.findNoteBooksByUserId(id);
	   for(Map<String,Object> map :list){
		   System.out.println(map);
	   }
   }
   @Test
   public void testListNotebooks(){
	   NotebookService service = ctx.getBean("notebookService",NotebookService.class);
	   String id = "03590914-a934-4da9-ba4d-b41799f917d1";
	   List<Map<String, Object>> list = service.listNotebooks(id);
	   for(Map<String,Object> map:list){
		   System.out.println(map);
	   }
	   
   }
   @Test
   public void testNotes(){
	   String id ="0037215c-09fe-4eaa-aeb5-25a340c6b39b";
	   NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
	   List<Map<String,Object>> list = dao.findNotesByNotebookId(id);
	   for(Map<String,Object> map :list){
		   System.out.println(map);
	   }
   }
   @Test
   public void testListNotes(){
	   String id ="0037215c-09fe-4eaa-aeb5-25a340c6b39b";
	   NoteService service = ctx.getBean("noteService",NoteService.class);
	   List<Map<String,Object>> list = service.listNotes(id);
	   for(Map<String,Object>map:list){
		   System.out.println(map);
	   }
   }
   @Test
   public void testGetNote(){
	   NoteController con = ctx.getBean("noteController",NoteController.class);
	   String id="8530622b-f739-4048-a23f-d226228756b3";
	   JsonResult result = con.get(id);
	   System.out.println(result);
   }
   @Test
   public void testUpdateNote(){
	   String noteid="8530622b-f739-4048-a23f-d226228756b3";
	   Map<String,Object> note=new HashMap<String,Object>();
	   note.put("id",noteid);
	   //可选参数
	   note.put("title", "java基础");
	   note.put("lastModifyTime", System.currentTimeMillis());
	   NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
	   int n = dao.updateNote(note);
	   System.out.println(n);
	   Note one = dao.findNoteById(noteid);
	   System.out.println(one);
   }
   @Test
   public void testUpdate(){
	   String id="8530622b-f739-4048-a23f-d226228756b3";
	   NoteController con = ctx.getBean("noteController",NoteController.class);
	   JsonResult jr = con.save(id, "", "<p>你好</p><p>你干嘛呢,");
	   System.out.println(jr);
   }
   @Test
   public void testAddNote(){
	   NoteController con = ctx.getBean("noteController",NoteController.class);
	   JsonResult jr = con.addNote("0037215c-09fe-4eaa-aeb5-25a340c6b39b", "123", "xiaxia");
	   System.out.println(jr);
   }
   @Test
   public void testDeleteNote(){
	   NoteController con = ctx.getBean("noteController",NoteController.class);
	   String noteId = "8530622b-f739-4048-a23f-d226228756b3";
	   JsonResult jr = con.deleteNote(noteId);
	   System.out.println(jr);
   }
   
   
   /* 8d3763b2-8e01-48d4-a338-730b02ded9c9
     a200ec50-4111-4785-97b3-539115b61ed5
     19fbb55b-0541-433b-a7cd-dba52220a447
     7c44e29b-0622-43dd-9f50-7103b1461bfe

    * 
    */
   @Test
   public void testDeleteNotes(){
	   String id1="8d3763b2-8e01-48d4-a338-730b02ded9c9";
	   String id2="7c44e29b-0622-43dd-9f50-7103b1461bfe";
	   String id3="456";
	   String id4="19fbb55b-0541-433b-a7cd-dba52220a447";
	   NoteService service = ctx.getBean("noteService",NoteService.class);
	   service.deleteNotes(id1,id2,id3,id4);
   }
   // fa8d3d9d-2de5-4cfe-845f-951041bcc461
   //e46239d6-4f54-426c-a448-f7a0d45f9425
   @Test
   public void testDeleteNotebooks(){
	   String id1 ="fa8d3d9d-2de5-4cfe-845f-951041bcc461";
	   String id2 ="1234567";
	   String id3 ="e46239d6-4f54-426c-a448-f7a0d45f9425";
	   NotebookService s = ctx.getBean("notebookService",NotebookService.class);
	   s.deleteNotebooks(id1,id2,id3);
   }
   @Test
   //模糊查询
   public void testFindNotesByParam(){
	   NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
	   Map<String,Object> param = new HashMap<String,Object>();
	   //所有带有'你'的title和body %：任意个数字符；_：一个字符
	  // param.put("key", "%你%");
	   //所有带有'霞'的title
	  param.put("title", "%霞%");
	   //所有带有'你'的body
	   //param.put("body", "%你%");
	   List<Map<String,Object>> list = dao.findNotesByParam(param);
	   System.out.println(list);
   }
   
   @Test
   public void testDelete(){
	   String[] idlist = {"8d3763b2-8e01-48d4-a338-730b02ded9c9","a200ec50-4111-4785-97b3-539115b61ed5"};
	   NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
	   Map<String,Object> map = new HashMap<String, Object>();
	   map.put("idList", idlist);
	   int n = dao.deleteNotes(map);
	   System.out.println(n);
   }
   @Test
   public void testFindNoteByParam(){
	   NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
	   Map<String,Object> param = new HashMap<String,Object>();
	   param.put("statusId", "1");
	   param.put("start", 0);
	   param.put("length", 5);
	   List<Map<String,Object>> list = dao.findNoteByParam(param);
	   System.out.println(list);
   }
   @Test
   public void testFindNotebooksByParam(){
	   String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
	   String table = "cn_notebook";
	   int start = 0;
	   int size = 6;
	   Map<String,Object> param = new HashMap<String,Object>();
	   param.put("tableName", table);
	   param.put("userId",userId);
	   param.put("start",start);
	   param.put("size", size);
	   NoteBookDAO dao = ctx.getBean("noteBookDAO",NoteBookDAO.class);
	   List<Map<String,Object>> list = dao.findNoteBooksByParam(param);
	   for(Map<String,Object>map:list){
		   System.out.println(map);
	   }
   }
   @Test
   public void testListnoteboks(){
	   String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
	   NotebookService service = ctx.getBean("notebookService",NotebookService.class);
	   List<Map<String,Object>> list = service.listNotebooks(userId, 1);
	   for(Map<String,Object> map:list){
		   System.out.println(map);
	   }
   }
   @Test
   public void testFindPost(){
	   PostDao dao = ctx.getBean("postDao",PostDao.class);
	   Post post = dao.findPostById(1);
	   System.out.println(post);
   }
}
   
