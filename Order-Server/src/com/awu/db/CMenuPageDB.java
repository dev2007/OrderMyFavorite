package com.awu.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.crypto.Data;

import com.awu.db.entity.CDataRow;

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
		CDataRow row = new CDataRow();
		try {
			row = dbUtils.selectSingleRow("select * from ", params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return row.size() != 0;
	}
}
