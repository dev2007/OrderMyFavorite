package com.awu.servlet.bl;

import javax.servlet.http.HttpServletRequest;

import com.awu.db.utils.EDBMSG;

/***
 * Load Menu Interface
 * @author Awu
 *
 */
public interface ILoadMenuBL {
	EDBMSG loadMenu(HttpServletRequest request,String userName);
}
