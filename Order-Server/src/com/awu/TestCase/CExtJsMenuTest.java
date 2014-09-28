package com.awu.TestCase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.entity.CExtJsMenu;

public class CExtJsMenuTest {
	private CExtJsMenu extJsMenu;
	
	@Before
	public void setUp() throws Exception {
		extJsMenu = new CExtJsMenu("1", "hello", true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToJson() {
		System.out.println(extJsMenu.toJson());
	}

}
