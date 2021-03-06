package com.awu.db.entity;

import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.google.gson.Gson;

/**
 * CDataTable class. A container for contain CDataRow objects.
 * 
 * @author Awu
 * 
 */
public class CDataTable {
	private ArrayList<CDataRow> _rowList;
	/**
	 * total record number of all pages.
	 */
	private int totalRecord = 0;

	/**
	 * Constructor.
	 */
	public CDataTable() {
		_rowList = new ArrayList<>();
	}

	/**
	 * Copy constructor.
	 * 
	 * @param dt
	 */
	public CDataTable(CDataTable dt) {
		_rowList = new ArrayList<>();
		for (int i = 0; i < dt.size(); i++) {
			_rowList.add(dt.getRow(i));
		}
	}

	/**
	 * Get rows' number.
	 * 
	 * @return
	 */
	public int size() {
		return _rowList.size();
	}

	/**
	 * Set total record number.
	 * @param totalRecord
	 * @return True. set ok. 
	 * 		False.this paramater value is less than select rows,then set the totalrecord equals selected rows number. 
	 */
	public Boolean setTotalRecord(int totalRecord) {
		if (totalRecord < size()) {
			this.totalRecord = size();
			return false;
		}
		
		this.totalRecord = totalRecord;
		return true;
	}

	/**
	 * Add one Datatable row.
	 * 
	 * @param row
	 */
	public void addRow(CDataRow row) {
		_rowList.add(row);
	}

	/**
	 * Get Datatable row data by index.
	 * 
	 * @param index
	 * @return
	 */
	public CDataRow getRow(int index) {
		if (index < 0 || index > _rowList.size())
			return new CDataRow();

		return _rowList.get(index);
	}

	/**
	 * Convert this object to json string.
	 * 
	 * @return
	 */
	public String toJson() {
		Gson gson = new Gson();
		return removeRowHeader(gson.toJson(this));
	}

	/**
	 * Convert this object to json string. At the same time,use specify header
	 * name.
	 * 
	 * @param jsonHeader
	 *            json's specified name.
	 * @return
	 */
	public String toJson(String jsonHeader) {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		json = json.replace("_rowList", jsonHeader);
		json = removeRowHeader(json);
		return json;
	}

	private String removeRowHeader(String json) {
		json = json.replace("{\"_data\":", "");
		json = json.replace("},", ",");
		json = json.replace("}]", "]");
		return json;
	}
}
