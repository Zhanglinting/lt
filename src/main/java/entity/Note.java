package entity;

import java.io.Serializable;


public class Note implements Serializable {
	private static final long serialVersionUID = 1L;
     private String id;
     private String notebookId;
     private String userId;
     private String statusId;
     private String typeId;
     private String title;
     private String body;
     private Long createtime;
     private Long lastmodifytime;
	public Note() {
		
	}
	public Note(String id, String notebookId, String userId, String statusId, String typeId, String title, String body,
			Long createtime, Long lastmodifytime) {
		super();
		this.id = id;
		this.notebookId = notebookId;
		this.userId = userId;
		this.statusId = statusId;
		this.typeId = typeId;
		this.title = title;
		this.body = body;
		this.createtime = createtime;
		this.lastmodifytime = lastmodifytime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNotebookId() {
		return notebookId;
	}
	public void setNotebookId(String notebookId) {
		this.notebookId = notebookId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public Long getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(Long lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastmodifytime == null) ? 0 : lastmodifytime.hashCode());
		result = prime * result + ((notebookId == null) ? 0 : notebookId.hashCode());
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Note other = (Note) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastmodifytime == null) {
			if (other.lastmodifytime != null)
				return false;
		} else if (!lastmodifytime.equals(other.lastmodifytime))
			return false;
		if (notebookId == null) {
			if (other.notebookId != null)
				return false;
		} else if (!notebookId.equals(other.notebookId))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Note [id=" + id + ", notebookId=" + notebookId + ", userId=" + userId + ", statusId=" + statusId
				+ ", typeId=" + typeId + ", title=" + title + ", body=" + body + ", createtime=" + createtime
				+ ", lastmodifytime=" + lastmodifytime + "]";
	}
     
     
}
