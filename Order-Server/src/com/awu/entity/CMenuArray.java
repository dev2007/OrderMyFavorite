package com.awu.entity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/**
 * This class obtains menu objects.
 * @author Awu
 *
 */
public class CMenuArray {	
	/**
	 * Menu array.
	 */
	private List<CMenu> menus;
	
	/**
	 * constructor
	 */
	public CMenuArray(){
		menus = new ArrayList<>();
	}
	
	/**
	 * append a menu object.
	 * @param menu
	 */
	public void append(CMenu menu){
		menus.add(menu);
	}
	
	/**
	 * remove a menu object by index.
	 * @param index
	 */
	public void remove(int index){
		menus.remove(index);
	}
	
	/**
	 * clear all menu objects.
	 */
	public void clear(){
		menus.clear();
	}
	
	/**
	 * convert this object to json data.
	 * @return
	 */
	public String toJson(){
		Gson gson = new Gson();
		return parseJsonHeader(gson.toJson(this) );
	}
	
	/**
	 * remove json header for easy ui tree.
	 * @param json
	 * @return
	 */
	private String parseJsonHeader(String json){
		final int HEADER_LEN = 9;
		return json.substring(HEADER_LEN,json.length() - 1);
	}
	
}
