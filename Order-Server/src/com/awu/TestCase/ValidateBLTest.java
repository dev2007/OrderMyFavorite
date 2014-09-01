package com.awu.TestCase;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.auto.Mock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.servlet.bl.ValidateBL;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ValidateBLTest {
	private ValidateBL bl;
	
	@Before
	public void setUp() throws Exception {
		bl = new ValidateBL();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidate() {
		Mockery context = new Mockery();
		final HttpServletRequest request = context.mock(HttpServletRequest.class);
		final HttpServletResponse response = context.mock(HttpServletResponse.class);
		context.checking(new Expectations(){
			{
				oneOf(request).getParameter("username");will(returnValue("123"));
				oneOf(request).getParameter("password");will(returnValue("321"));
			}

		});

		try {
			bl.Validate(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
