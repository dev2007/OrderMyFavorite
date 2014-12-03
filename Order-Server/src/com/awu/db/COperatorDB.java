package com.awu.db;

import com.awu.db.entity.CDataRow;
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
	 * @param page current page number.
	 * @param pageSize page size number.
	 * @return CDatatable object.
	 */
	public CDataTable getOperatorList(int page,int pageSize){
		//prevent data error.
		if(page <=0)
			page = 1;
		if(pageSize <=0)
			pageSize = 25;
		
		int totalRecord = getTotalRecord();
		
		String sql = "select operator.idoperator,operator.roleid,operator.fullname,operator.username,waiter.age,sex.fullname as sex,waiter.phonenumber from operator ";
		sql += "left join waiter  on operator.waiterid = waiter.idwaiter ";
		sql += "left join sex on waiter.sex = sex.idsex ";
		sql += "order by operator.roleid,operator.idoperator ";
		sql += String.format("limit %d,%d",(page - 1) * pageSize,pageSize);
		try {
			CDataTable dt = dbUtils.executeQuery(sql);
			Boolean flag = dt.setTotalRecord(totalRecord);
			if(!flag)
				System.err.println("operator total size less then select size.");
			return dt;
		} catch (Exception e) {
			return new CDataTable();
		}
	}
	
	private int getTotalRecord(){
		String sumSql = "select count(operator.idoperator) as total from operator ";
		sumSql += "left join waiter  on operator.waiterid = waiter.idwaiter ";
		sumSql += "left join sex on waiter.sex = sex.idsex ";
		sumSql += "order by operator.roleid,operator.idoperator ";
		try {
			CDataRow dr = dbUtils.selectSingleRow(sumSql);
			return Integer.parseInt((String)dr.value("total"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}
}