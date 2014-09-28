package com.awu.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.db.CLoadMenuDB;
import com.awu.entity.CExtJsMenuRoot;
import com.awu.entity.CMenuArray;

public class CLoadMenuDBTest {
	CLoadMenuDB db;
	@Before
	public void setUp() throws Exception {
		db = CLoadMenuDB._instance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserMenu() {
		CMenuArray array = db.getUserMenu("admin");
		System.out.println(array.toJson());
	}
	
	@Test
	public void testGetUserExtMenu(){
		CExtJsMenuRoot root = db.getUserExtMenu("admin");
		System.out.println(root.toJson());
	}

}
