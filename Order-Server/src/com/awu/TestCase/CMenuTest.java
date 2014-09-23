package com.awu.TestCase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.entity.CMenu;

public class CMenuTest {
	CMenu menu;
	@Before
	public void setUp() throws Exception {
		menu = new CMenu("1", "0", "菜品管理");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToJson() {
		assertEquals("{\"id\":\"1\",\"parentId\":\"0\",\"name\":\"菜品管理\"}", menu.toJson());
	}

}
