package com.awu.listeners.bl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.ws.Endpoint;

import com.awu.services.IService;

/**
 * Service manager
 * publish service by endpoint.publish
 * @author Awu
 *
 */
public class ServicesEndpointManager {
	private Map<String, IService> serviceDic = new HashMap<String,IService>();
	
	/**
	 * constructor
	 */
	public ServicesEndpointManager(){
		
	}
	
	/**
	 * add service to factory
	 * @param name
	 * @param service
	 */
	public void AddService(IService service){
		//hasn't been add,add it
		if(!serviceDic.containsKey(service.ServiceName())){
			serviceDic.put(service.ServiceName(), service);
		}
	}
	
	/**
	 * publish all added service
	 */
	public void Pusblish(){
		Iterator<?> iter = serviceDic.entrySet().iterator();
		//loop publish services
		while (iter.hasNext()) { 
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey(); 
			Object val = entry.getValue();
			//TODO:获取本机ip
			Endpoint.publish("http://localhost:9898/"+key, val);
			System.out.println("publish " + key + " ok");
			}
	}
	
	/**
	 * Print all added services names.
	 */
	public void PrintAllServices(){
		System.out.println("---------------------------");
		System.out.println("All services:");
		Iterator<?> iter = serviceDic.entrySet().iterator();
		//loop publish services
		while (iter.hasNext()) { 
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey(); 
			Object val = entry.getValue();
			System.out.println(key + ":" + ((IService)val).ServiceName() );
			}
		System.out.println("---------------------------");
	}
	
	/**
	 * Get the service by its name.
	 * @param name
	 * @return
	 */
	public IService GetServiceByName(String name){
		return serviceDic.get(name);
	}
	
	
}
