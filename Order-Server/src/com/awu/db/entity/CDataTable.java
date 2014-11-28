package com.awu.db.entity;

import java.util.ArrayList;

/**
 * CDataTable class.
 * A container for contain CDataRow objects.
 * @author Awu
 *
 */
public class CDataTable {
	private ArrayList<CDataRow> _rowList;
	
	/**
	 * Constructor.
	 */
	public CDataTable(){
		_rowList = new ArrayList<>();
	}
	
	/**
	 * Copy constructor.
	 * @param dt
	 */
	public CDataTable(CDataTable dt){
		_rowList = new ArrayList<>();
		
		for (int i = 0; i < dt.size();i++) {
			_rowList.add(dt.getRow(i));
		}
	}
	
	/**
	 * Get rows' number.
	 * @return
	 */
	public int size(){
		return _rowList.size();
	}
	
	/**
	 * Add one Datatable row.
	 * @param row
	 */
	public void addRow(CDataRow row){
		_rowList.add(row);
	}
	
	/**
	 * Get Datatable row data by index.
	 * @param index
	 * @return
	 */
	public CDataRow getRow(int index){
		if(index < 0 || index > _rowList.size())
			return new CDataRow();
		
		return _rowList.get(index);
	}
}
