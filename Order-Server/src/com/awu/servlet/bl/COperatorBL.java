package com.awu.servlet.bl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.db.COperatorDB;
import com.awu.utils.CommonStr;

public class COperatorBL {
	private static COperatorDB dbl = null;
	
	public  COperatorBL(){
		dbl = COperatorDB._instance();
	}
	
	public void getOperatorList(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		int pageNum = Integer.parseInt(request.getParameter("page") );
//		int pageSize = Integer.parseInt(request.getParameter("size") );
		int pageNum = 1;
		int pageSize = 1;
		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		String json = dbl.getOperatorList(pageNum, pageSize).toJson("users");
		writer.write(json);
	}
}
