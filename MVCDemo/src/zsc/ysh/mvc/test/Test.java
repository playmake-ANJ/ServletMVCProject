package zsc.ysh.mvc.test;

import java.sql.Connection;

import zsc.ysh.mvc.util.MyJdbcUtil;

public class Test {
	
	public static void main(String[] args) {
		Connection conn = MyJdbcUtil.getConnection();
		if(conn != null) {
			System.out.println("数据库连接成功！");
			MyJdbcUtil.close(conn, null, null);
		}else {
			System.out.println("连接失败");
		}
	}

}
