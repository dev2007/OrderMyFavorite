package com.awu.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.awu.db.CDBInfo;

/***
 * Database utils.
 * @author Awu
 *
 */
public class CDbUtils {
	private static Connection currentConnection = null;
	
	/***
	 * Get database connection.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection GetConnection() throws ClassNotFoundException, SQLException {
		
		 if(null == currentConnection){
				return createConnection();
		 }else {
			return currentConnection;
		}
	}
	
	private Connection createConnection() throws ClassNotFoundException, SQLException{
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 String url = CDBInfo.getM_ConnectUrl();
		 String username = CDBInfo.getM_UserName();
		 String password = CDBInfo.getM_Password();
		
		 Connection con = DriverManager.getConnection(url, username, password); 
		 return con; 
	}
}
