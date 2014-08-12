package com.awu.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.ws.Endpoint;

import com.awu.listeners.bl.ServicesEndpointManager;
import com.awu.services.TabletService;

/**
 * Application Lifecycle Listener implementation class InitServiceListener
 *
 */
@WebListener
public class InitServiceListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitServiceListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("init services start");
        ServicesEndpointManager servicesEndpointManager = new ServicesEndpointManager();
        servicesEndpointManager.AddService("TabletService", new TabletService());
        servicesEndpointManager.Pusblish();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("init services destroyed");
    }
	
}
