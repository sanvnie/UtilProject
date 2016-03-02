package com.util.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 连接数据库的工具类
 * @author Administrator
 *
 */
public class DbUtil {
	private static final String MYSQL = "jdbc:mysql://";

	private static final String ORACLE = "jdbc:oracle:thin:@";

	private static final String SQLSERVER = "jdbc:microsoft:sqlserver://";
	/**
	 * 获取数据库连接
	 * @param dbDriver 数据库驱动名
	 * @param dbUrl 数据库URL
	 * @param dbUser 用户名
	 * @param dbPasswd 密码
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConn(String dbDriver,String dbUrl,String dbUser,String dbPasswd) throws ClassNotFoundException, SQLException{
		Class.forName(dbDriver);
		return DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @throws SQLException
	 */
	public static void close(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
			conn=null;
		}
	}
	
	/**
	 * 获取preparedstatment
	 * @param conn
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement getPreparedStatement(Connection conn,String sql) throws SQLException{
		return conn.prepareStatement(sql);
	}
}
