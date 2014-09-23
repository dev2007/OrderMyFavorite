package com.awu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.servlet.bl.CMenuPageBL;
import com.awu.servlet.bl.IMenuPageBL;

/**
 * Servlet implementation class MenuPage
 */
@WebServlet("/MenuPage")
public class MenuPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMenuPageBL menuPageBL = new CMenuPageBL();
		
		PrintWriter writer = response.getWriter();
		String userName = (String)request.getAttribute("username");
		String menuId =  (String)request.getAttribute("menuid");
		writer.write(menuPageBL.getMenuData(userName,menuId));
	}

}
