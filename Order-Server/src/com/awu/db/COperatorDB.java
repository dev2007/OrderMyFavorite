package com.awu.db;

import com.awu.db.entity.CDataTable;

/**
 * Operator db class.
 * @author Awu
 *
 */
public class COperatorDB extends CCommonDB {
	private static COperatorDB dbl = null;
	
	/**
	 * COperatorDB singleton.
	 * @return this class's object.
	 */
	public static COperatorDB _instance(){
		if(null == dbl)
			dbl = new COperatorDB();
		
		return dbl;
	}
	
	/**
	 * Constructor.
	 */
	private COperatorDB(){
		super();
	}
	
	/**
	 * Get operators datatable by page number and page size number.
	 * @param pageNum current page number.
	 * @param pageSize page size number.
	 * @return CDatatable object.
	 */
	public CDataTable getOperatorList(int pageNum,int pageSize){
		//prevent data error.
		if(pageNum <=0)
			pageNum = 1;
		if(pageSize <=0)
			pageSize = 1;
		
		String sql = String.format("select idoperator,fullname,username from operator limit %d,%d",(pageNum - 1) * pageSize,pageSize);
		try {
			CDataTable dt = dbUtils.executeQuery(sql);
			return dt;
		} catch (Exception e) {
			return new CDataTable();
		}
	}
}
