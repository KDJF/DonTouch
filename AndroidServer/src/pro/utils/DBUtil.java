package pro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	// 属性:url,user,password
	private static String url = "jdbc:mysql://localhost:3306/systemdesigner?useUnicode=true&amp;"
			+ "characterEncoding=utf8;";
	private static String user = "root";
	private static String password = "1234";

	// 加载驱动

	// 方法：创建连接，关闭连接
	public static Connection getConnection() {
		Connection conn = null;

		// 创建数据库连接
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeStatement(Statement st) {
		try {
			if (st != null && !st.isClosed()) {
				st.close();
				st = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
