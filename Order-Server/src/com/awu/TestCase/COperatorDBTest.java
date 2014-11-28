package com.awu.TestCase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.db.COperatorDB;

public class COperatorDBTest {
	private COperatorDB dbl = null;
	
	@Before
	public void setUp() throws Exception {
		dbl = COperatorDB._instance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetOperatorList() {
		String json = dbl.getOperatorList(2,1).toJson();
		System.out.println(json);
	}

}
