package zsc.ysh.mvc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyJdbcUtil {
      //c3p0 获取数据库连接
	private static DataSource dataSource = null;
	
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭数据资源
	public static void close(Connection conn,PreparedStatement stat,ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(stat != null) {
				stat.close();
			}
			if(conn != null) {
				conn.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
