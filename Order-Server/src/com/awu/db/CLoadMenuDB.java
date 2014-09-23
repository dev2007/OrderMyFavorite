package com.awu.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.awu.entity.CMenu;
import com.awu.entity.CMenuArray;

public class CLoadMenuDB extends CCommonDB{
	private static CLoadMenuDB dbl = null;

	/***
	 * CLoadMenDB singleton
	 * 
	 * @return this class' object
	 */
	public static CLoadMenuDB _instance() {
		if (null == dbl)
			dbl = new CLoadMenuDB();

		return dbl;
	}

	private CLoadMenuDB() {
		
	}

	/**
	 * get user's role relevant menus.
	 * 
	 * @param userName
	 * @return
	 */
	public CMenuArray getUserMenu(String userName) {
		CMenuArray menuArray = new CMenuArray();
		
		String sql = "select T2.idmenu,T2.parentvalue,T2.fullname from rolemenu T1 "
				+ " inner join menu T2 on T1.menuid = T2.idmenu "
				+ " inner join operator T3 on T1.roleid = T3.roleid "
				+ " where T3.username = ?";
		ArrayList<Object> params = new ArrayList<>();
		params.add(userName);
		try {

			
			ArrayList<Hashtable<String, Object>> dt = dbUtils.executeQuery(sql, params);
			for (int i = 0; i < dt.size(); i++) {
				Hashtable<String, Object> row = dt.get(i);
				
				CMenu menu = new CMenu((String)row.get("idmenu"), (String)row.get("parentvalue"), (String)row.get("fullname"));
				menuArray.append(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menuArray;
	}
}
