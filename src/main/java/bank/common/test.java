package bank.common;

import java.sql.Connection;

import bank.util.DBUtil;

public class test {
	
	public static void main(String[] args) {
		try(Connection conn = DBUtil.getConnection()) {
			System.out.println("연결성공: " + conn.getMetaData().getDatabaseProductVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
