package com.awu.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.crypto.Data;

public class CMenuPageDB extends CCommonDB{
	private static CMenuPageDB dbl = null;

	public static CMenuPageDB _instance(){
		if(null == dbl)
			dbl = new CMenuPageDB();
		
		return dbl;
	}
	
	private CMenuPageDB(){
	}
	
	public Boolean validateOperatorLimit(String userName,String menuId){
		ArrayList<Object> params = new ArrayList<>();
		params.add(userName);
		params.add(menuId);
		Hashtable<String, Object> data = new Hashtable<>();
		try {
			data = dbUtils.selectSingleRow("select * from ", params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return data.size() != 0;
	}
}
