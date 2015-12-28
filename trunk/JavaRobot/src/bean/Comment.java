package bean;

import java.util.Date;

public class Comment {
	private String id;
	private String username;
	private String user_id;
	private String message;
	private Date date;

	public Comment() {
	}

	public Comment(String id, String username, String user_id, String message,
			Date date) {
		this.id = id;
		this.username = username;
		this.user_id = user_id;
		this.message = message;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
