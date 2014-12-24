package com.awu.servlet.bl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.db.CDishDB;
import com.awu.db.utils.EDBMSG;
import com.awu.entity.CDish;
import com.awu.utils.CommonStr;

public class CDishBL {
	private static CDishDB dbl = null;
	
	public CDishBL(){
		dbl = CDishDB._instance();
	}
	
	public void run(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(null != request.getParameter("type")){
			String type = (String)request.getParameter("type");
			type = type.toLowerCase();
			
			switch (type) {
			case "list":
				getDishList(request, response);
				break;
			case "type":
				getDishType(request, response);
				break;
			case "add":
				addDish(request,response);
				break;
			default:
				break;
			}
		}
	}
	
	/***
	 * Get dish list data.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void getDishList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int pageNum = Integer.parseInt(request.getParameter("page") );
		int pageSize = Integer.parseInt(request.getParameter("limit") );

		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		String json = dbl.getDishList(pageNum, pageSize).toJson("dish");
		writer.write(json);
	}
	
	private void getDishType(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		writer.write(dbl.getTypeList());
	}
	
	private void addDish(HttpServletRequest request,HttpServletResponse response) throws IOException{
		CDish instance = new CDish();
		instance.setTypeId(Integer.parseInt(request.getParameter("iddishtype")));
		instance.setFullname(request.getParameter("fullname"));
		instance.setDescription(request.getParameter("description"));
		instance.setUnitPrice(Float.parseFloat(request.getParameter("unitprice")));
		instance.setImageUrl(request.getParameter("imageurl"));
		
		EDBMSG result = EDBMSG.FAIL;
		try {
			result = dbl.addDish(instance);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		writer.write(formatStr(result));
	}
	
	private String formatStr(EDBMSG result){
		String format = "{\"success\":%s,\"msg\":\"%s\"}";
		String trueFlag = "true";
		String falseFalg = "false";
		String msg = "";
		
		if(result == EDBMSG.OK)
			msg = String.format(format, trueFlag,"添加成功");
		else if(result == EDBMSG.FAIL)
			msg = String.format(format, falseFalg,"添加失败");
		else if(result == EDBMSG.ERROR)
			msg = String.format(format, falseFalg,"数据库出错");
		
		return msg;
	}
}
