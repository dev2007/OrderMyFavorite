package com.awu.servlet.bl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for menupage business logic. get user's appointed menu data.
 * 
 * @author Awu
 * 
 */
public interface IMenuPageBL {
	void getMenuData(HttpServletRequest request, HttpServletResponse response)
			throws IOException;
}
