package com.awu.listeners;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.ws.Endpoint;

import com.awu.db.utils.CDbUtils;
import com.awu.listeners.bl.ServicesEndpointManager;
import com.awu.services.CTabletService;

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
    	InitServices();
    	InitDbConnect();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("init services destroyed");
    }
    
    /**
     * add services.
     */
    private void InitServices(){
        System.out.println("init services start");
        ServicesEndpointManager servicesEndpointManager = new ServicesEndpointManager();
        servicesEndpointManager.AddService(new CTabletService());
        servicesEndpointManager.Pusblish();
    }
    
    /**
     * init database connect information.
     */
    private void InitDbConnect(){
    	System.out.println("init database connection.");
    	CDbUtils dbUtils = new CDbUtils();
    	try {
			dbUtils.GetConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.println("database ok");
    }
	
}
