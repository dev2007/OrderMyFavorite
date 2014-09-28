package com.awu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.servlet.bl.CLoadMenuBL;
import com.awu.servlet.bl.ILoadMenuBL;

/**
 * Servlet implementation class LoadMenu
 */
@WebServlet("/LoadMenu")
public class LoadMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ILoadMenuBL loadMenuBL = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadMenu() {
        super();
        loadMenuBL = new CLoadMenuBL();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		loadMenuBL.loadMenu(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
