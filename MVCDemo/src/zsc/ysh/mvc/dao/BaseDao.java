package zsc.ysh.mvc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import zsc.ysh.mvc.util.MyJdbcUtil;

public class BaseDao<T> {
	
	//DBUtil ��QueryRunner ������ݿ����е�CRUD����
	 QueryRunner queryRunner = new QueryRunner();
	 private Class<T> clazz;
	 
	 @SuppressWarnings("unchecked")
	public BaseDao() {
		 //Ϊ���õ��������Class,��User.class
		 Type superType = this.getClass().getGenericSuperclass();
		 if(superType instanceof ParameterizedType) {
		   ParameterizedType pt = (ParameterizedType) superType;
   		   //����һ����������
   		   Type[] tarry = pt.getActualTypeArguments();
   		   clazz =  (Class<T>) tarry[0];
		 }
	 }
	 
	 /*
	  *����һ����¼ͨ�÷���
	  */
	 public T get(String sql,Object...objects) {
		 Connection conn = null;
		 T entity = null;
		 try {
			 conn = MyJdbcUtil.getConnection();
			 entity = queryRunner.query(conn, sql, new BeanHandler<T>(clazz), objects);
			 
		 }catch (Exception e){
			 e.printStackTrace();
		 }finally {
			 MyJdbcUtil.close(conn, null, null);
		 }
		 return entity;
	 }
	
	 /*
	  * ��ɾ��ͨ�÷���
	  */
	 public int uid(String sql,Object...objects) {
		 int row = 0;
		 Connection conn = null;
		 try {
			 conn = MyJdbcUtil.getConnection();
			 row = queryRunner.update(conn, sql, objects);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 MyJdbcUtil.close(conn, null, null);
		 }
		 return row;
	 }
	 
	 /*
	  * �������м�¼
	  */
	 public List<T> getList(String sql,Object...objects){
		 List<T> list = null;
		 Connection conn = null;
		 try{
			 conn = MyJdbcUtil.getConnection();
			 //����ʹ�õĲ�����new BeanListHandler<>()
			 list = queryRunner.query(conn, sql, new BeanListHandler<T>(clazz),objects);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 MyJdbcUtil.close(conn, null, null);
		 }
		 return list;
	 }
	 
	 	/*
		 * @����һ��SQL���Ľ��ֻ��һ����ֵ�����͵Ĳ�ѯ��count(id)
		 * 
		 */
		public Object getCount(String sql,Object...args) {
			Connection conn = null;
			Object obj = null;
			try {
				conn = MyJdbcUtil.getConnection();
				obj = queryRunner.query(conn, sql, new ScalarHandler(), args);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				MyJdbcUtil.close(conn, null, null);		
			}
			
			return obj;
		}
	
	 
	 
}
