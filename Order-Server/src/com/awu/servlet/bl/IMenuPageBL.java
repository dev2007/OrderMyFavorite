package com.awu.servlet.bl;

/**
 * Interface for menupage business logic.
 * get user's appointed menu data.
 * @author Awu
 *
 */
public interface IMenuPageBL {
	String getMenuData(String username,String menuid);
}
