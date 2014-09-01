package com.awu.servlet.bl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * user Login validate Interface
 * @author Awu
 *
 */
public interface IValidateBL {
	void Validate(HttpServletRequest request, HttpServletResponse response)
			throws IOException;
}
