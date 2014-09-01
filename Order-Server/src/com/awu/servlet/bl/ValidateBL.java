package com.awu.servlet.bl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateBL implements IValidateBL {	
	private String loginOKPage = "CorpLogin2.jsp";

	public ValidateBL() {
	}

	@Override
	public void Validate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		PrintWriter writer = response.getWriter();
		response.setContentType("text;html;charset=utf-8");

		if (userName.equals("")) {
			writer.write("�û���Ϊ�գ�");
		} else if (password.equals("")) {
			writer.write("����Ϊ�գ�");
		} else {
			writer.write(CTipFunction.OKWithUrl(loginOKPage));
		}
	}
}
