package com.awu.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.awu.db.entity.CDataRow;
import com.awu.db.entity.CDataTable;

/***
 * Database utils.
 * These method use autocommit default.
 * If you need use transacation,you need set commit model to false,like conn.setAutoCommit(false),
 * after your operation finish,you need set commit model to true,like conn.setAutoCommit(true)
 * if you forget set it,you may be face some bugs such like 'can't query data'.
 * @author Awu
 * 
 */
public class CDbUtils {
	/**
	 * Db connection.
	 */
	private static Connection currentConnection;
	
	/**
	 * Constructor.
	 */
	public CDbUtils(){
		try {
			currentConnection = createConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Get database connection.
	 * 
	 * If you use conn.setAutoCommit(false) to realize transaction,
	 * after your operation,you need set commit model to true, conn.setAutoCommit(true)
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		if(null == currentConnection)
			currentConnection = createConnection();
		return currentConnection;
	}

	/**
	 * Create db connection.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
	public CDataTable executeQuery(String sql) throws SQLException {
		ResultSet rSet = null;
		Statement statement = null;

		CDataTable dTable = new CDataTable();

		try {
			statement = currentConnection.createStatement();

			rSet = statement.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dTable = convertResetToTable(rSet);

			rSet.close();
			statement.close();

			return dTable;
		}
	}

	/***
	 * Execute sql with params.
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public CDataTable executeQuery(String sql, ArrayList<Object> params)
			throws SQLException {
		ResultSet rSet = null;
		java.sql.PreparedStatement preStatement = null;
		CDataTable dTable = new CDataTable();

		try {
			preStatement = currentConnection.prepareStatement(sql);
			bindParams(preStatement, params);
			rSet = preStatement.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dTable = convertResetToTable(rSet);

			rSet.close();
			preStatement.close();

			return dTable;
		}
	}

	/**
	 * select single row of query result. if has more rows,just return the first
	 * row.
	 * 
	 * @param sql query sql,such like 'SELECT * FROM AA WHERE BB = CC'
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public CDataRow selectSingleRow(String sql) throws Exception {
		ResultSet rSet = null;
		Statement statement = null;
		ResultSetMetaData meta = null;
		int columnsCount = 0;

		try {
			statement = currentConnection.createStatement();

			rSet = statement.executeQuery(sql);
			meta = rSet.getMetaData();
			columnsCount = meta.getColumnCount();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CDataRow row = new CDataRow();

			if (rSet.next()) {
				row = converResetRow(rSet, meta, columnsCount);
			}

			rSet.close();
			statement.close();
			return row;
		}
	}

	/**
	 * select single row of query result with paramters. if has more rows,just
	 * return the first row.
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public CDataRow selectSingleRow(String sql, ArrayList<Object> params)
			throws Exception {
		ResultSet rSet = null;
		java.sql.PreparedStatement statement = null;
		ResultSetMetaData meta = null;
		int columnsCount = 0;

		try {
			statement = currentConnection.prepareStatement(sql);
			bindParams(statement, params);

			rSet = statement.executeQuery();
			meta = rSet.getMetaData();
			columnsCount = meta.getColumnCount();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CDataRow row = new CDataRow();
			if (rSet.next()) {
				row = converResetRow(rSet, meta, columnsCount);
			}

			rSet.close();
			statement.close();
			return row;
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
			statement = currentConnection.createStatement();

			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != statement)
				statement.close();
			return result;
		}
	}
	

	/**
	 * Execute NonQuery with paramaters.
	 * 
	 * @param sql
	 * @return Int 1.Be affected row number 2.0
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public int executeNonQuery(String sql, ArrayList<Object> params)
			throws SQLException {
		int result = 0;
		java.sql.PreparedStatement statement = null;
		try {
			statement = currentConnection.prepareStatement(sql);
			bindParams(statement, params);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != statement)
				statement.close();
			return result;
		}
	}
	
	/**
	 * Execute Insert SQL,and return auto generated id.
	 * 
	 * @param sql
	 * @return auto generated id. 0.operation fail,may be hasn't auto generated column.
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public int executeInsertReturnId(String sql) throws SQLException {
		int result = 0;
		Statement statement = null;
		try {
			statement = currentConnection.createStatement();

			statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rSet = statement.getGeneratedKeys();
			if(rSet.next()){
				result =  rSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != statement)
				statement.close();
			return result;
		}
	}
	
	/**
	 * Execute Insert SQL,and return auto generated id. with paramaters.
	 * 
	 * @param sql
	 * @return auto generated id. 0.operation fail,may be hasn't auto generated column.
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public int executeInsertReturnId(String sql, ArrayList<Object> params)
			throws SQLException {
		int result = 0;
		java.sql.PreparedStatement statement = null;
		try {
			statement = currentConnection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			bindParams(statement, params);
			statement.executeUpdate();
			ResultSet rSet = statement.getGeneratedKeys();
			if(rSet.next()){
				result = rSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != statement)
				statement.close();
			return result;
		}
	}

	/**
	 * Convert ResultSet to CDataTable.
	 * 
	 * @param rSet
	 * @return
	 */
	private CDataTable convertResetToTable(ResultSet rSet) {
		CDataTable dTable = new CDataTable();

		ResultSetMetaData meta;
		int columnsCount = 0;
		try {
			meta = rSet.getMetaData();
			columnsCount = meta.getColumnCount();

			while (rSet.next()) {
				CDataRow row = converResetRow(rSet, meta, columnsCount);
				dTable.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dTable;
	}

	/**
	 * Convert Resultset one row to CDataRow object.
	 * 
	 * @param rSet
	 * @param meta
	 * @param columnsCount
	 * @return
	 * @throws Exception
	 */
	private CDataRow converResetRow(ResultSet rSet, ResultSetMetaData meta,
			int columnsCount) throws Exception {
		CDataRow row = new CDataRow();
		for (int i = 1; i <= columnsCount; i++) {
			String name = meta.getColumnLabel(i);
			row.addColumn(name, rSet.getString(name));
		}
		return row;
	}

	/***
	 * Convert common paramater object to real data type, and set them on
	 * PreparedStatement for sql execute.
	 * 
	 * @param ps
	 * @param params
	 * @throws SQLException
	 */
	private void bindParams(java.sql.PreparedStatement ps,
			ArrayList<Object> params) throws SQLException {
		for (int i = 0; i < params.size(); i++) {
			Object value = params.get(i);

			if (value instanceof Integer) {
				ps.setInt(i + 1, (int) value);
			} else if (value instanceof String) {
				ps.setString(i + 1, (String) value);
			} else if (value instanceof Float){
				ps.setFloat(i + 1, (float)value);
			}
		}
	}
}
