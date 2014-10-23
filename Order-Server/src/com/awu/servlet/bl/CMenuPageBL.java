package com.awu.servlet.bl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.app.CApp;
import com.awu.db.CMenuPageDB;
import com.awu.utils.CommonStr;
import com.awu.utils.ScriptAlertStr;

public class CMenuPageBL implements IMenuPageBL{
	private CMenuPageDB dbl = null;
	
	public CMenuPageBL(){
		dbl = CMenuPageDB._instance();
	}
	
	@Override
	public void getMenuData(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		String userName = (String)request.getSession().getAttribute("username");
		String menuId =  (String)request.getParameter("menuid");
		
		if(null == userName){
			writer.write("<script type='text/javascript'>alert('登录已失效，请重新登录')</script>");
			return;
		}

		//if not has this limit,return empty.
		if(!validateLimit(userName, menuId))
			writer.write(ScriptAlertStr.NOLIMIT);
		else
			writer.write(CApp.getMenuPageContent(request,menuId));
	}
	
	/**
	 * validate user's menu limit.
	 * @param username
	 * @param menuid
	 * @return if not has this limit,return false,else return true.
	 */
	private Boolean validateLimit(String userName,String menuId){
		return dbl.validateOperatorLimit(userName, menuId);
	}

}
