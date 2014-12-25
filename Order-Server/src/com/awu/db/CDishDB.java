package com.awu.db;

import java.sql.SQLException;
import java.util.ArrayList;

import com.awu.db.entity.CDataRow;
import com.awu.db.entity.CDataTable;
import com.awu.db.utils.EDBMSG;
import com.awu.entity.CDish;

public class CDishDB extends CCommonDB {
	private static CDishDB dbl = null;
	
	/**
	 * CDishDb singleton
	 * @return this class' object.
	 */
	public static CDishDB _instance(){
		if(null == dbl)
			dbl = new CDishDB();
		
		return dbl;
	}
	
	private CDishDB(){
		super();
	}
	
	/**
	 * Get dish datatable by page number and page size number.
	 * 
	 * @param page
	 *            current page number.
	 * @param pageSize
	 *            page size number.
	 * @return CDatatable object.
	 */
	public CDataTable getDishList(int page,int pageSize){
		// prevent data error.
		if (page <= 0)
			page = 1;
		if (pageSize <= 0)
			pageSize = 25;
		
		int totalRecord = getTotalRecord();

		String sql = "select goodsbase.idgoodsbase as id,dishtype.fullname as typename,"
				+ "goodsbase.fullname as dishname,goodsbase.description,goodsbase.unitprice,goodsbase.imageurl ";
		sql += "from goodsbase  inner join dish  on goodsbase.idgoodsbase = dish.baseid inner join dishtype  on dish.dishtypeid = dishtype.iddishtype ";
		sql += String.format("limit %d,%d", (page - 1) * pageSize, pageSize);
		try {
			CDataTable dt = dbUtils.executeQuery(sql);
			Boolean flag = dt.setTotalRecord(totalRecord);
			if (!flag)
				System.err
						.println("dish total size less then select size.");
			return dt;
		} catch (Exception e) {
			return new CDataTable();
		}
	}
	
	
	/***
	 * Get total number of dish.
	 * @return
	 */
	private int getTotalRecord(){
		String sql = "select count(goodsbase.idgoodsbase) as total ";
		sql += "from goodsbase  "
				+ "inner join dish  on goodsbase.idgoodsbase = dish.baseid "
				+ "inner join dishtype  on dish.dishtypeid = dishtype.iddishtype ";
		try {
			CDataRow dr = dbUtils.selectSingleRow(sql);
			return Integer.parseInt((String) dr.value("total"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Get dish type list.
	 * @return
	 */
	public String getTypeList() {
		String sql = "select iddishtype,fullname from dishtype";
		try {
			CDataTable dt = dbUtils.executeQuery(sql);
			return dt.toJson("type").replace(",\"totalRecord\":0}", "}");
		} catch (Exception e) {
			return "";
		}
	}
	
	/***
	 * Add dish.
	 * @param dish
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public EDBMSG addDish(CDish dish) throws ClassNotFoundException, SQLException{
		String sql = "insert into goodsbase(fullname,description,unitprice,imageurl) values(?,?,?,?) ";
		String sql2 = "insert into dish(baseid,cando,dishtypeid) values(?,1,?) ";
		
		ArrayList<Object> params = new ArrayList<>();
		params.add(dish.getFullname());
		params.add(dish.getDescription());
		params.add(dish.getUnitPrice());
		params.add(dish.getImageUrl());
		
		ArrayList<Object> params2 = new ArrayList<>();
		
		try {
			dbUtils.getConnection().setAutoCommit(false);
			int result = dbUtils.executeInsertReturnId(sql, params);
			if(result <= 0){
				dbUtils.getConnection().rollback();
				return EDBMSG.FAIL;
			}
			
			params2.add(result);
			params2.add(dish.getTypeId());
			
			result = dbUtils.executeNonQuery(sql2, params2);
			if(result <= 0){
				dbUtils.getConnection().rollback();
				return EDBMSG.FAIL;
			}
			
			dbUtils.getConnection().commit();
			dbUtils.getConnection().setAutoCommit(true);
			return EDBMSG.OK;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			dbUtils.getConnection().rollback();
			return EDBMSG.ERROR;
		}
		
	}
}
