package com.awu.servlet.bl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.db.utils.EDBMSG;

/***
 * Load Menu Interface
 * @author Awu
 *
 */
public interface ILoadMenuBL {
	void loadMenu(HttpServletRequest request,HttpServletResponse response);
}
