package com.awu.db.entity;

import java.util.Hashtable;
import java.util.Iterator;

public class CDataRow {
	private Hashtable<String, Object> _data;
	
	/**
	 * constructor.
	 */
	public CDataRow(){
		_data = new Hashtable<>();
	}
	
	/**
	 * copy constructor
	 * @param row
	 */
	public CDataRow(CDataRow row){
		_data = new Hashtable<>();
		for (int i = 0; i < row.size(); i++) {
			_data.put(row.columnName(i), row.value(i));
		}
	}
	
	/**
	 * Get CDataRow's copy object.
	 * @return
	 */
	public CDataRow copy(){
		return new CDataRow(this);
	}
	
	/**
	 * add column with value.
	 * @param columnName
	 * @param value
	 * @throws Exception if CDataRow's store object is null,then throw exception.
	 */
	public void addColumn(String columnName,Object value) throws Exception{
		if(_data != null){
			_data.put(columnName, value);
		}else{
			throw new Exception("This CDataRow object is null.");
		}
	}
	
	/***
	 * remove column.
	 * @param columnName column name.
	 * @return if success,then return true.
	 * @throws Exception if CDataRow's not contains this column,then throw exception.
	 */
	public Boolean removeColumn(String columnName) throws Exception{
		if(_data.containsKey(columnName)){
			_data.remove(columnName);
			return true;
		}else{
			throw new Exception("This CDataRow is not contains this column name.");
		}
	}
	
	/***
	 * Get the object's data number.
	 * @return
	 */
	public int size(){
		return _data.size();
	}
	
	/**
	 * Get the index's column name.
	 * @param index
	 * @return
	 */
	public String columnName(int index){
		int count = 0;
		for (Iterator<String> itr = _data.keySet().iterator(); itr.hasNext();){
			//find the index
			if(index == count){
				String key = (String) itr.next();
				return key;
			}
			count++;
		}
		return "";
	}
	
	/**
	 * Get the index's object value.
	 * @param index
	 * @return
	 */
	public Object value(int index){
		String key = columnName(index);
		
		//find relevant key
		if(!key.equals("")){
			return _data.get(key);
		}
		
		return null;
	}
	
	/**
	 * Get the column's object value.
	 * @param columnName
	 * @return
	 */
	public Object value(String columnName){
		return _data.get(columnName);
	}
}
