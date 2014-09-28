package com.awu.entity;

import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Menu store class for ExtJs.
 * @author Awu
 *
 */
public class CExtJsMenu {
	/**
	 * menu id
	 */
	private String id;
	/**
	 * menu name.
	 */
	private String text;
	/**
	 * menu is leaf.
	 */
	private Boolean leaf;
	/**
	 * this menu's leaf menus.
	 */
	private ArrayList<CExtJsMenu> children;
	
	public CExtJsMenu(){
		id = "";
		text = "";
		leaf = true;
		children = new ArrayList<>();
	}
	
	public CExtJsMenu(String id,String text,Boolean leaf){
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		children = new ArrayList<>();
	}
	
	public void addChild(CExtJsMenu menu){
		children.add(menu);
	}
	
	public String getId(){
		return id;
	}
	
	public CExtJsMenu getChild(String id){
		for (CExtJsMenu menu : children) {
			if(menu.getId().equals(id))
				return menu;
		}
		return null;
	}
	
	public ArrayList<CExtJsMenu> getChildren(){
		return children;
	}
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
