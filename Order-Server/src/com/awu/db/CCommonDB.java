package com.awu.db;

import com.awu.db.utils.CDbUtils;

/**
 * Database operator super class.
 * Provide CDbUtils object for subclass.
 * @author Awu
 *
 */
public class CCommonDB {
	protected CDbUtils dbUtils = null;
	
	public CCommonDB(){
		dbUtils = new CDbUtils();
	}
}
