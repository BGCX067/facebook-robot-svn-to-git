package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import bean.Comment;
import bean.Post;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class CommentDAO extends DatabaseConnection {
	public void insert(Comment comment, Post post) throws SQLException,
			ClassNotFoundException {
		String sql = "INSERT IGNORE INTO tb_comments(id,username,user_id,message,date,post_id) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = (PreparedStatement) getPreparedStatement(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ps.setString(1, comment.getId());
		ps.setString(2, comment.getUsername());
		ps.setString(3, comment.getUser_id());
		ps.setString(4, comment.getMessage());
		ps.setString(5, sdf.format(comment.getDate()));
		ps.setString(6, post.getId());
		ps.execute();
		ps.close();
	}

	public void cleanTable() throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM tb_comments";
		Statement stmt = (Statement) getStatement();
		stmt.execute(sql);
		stmt.close();
	}

	public ResultSet getAllComments(Post post) throws SQLException,
			ClassNotFoundException {
		String sql = "SELECT * FROM tb_comments WHERE post_id='" + post.getId()
				+ "'";
		Statement stmt = (Statement) getStatement();
		stmt.execute(sql);
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
}
