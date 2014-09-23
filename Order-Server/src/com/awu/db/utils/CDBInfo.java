package com.awu.db.utils;

/**
 * Database connect info.
 * @author Awu
 *
 */
public class CDBInfo {
	/**
	 * Produce flag.
	 * True.produce enviroment.
	 * False.develop enviroment.
	 */
	private static Boolean PRODUCEFLAG = false;
	
	private static String m_LocalConnectUrl = "jdbc:mysql://localhost:3306/orderdb";
	private static String m_LocalUserName = "root";
	private static String m_LocalPassword = "123456";
	
	private static String m_ProduceConnectUrl = "";
	private static String m_ProduceUserName = "";
	private static String m_ProducePassword = "";
	
	/***
	 * Database connect url.
	 * @return
	 */
	public static String getM_ConnectUrl() {
		return PRODUCEFLAG ? m_ProduceConnectUrl : m_LocalConnectUrl;
	}
	
	/***
	 * Database connect username.
	 * @return
	 */
	public static String getM_UserName() {
		return PRODUCEFLAG ? m_ProduceUserName : m_LocalUserName;
	}

	/**
	 * Database connect password.
	 * @return
	 */
	public static String getM_Password() {
		return PRODUCEFLAG ? m_ProducePassword :m_LocalPassword;
	}

	
}
