package com.awu.servlet.bl;

public class CMenuPageBL implements IMenuPageBL{

	@Override
	public String getMenuData(String username, String menuid) {
		//if not has this limit,return empty.
		if(!validateLimit(username, menuid))
			return "";
		
		return "need reliazed";
	}
	
	/**
	 * validate user's menu limit.
	 * @param username
	 * @param menuid
	 * @return if not has this limit,return false,else return true.
	 */
	private Boolean validateLimit(String username,String menuid){
		return false;
	}

}
