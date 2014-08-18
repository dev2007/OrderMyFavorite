package com.awu.TestCase.services;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awu.services.CTabletService;

public class CTabletServiceTest {

	@Test
	public void testServiceName() {
		assertSame("TabletService", new CTabletService().ServiceName());
	}

}
