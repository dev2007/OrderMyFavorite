package com.awu.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import sun.awt.image.OffScreenImage;

import com.mysql.jdbc.PreparedStatement;

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
	public ArrayList<Hashtable<String, Object>> executeQuery(String sql) throws SQLException {
		ResultSet rSet = null;
		Statement statement = null;
		ArrayList<Hashtable<String, Object>> arrayList = new ArrayList<>();
		
		try {
			Connection conn = GetConnection();
			statement = conn.createStatement();

			rSet = statement.executeQuery(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			arrayList = covertResetToArrayList(rSet);
			rSet.close();
			statement.close();
			return arrayList;
		}
	}
	
	/***
	 *Execute sql with params.
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public ArrayList<Hashtable<String, Object>> executeQuery(String sql,ArrayList<Object> params) throws SQLException {
		ResultSet rSet = null;
		java.sql.PreparedStatement preStatement = null;
		ArrayList<Hashtable<String, Object>> arrayList = new ArrayList<>();
		
		try {
			Connection conn = GetConnection();
			preStatement = conn.prepareStatement(sql);
			bindParams(preStatement, params);
			rSet = preStatement.executeQuery();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			arrayList = covertResetToArrayList(rSet);
			rSet.close();
			preStatement.close();
			return arrayList;
		}
	}
	
	/**
	 * select single row of query result.
	 * if has more rows,just return the first row.
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public Hashtable<String, Object> selectSingleRow(String sql) throws SQLException {
		ResultSet rSet = null;
		Statement statement = null;
		ResultSetMetaData meta = null;
		int columnsCount = 0;
		
		try {
			Connection conn = GetConnection();
			statement = conn.createStatement();

			rSet = statement.executeQuery(sql);
			meta = rSet.getMetaData();
			columnsCount =  meta.getColumnCount();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Hashtable<String, Object> table = new Hashtable<>();
			if(rSet.next()){
				table = convertResetRowToTable(rSet, meta, columnsCount);
			}
			
			rSet.close();
			statement.close();
			return table;
		}
	}
	
	/**
	 * select single row of query result with paramters.
	 * if has more rows,just return the first row.
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public Hashtable<String, Object> selectSingleRow(String sql,ArrayList<Object> params) throws SQLException {
		ResultSet rSet = null;
		java.sql.PreparedStatement statement = null;
		ResultSetMetaData meta = null;
		int columnsCount = 0;
		
		try {
			Connection conn = GetConnection();
			statement = conn.prepareStatement(sql);
			bindParams(statement, params);
			
			rSet = statement.executeQuery();
			meta = rSet.getMetaData();
			columnsCount =  meta.getColumnCount();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Hashtable<String, Object> table = new Hashtable<>();
			if(rSet.next()){
				table = convertResetRowToTable(rSet, meta, columnsCount);
			}
			
			rSet.close();
			statement.close();
			return table;
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
	public int executeNonQuery(String sql) throws SQLException {
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
	
	/**
	 * Convert one Reset result to ArrayList
	 * @param rSet
	 * @return
	 * @throws SQLException
	 */
	private ArrayList<Hashtable<String, Object>> covertResetToArrayList(ResultSet rSet) throws SQLException{
		ArrayList<Hashtable<String, Object>> arrayList = new ArrayList<>();
		ResultSetMetaData meta = rSet.getMetaData();
		int columnsCount = meta.getColumnCount();
		
		while (rSet.next()) {
			Hashtable<String, Object> table = convertResetRowToTable(rSet,meta, columnsCount);
			arrayList.add(table);
		}
		
		return arrayList;
	}

	/**
	 * Convert Resultset one row data to HashTable
	 * @param rSet
	 * @param meta
	 * @param columnsCount
	 * @return
	 * @throws SQLException
	 */
	private Hashtable<String, Object> convertResetRowToTable(ResultSet rSet,
			ResultSetMetaData meta, int columnsCount) throws SQLException {
		Hashtable<String, Object> table = new Hashtable<>();

		for (int i = 1; i <= columnsCount; i++) {
			String name = meta.getColumnName(i);
			table.put(name, rSet.getString(name));
		}
		return table;
	}
	
	/***
	 * Convert common paramater object to real data type,
	 * and set them on PreparedStatement for sql execute.
	 * @param ps
	 * @param params
	 * @throws SQLException
	 */
	private void bindParams(java.sql.PreparedStatement ps,ArrayList<Object> params) throws SQLException{
		for (int i = 0; i < params.size(); i++) {
			Object value = params.get(i);
			
			if(value instanceof Integer){
				ps.setInt(i+1, (int)value);
			}else if(value instanceof String){
				ps.setString(i+1, (String)value);
			}
		}
	}
}
