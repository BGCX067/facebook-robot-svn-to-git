package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import bean.Post;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class PostDAO extends DatabaseConnection {
	public void insert(Post post) throws SQLException, ClassNotFoundException {
		String sql = "INSERT IGNORE INTO tb_posts(id,date,message,link) VALUES (?,?,?,?)";
		PreparedStatement ps = (PreparedStatement) getPreparedStatement(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ps.setString(1, post.getId());
		ps.setString(2, sdf.format(post.getDate()));
		ps.setString(3, post.getMessage());
		ps.setString(4, post.getLink());
		ps.execute();
		ps.close();
	}

	public void cleanTable() throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM tb_posts";
		Statement stmt = (Statement) getStatement();
		stmt.execute(sql);
		stmt.close();
	}

	public ResultSet getAllPosts() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM tb_posts ORDER BY date DESC";
		Statement stmt = (Statement) getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
}
