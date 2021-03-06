package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
      private Integer id;
      private String content;
      private Long time;
      //private Integer person;
      private Person person;
      
      private List<Comment> comments = new ArrayList<Comment>();
      
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public Post() {
	
	}
	public Post(Integer id, String content, Long time, Person person, List<Comment> comments) {
		super();
		this.id = id;
		this.content = content;
		this.time = time;
		this.person = person;
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", time=" + time + ", person=" + person + ", comments="
				+ comments + "]";
	}

	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
      
      
}
