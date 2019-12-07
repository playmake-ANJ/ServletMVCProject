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
	
	//DBUtil 类QueryRunner 完成数据库所有的CRUD操作
	 QueryRunner queryRunner = new QueryRunner();
	 private Class<T> clazz;
	 
	 @SuppressWarnings("unchecked")
	public BaseDao() {
		 //为了拿到具体类的Class,如User.class
		 Type superType = this.getClass().getGenericSuperclass();
		 if(superType instanceof ParameterizedType) {
		   ParameterizedType pt = (ParameterizedType) superType;
   		   //返回一个类型数组
   		   Type[] tarry = pt.getActualTypeArguments();
   		   clazz =  (Class<T>) tarry[0];
		 }
	 }
	 
	 /*
	  *返回一条记录通用方法
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
	  * 增删改通用方法
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
	  * 返回所有记录
	  */
	 public List<T> getList(String sql,Object...objects){
		 List<T> list = null;
		 Connection conn = null;
		 try{
			 conn = MyJdbcUtil.getConnection();
			 //这里使用的参数：new BeanListHandler<>()
			 list = queryRunner.query(conn, sql, new BeanListHandler<T>(clazz),objects);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 MyJdbcUtil.close(conn, null, null);
		 }
		 return list;
	 }
	 
	 	/*
		 * @返回一个SQL语句的结果只有一个数值的类型的查询，count(id)
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
