package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import bean.Comment;
import bean.Post;
import dao.CommentDAO;
import dao.PostDAO;

public class DatabaseData {
	ArrayList<Post> postsList = new ArrayList<Post>();

	public void eraseDatabase() throws ClassNotFoundException, SQLException {
		PostDAO postDAO = new PostDAO();
		postDAO.cleanTable();
	}

	public ArrayList<Post> getAllPostsAndComments() throws SQLException,
			ClassNotFoundException, ParseException {
		PostDAO postDAO = new PostDAO();
		CommentDAO commentDAO = new CommentDAO();
		populatePostList(postDAO.getAllPosts());
		for (int i = 0; i < postsList.size(); i++) {
			Post post = postsList.get(i);
			populateComments(commentDAO.getAllComments(post), i);
		}
		return postsList;
	}

	private void populatePostList(ResultSet rs) throws SQLException,
			ParseException {
		while (rs.next()) {
			String id = rs.getString("id");
			Date date = new Date(rs.getTimestamp("date").getTime());
			String message = rs.getString("message");
			String link = rs.getString("link");
			Post objPost = new Post(id, date, message, link);
			postsList.add(objPost);
		}
	}

	private void populateComments(ResultSet rs, int index) throws SQLException {
		while (rs.next()) {
			String id = rs.getString("id");
			String username = rs.getString("username");
			String user_id = rs.getString("user_id");
			String message = rs.getString("message");
			Date date = new Date(rs.getTimestamp("date").getTime());
			Comment objComment = new Comment(id, username, user_id, message,
					date);
			postsList.get(index).getComments().add(objComment);
		}
	}
}
