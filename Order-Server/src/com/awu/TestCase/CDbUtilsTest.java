package com.awu.TestCase;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.db.entity.CDataRow;
import com.awu.db.entity.CDataTable;
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
			CDataRow row = utils.selectSingleRow("select * from sex where idsex = 0");
			assertEquals("女", row.value("fullname"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectSingleRowWithParams(){
		try {
			ArrayList<Object> params = new ArrayList<>();
			params.add(0);
			CDataRow row = utils.selectSingleRow("select * from sex where idsex = ?",params);
			assertEquals("女", row.value("fullname"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExecuteQuery(){
		try {
			CDataTable dt = utils.executeQuery("select * from sex");
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
			CDataTable dt = utils.executeQuery("select * from sex where idsex = ?", params);
			assertSame(1, dt.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
