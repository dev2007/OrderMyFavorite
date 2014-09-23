package com.awu.TestCase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.db.CValidateDB;
import com.awu.db.utils.EDBMSG;

public class CValidateDBTest {
	private CValidateDB db = null;
	
	@Before
	public void setUp() throws Exception {
		db = CValidateDB._instance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidateLogin() {
		EDBMSG result = db.ValidateLogin("admin", "adminpwd");
		assertSame("ok", result.toString());
		result = db.ValidateLogin("admin", "123");
		assertSame("fail", result.toString());
	}

}
