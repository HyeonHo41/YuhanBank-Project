package bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	 private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
	 private static final String USER = "bank";
	 private static final String PASSWORD = "1234";
	 
	 static {
		 try {
			Class.forName("oracle.jdbc.OracleDriver");
		 } catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(
					"ojdbc11.jar 가 WEB-INF/lib에 없습니다." + e.getMessage());
		 }
	 }
	 
	 private DBUtil() {}
	 
	 public static Connection getConnection() throws SQLException {
		 return DriverManager.getConnection(URL, USER, PASSWORD);
		 
	 }
	 
	 public static void rollback(Connection conn) {
		 if (conn == null) return;
		 
		 try {
			conn.rollback();
		} catch (SQLException ignore) {
		}
		 
	 }
	 
	 public static void close(AutoCloseable resource) {
		 if(resource == null) return;
		 
		 try {
			resource.close();
		} catch (Exception ignore) {
		}
	 }
	 
	 
	
	
	
	
}
