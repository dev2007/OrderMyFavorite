package com.awu.servlet.bl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.db.COperatorDB;
import com.awu.entity.COperator;
import com.awu.utils.CommonStr;

public class COperatorBL {
	private static COperatorDB dbl = null;
	
	public  COperatorBL(){
		dbl = COperatorDB._instance();
	}
	
	public void run(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(null != request.getParameter("type")){
			String type = (String)request.getParameter("type");
			switch (type) {
			case "list":
				getOperatorList(request, response);
				break;
			case "add":
				try {
					addOperator(request,response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			default:
				break;
			}
		}
	}
	
	/**
	 * Get operators list.
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void getOperatorList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int pageNum = Integer.parseInt(request.getParameter("page") );
		int pageSize = Integer.parseInt(request.getParameter("limit") );

		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		String json = dbl.getOperatorList(pageNum, pageSize).toJson("users");
		writer.write(json);
	}
	
	/**
	 * Add an operator.
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void addOperator(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException{
		COperator instance = new COperator();
		instance.setAge(Integer.parseInt(request.getParameter("age")));
		instance.setFullName(request.getParameter("fullname"));
		instance.setPassword(request.getParameter("password"));
		instance.setPhoneNumber(request.getParameter("phonenumber"));
		instance.setRoleId(Integer.parseInt(request.getParameter("roleid")));
		instance.setSex(Integer.parseInt(request.getParameter("sex")));
		instance.setUserName(request.getParameter("username"));
		
		Boolean result = dbl.addOperator(instance);
		PrintWriter writer = response.getWriter();
		if(result)
		writer.write("{\"success\":true,\"msg\":\"Test ok\"}");
		else {
			writer.write("{\"success\":false,\"msg\":\"Test failed\"}");
		}
	}
}