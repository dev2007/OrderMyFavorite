package com.awu.servlet.bl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awu.db.CValidateDB;
import com.awu.db.utils.EDBMSG;
import com.awu.utils.CommonStr;

public class ValidateBL implements IValidateBL {
	public ValidateBL() {
	}

	@Override
	public void Validate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		response.setContentType(CommonStr.HTML_UTF8);
		PrintWriter writer = response.getWriter();

		if (userName.equals("")) {
			writer.write(CTipFunction.fail("用户名为空！"));
		} else if (password.equals("")) {
			writer.write(CTipFunction.fail("密码为空！"));
		} else {
			CValidateDB validateDBL = CValidateDB._instance();
			EDBMSG result = validateDBL.ValidateLogin(userName, password);

			// check result
			if (result.equals(EDBMSG.OK)) {
				writer.write(CTipFunction.success("登录成功"));
				validateOk(request, userName);
			} else {
				String message = "";
				switch (result) {
				case FAIL:
					message = "用户名或密码异常，请重试！";
					break;
				case ERROR:
					message = "系统发生异常，请重试或联系管理员！";
					break;
				default:
					message = "发生异常，请联系管理员！";
					break;
				}
				writer.write(CTipFunction.fail(message));
			}
		}
	}

	/**
	 * set login user's user name.
	 * 
	 * @param request
	 * @param userName
	 */
	private void validateOk(HttpServletRequest request, String userName) {
		request.getSession().setAttribute(CommonStr.SESSION_USERNAME, userName);
	}
}
