package com.awu.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.awu.db.entity.CDataRow;
import com.awu.db.entity.CDataTable;
import com.awu.entity.CExtJsMenu;
import com.awu.entity.CExtJsMenuRoot;
import com.awu.entity.CMenu;
import com.awu.entity.CMenuArray;

public class CLoadMenuDB extends CCommonDB {
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
		super();
	}

	/**
	 * Get menu data from db.
	 * 
	 * @param userName
	 * @return
	 */
	@SuppressWarnings("finally")
	private CDataTable getMenuData(String userName) {
		CDataTable dt = new CDataTable();
		String sql = "select T2.idmenu,T2.parentvalue,T2.fullname,T2.haschild from rolemenu T1 "
				+ " inner join menu T2 on T1.menuid = T2.idmenu "
				+ " inner join operator T3 on T1.roleid = T3.roleid "
				+ " where T3.username = ?";
		ArrayList<Object> params = new ArrayList<>();
		params.add(userName);
		try {
			dt = dbUtils.executeQuery(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return dt;
		}
	}

	/**
	 * get user's role relevant menus.
	 * 
	 * @param userName
	 * @return
	 */
	public CMenuArray getUserMenu(String userName) {
		CMenuArray menuArray = new CMenuArray();

		String sql = "select T2.idmenu,T2.parentvalue,T2.fullname,T2.haschild from rolemenu T1 "
				+ " inner join menu T2 on T1.menuid = T2.idmenu "
				+ " inner join operator T3 on T1.roleid = T3.roleid "
				+ " where T3.username = ?";
		ArrayList<Object> params = new ArrayList<>();
		params.add(userName);
		try {

			CDataTable dt = dbUtils.executeQuery(sql, params);
			for (int i = 0; i < dt.size(); i++) {
				CDataRow row = dt.getRow(i);

				CMenu menu = new CMenu((String) row.value("idmenu"),
						(String) row.value("parentvalue"),
						(String) row.value("fullname"));
				menuArray.append(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menuArray;
	}

	/**
	 * Get user's menus by extjs style json.
	 * 
	 * @param userName
	 * @return
	 */
	public CExtJsMenuRoot getUserExtMenu(String userName) {
		CDataTable dt = getMenuData(userName);

		CExtJsMenuRoot menuRoot = new CExtJsMenuRoot();

		if (dt.size() == 0)
			return menuRoot;

		for (int i = 0; i < dt.size(); i++) {
			CDataRow row = dt.getRow(i);

			Boolean isLeaf = ((String) row.value("haschild")).equals("0");
			CExtJsMenu menu = new CExtJsMenu((String) row.value("idmenu"),
					(String) row.value("fullname"), isLeaf);
			CExtJsMenu father = menuRoot.getChild((String) row
					.value("parentvalue"));
			if (null == father)
				menuRoot.addChild(menu);
			else {
				father.addChild(menu);
			}
		}
		return menuRoot;
	}
}
