package com.awu.TestCase.listeners.bl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.awu.listeners.bl.ServicesEndpointManager;
import com.awu.services.CTabletService;

public class ServicesEndpointManagerTest {
	private ServicesEndpointManager manager;
	
	@Before
	public void setUp() throws Exception {
		manager = new ServicesEndpointManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testServicesEndpointManager() {
		
	}

	@Test
	public void testAddService() {
		assertSame(manager.GetServiceByName("TabletService"), null);
		manager.AddService( new CTabletService());
		assertSame(manager.GetServiceByName("TabletService").ServiceName(), "TabletService");
	}

	@Test
	public void testPusblish() {
		manager.AddService(new CTabletService());
		assertSame(manager.GetServiceByName("TabletService").ServiceName(), "TabletService");
	}

	@Test
	public void testPrintAllServices() {
		manager.AddService(new CTabletService());
		manager.PrintAllServices();
	}

}
