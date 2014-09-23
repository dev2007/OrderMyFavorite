package com.awu.entity;

import com.google.gson.Gson;

/***
 * menu class.
 * @author Awu
 *
 */
public class CMenu {
	/**
	 * db autocreate id
	 */
	private String id;
	/**
	 * parent id.
	 */
	private String parentId;
	/**
	 * menu name.
	 */
	private String name;
	
	/**
	 * Constructor
	 * @param id
	 * @param parentId
	 * @param name
	 */
	public CMenu(String id,String parentId,String name){
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}
	
	/**
	 * Convert object to json data.
	 * @return
	 */
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
