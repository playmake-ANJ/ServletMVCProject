package zsc.ysh.mvc.test;

import java.sql.Connection;

import zsc.ysh.mvc.util.MyJdbcUtil;

public class Test {
	
	public static void main(String[] args) {
		Connection conn = MyJdbcUtil.getConnection();
		if(conn != null) {
			System.out.println("���ݿ����ӳɹ���");
			MyJdbcUtil.close(conn, null, null);
		}else {
			System.out.println("����ʧ��");
		}
	}

}
