package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcConnUtil {
	private Connection conn;

	public Connection createConn() throws SQLException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//String urlstr = "jdbc:sqlserver://172.23.53.85:1433;databaseName=JspProject;user=watcher;password=P@ssword;encrypt=true;trustServerCertificate=true";
			String urlstr = "jdbc:sqlserver://25.35.72.102:1433;databaseName=3rdtest;user=saa;password=1234;encrypt=true;trustServerCertificate=true";
			conn = DriverManager.getConnection(urlstr);
			System.out.println("Connection Status:" + !conn.isClosed());
			// 原本conn.isClosed()當連線物件關閉的時候會回傳true; 當連線物件持續開啟時回傳false
			// 在這裡使用!conn.isClosed()代表連線物件開啟。
			return conn;
		} catch (Exception e) {
			return null;
		}
	}

	public Connection createConn2() {
		try {
			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/JdbcSQLServerConn/ResourcesSystem3");
			conn = ds.getConnection();
			System.out.println("Connection2 Status:" + !conn.isClosed());

			return conn;
		} catch (Exception e) {
			return null;
		}

	}

	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		System.out.println("Connection is closed!");
	}
}
