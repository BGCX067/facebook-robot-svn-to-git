package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
	private String id;
	private Date date;
	private String message;
	private String link;
	private ArrayList<Comment> comments;

	public Post() {
	}

	public Post(String id, Date date, String message, String link) {
		this.id = id;
		this.date = date;
		this.message = message;
		this.link = link;
		this.comments = new ArrayList<Comment>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setText(String message) {
		this.message = message;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
}
