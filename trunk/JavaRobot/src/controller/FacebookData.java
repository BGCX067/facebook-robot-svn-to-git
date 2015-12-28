package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.Post;

import dao.CommentDAO;
import dao.PostDAO;

public class FacebookData {
	static AccessToken accessToken = new DefaultFacebookClient()
			.obtainAppAccessToken("418473888222187",
					"251088f26798b5161ca312803f39f837");
	static FacebookClient faceClient = new DefaultFacebookClient(
			FacebookData.accessToken.getAccessToken());

	public static void startOperations() throws SQLException,
			ClassNotFoundException, ParseException {
		retrievePosts();
	}

	private static void retrievePosts() throws SQLException,
			ClassNotFoundException, ParseException {
		Connection<Post> lastPosts = faceClient.fetchConnection(
				"naturanet/posts", Post.class, Parameter.with("fields",
						"id, created_time, message, link,comments"), Parameter
						.with("limit", 10));

		for (Post post : lastPosts.getData()) {
			String id = post.getId().toString();
			Date date = post.getCreatedTime();
			String message = post.getMessage();
			String link = post.getLink();
			// Comments cm = post.getComments(); Não pega todos os comentários

			bean.Post objPost = new bean.Post(id, date, message, link);
			PostDAO postDAO = new PostDAO();
			postDAO.insert(objPost);

			long commentsNumber = post.getComments().getCount();
			if (commentsNumber != 0) {
				retrieveComments(objPost, commentsNumber);
			}
		}
	}

	private static void retrieveComments(bean.Post objPost, long commentsNumber)
			throws SQLException, ClassNotFoundException {
		CommentDAO commentDAO = new CommentDAO();
		int limit = 50;
		int offset = 0;
		while (offset < commentsNumber) { // Enquanto houver mais comentários
			Connection<Comment> commentsList = faceClient.fetchConnection(
					objPost.getId() + "/comments", Comment.class,
					Parameter.with("limit", limit),
					Parameter.with("offset", offset));
			for (Comment comment : commentsList.getData()) {
				String id = comment.getId();
				String username = comment.getFrom().getName();
				String user_id = comment.getFrom().getId();
				String message = comment.getMessage();
				Date date = (Date) comment.getCreatedTime();

				bean.Comment objComment = new bean.Comment(id, username,
						user_id, message, date);

				commentDAO.insert(objComment, objPost);
			}
			offset += limit;
		}

	}
}
