package com.awu.servlet.bl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.db.CLoadMenuDB;
import com.awu.utils.CommonStr;

public class CLoadMenuBL implements ILoadMenuBL {
	private CLoadMenuDB loadMenuDB;
	
	public CLoadMenuBL(){
		this.loadMenuDB = CLoadMenuDB._instance();
	}
	
	/**
	 * Load user's menu.
	 */
	@Override
	public void loadMenu(HttpServletRequest request,HttpServletResponse response) {
		Object valueObject = request.getSession().getAttribute(CommonStr.SESSION_USERNAME);
		String userName = "";
		String jsonString = "";
		if(null != valueObject){
			userName = (String)valueObject;
			jsonString = loadMenuDB.getUserExtMenu(userName).toJson();
//			jsonString =  " [ {id:'detention',text : 'detention',leaf : true,children:[]}]";
		}
		
		response.setContentType(CommonStr.HTML_UTF8);
		
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
