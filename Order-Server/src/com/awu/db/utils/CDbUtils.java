package com.awu.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.awu.db.CDBInfo;

/***
 * Database utils.
 * 
 * @author Awu
 * 
 */
public class CDbUtils {
	private static Connection currentConnection = null;

	/***
	 * Get database connection.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection GetConnection() throws ClassNotFoundException,
			SQLException {

		if (null == currentConnection) {
			return createConnection();
		} else {
			return currentConnection;
		}
	}

	private Connection createConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");

		String url = CDBInfo.getM_ConnectUrl();
		String username = CDBInfo.getM_UserName();
		String password = CDBInfo.getM_Password();

		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}

	/**
	 * Execute SQL
	 * 
	 * @param sql
	 * @return ResultSet
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public ResultSet ExecuteSQL(String sql) throws SQLException {
		ResultSet rSet = null;
		Statement statement = null;
		try {
			Connection conn = GetConnection();
			statement = conn.createStatement();

			rSet = statement.executeQuery(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != statement)
				statement.close();
			return rSet;
		}
	}

	/**
	 * Execute NonQuery
	 * 
	 * @param sql
	 * @return Int 1.Be affected row number 2.0
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public int ExecuteNonQuerySQL(String sql) throws SQLException {
		int result = 0;
		Statement statement = null;
		try {
			Connection conn = GetConnection();
			statement = conn.createStatement();

			result = statement.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != statement)
				statement.close();
			return result;
		}
	}
}
