package com.awu.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.awu.db.entity.CDataRow;
import com.awu.db.utils.EDBMSG;

public class CValidateDB extends CCommonDB{
	private static CValidateDB dbl = null;
	
	/***
	 * CValidateDb singleton
	 * @return this class' object
	 */
	public static CValidateDB _instance(){
		if(null == dbl)
			dbl = new CValidateDB();

		return dbl;
	}
	
	private CValidateDB(){
		super();
	}
	
	/***
	 * Login validate in database
	 * @param userName
	 * @param password
	 * @return
	 */
	public EDBMSG ValidateLogin(String userName,String password){
		try {
			ArrayList<Object> params = new ArrayList<>();
			params.add(userName);
			params.add(password);
			CDataRow row = new CDataRow();
			try {
				row = dbUtils.selectSingleRow("select * from operator where username = ? and password = ?",params);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(row.size() == 0)
				return EDBMSG.FAIL;
			
			return EDBMSG.OK;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EDBMSG.ERROR;
		}
	}
}
