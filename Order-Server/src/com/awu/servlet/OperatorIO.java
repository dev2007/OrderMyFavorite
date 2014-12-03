package com.awu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.servlet.bl.COperatorBL;

/**
 * Servlet implementation class OperatorIO
 */
@WebServlet("/OperatorIO")
public class OperatorIO extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private COperatorBL bl = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperatorIO() {
        super();
        bl = new COperatorBL();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//just query now,may be has add,modify,delete.
		bl.run(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
