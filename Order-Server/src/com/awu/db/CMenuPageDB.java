package com.awu.db;

import java.util.ArrayList;

import com.awu.db.entity.CDataRow;

public class CMenuPageDB extends CCommonDB{
	private static CMenuPageDB dbl = null;

	public static CMenuPageDB _instance(){
		if(null == dbl)
			dbl = new CMenuPageDB();
		
		return dbl;
	}
	
	private CMenuPageDB(){
		super();
	}
	
	public Boolean validateOperatorLimit(String userName,String menuId){
		ArrayList<Object> params = new ArrayList<>();
		params.add(userName);
		params.add(menuId);
		CDataRow row = new CDataRow();
		try {
			row = dbUtils.selectSingleRow("select idoperator from operator T1 inner join rolemenu T2 on T1.roleid = T2.roleid "
					+ "where T1.username = ? and T2.menuid = ?", params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return row.size() != 0;
	}
}
