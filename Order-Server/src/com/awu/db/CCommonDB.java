package com.awu.db;

import com.awu.db.utils.CDbUtils;

/**
 * Database operator super class.
 * Provide CDbUtils object for subclass.
 * @author Awu
 *
 */
public class CCommonDB {
	protected static CDbUtils dbUtils = null;
	
	public CCommonDB(){
		if(null == dbUtils){
			dbUtils = new CDbUtils();
		}
	}
}
