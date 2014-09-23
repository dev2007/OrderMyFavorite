package com.awu.TestCase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.entity.CMenu;
import com.awu.entity.CMenuArray;

public class CMenuArrayTest {
	CMenuArray array;
	
	@Before
	public void setUp() throws Exception {
		array = new CMenuArray();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAppend() {
		CMenu menu = new CMenu("1", "0", "菜品管理");
		array.append(menu);
		System.out.println(array.toJson());
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testToJson() {
		CMenu menu = new CMenu("1", "0", "菜品管理");
		CMenu menu2 = new CMenu("2", "0", "人员管理");
		CMenu menu3 = new CMenu("3", "0", "权限管理");
		CMenu menu4 = new CMenu("31", "3", "权限组");
		array.append(menu);
		array.append(menu2);
		array.append(menu3);
		array.append(menu4);
		System.out.println(array.toJson());
	}

}
