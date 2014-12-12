package com.awu.servlet.bl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.db.COperatorDB;
import com.awu.db.utils.EDBMSG;
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
			type = type.toLowerCase();
			
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
			break;
			case "delete":
				try {
					deleteOperator(request, response);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "limit":
				try {
					roleList(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
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
		
		EDBMSG result = dbl.addOperator(instance);
		
		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		writer.write(formatStr(result));
	}
	
	/**
	 * Delete an operator.
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void deleteOperator(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException{
		String userName = request.getParameter("username");
		
		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		
		if(userName.equals("admin")){
			writer.write("超级管理员禁止删除！");
			return;
		}
		
		EDBMSG result = dbl.deleteOperator(userName);
		
		if(result == EDBMSG.OK)
			writer.write("删除成功");
		else if(result == EDBMSG.FAIL)
			writer.write("删除失败！");
		else if(result == EDBMSG.ERROR)
			writer.write("操作异常，请重试！");
	}
	
	private void roleList(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException{

		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();
		writer.write(dbl.getRoleList());
	}
	
	/**
	 * Format operation result message string.
	 * @param result
	 * @return
	 */
	private String formatStr(EDBMSG result){
		String format = "{\"success\":%s,\"msg\":\"%s\"}";
		String trueFlag = "true";
		String falseFalg = "false";
		
		String msg = "";
		if(result == EDBMSG.OK)
			msg = String.format(format, trueFlag,"添加成功");
		else if(result == EDBMSG.FAIL){
			msg = String.format(format, falseFalg,"数据库异常");
		}
		else if(result == EDBMSG.ERROR){
			msg = String.format(format, falseFalg,"数据库出错");
		}
		else if(result == EDBMSG.USERNAME_REPEAT){
			msg = String.format(format, falseFalg,"用户名重复，请换一个重试！");
		}
		else {
			msg = String.format(format, falseFalg,"操作异常！");
		}
		
		return msg;
	}
}