package com.awu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.servlet.bl.CDishBL;

/**
 * Servlet implementation class DishIO
 */
@WebServlet("/DishIO")
public class DishIO extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CDishBL bl;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishIO() {
        super();
        bl = new CDishBL();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bl.run(request, response);
	}

}
