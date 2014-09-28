package com.awu.entity;

import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Ext js tree panel data. menu root.
 * 
 * @author Awu
 * 
 */
public class CExtJsMenuRoot {
	private Boolean expanded;
	private ArrayList<CExtJsMenu> children;

	public CExtJsMenuRoot() {
		expanded = true;
		children = new ArrayList<>();
	}

	/**
	 * Add child menu.
	 * 
	 * @param menu
	 * @return True, if add success.False,if has same menu be added.
	 */
	public Boolean addChild(CExtJsMenu menu) {
		if (children.contains(menu)) {
			return false;
		}

		children.add(menu);
		return true;
	}
	
	/**
	 * Get root's child by id.
	 * @param id
	 * @return if get this child,return it,else return NULL.
	 */
	public CExtJsMenu getChild(String id){
		for (CExtJsMenu menu : children) {
			CExtJsMenu targetMenu = getChild(menu,id);
			if(null != targetMenu)
				return targetMenu;
		}
		return null;
	}

	/**
	 * Get child by id.
	 * it use recursion find child's child.
	 * @param childMenu
	 * @param id
	 * @return if get this child,return it,else return NULL.
	 */
	private CExtJsMenu getChild(CExtJsMenu childMenu, String id) {
		if(childMenu.getId().equals(id))
			return childMenu;
		for (CExtJsMenu menu : childMenu.getChildren()) {
			if (menu.getId().equals(id)) {
				return menu;
			} else {
				return getChild(menu, id);
			}
		}
		return null;
	}

	/**
	 * Convert this object to json.
	 * 
	 * @return
	 */
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
