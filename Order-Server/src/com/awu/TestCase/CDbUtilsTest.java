package com.awu.TestCase;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.db.utils.CDbUtils;

public class CDbUtilsTest {
	private CDbUtils utils;
	
	@Before
	public void setUp() throws Exception {
		utils = new CDbUtils();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetConnection() {
		try {
			assertTrue(utils.GetConnection() != null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectSingleRow(){
		try {
			Hashtable<String, Object> table = utils.selectSingleRow("select * from sex where idsex = 0");
			assertTrue(table.containsKey("fullname"));
			assertEquals("女", table.get("fullname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectSingleRowWithParams(){
		try {
			ArrayList<Object> params = new ArrayList<>();
			params.add(0);
			Hashtable<String, Object> table = utils.selectSingleRow("select * from sex where idsex = ?",params);
			assertTrue(table.containsKey("fullname"));
			assertEquals("女", table.get("fullname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExecuteQuery(){
		try {
			ArrayList<Hashtable<String, Object>> dt = utils.executeQuery("select * from sex");
			assertSame(2, dt.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExecuteQueryWithParams(){
		ArrayList<Object> params = new ArrayList<>();
		params.add(0);
		try {
			ArrayList<Hashtable<String, Object>> dt = utils.executeQuery("select * from sex where idsex = ?", params);
			assertSame(1, dt.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
