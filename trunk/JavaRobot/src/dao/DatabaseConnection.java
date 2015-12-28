package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatabaseConnection {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/db_facebook_robot";
	private String username = "root";
	private String password = "";
	private Connection connection = null;

	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		if (connection == null) {
			Class.forName(driver);
			connection = (Connection) DriverManager.getConnection(url,
					username, password);
		}
		return connection;
	}

	public void closeConnection() throws SQLException {
		connection.close();
	}

	public Statement getStatement() throws ClassNotFoundException, SQLException {
		return (Statement) getConnection().createStatement();
	}

	public PreparedStatement getPreparedStatement(String sql)
			throws ClassNotFoundException, SQLException {
		return getConnection().prepareStatement(sql);
	}
}
