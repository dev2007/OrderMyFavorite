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
	
	public ServicesEndpointManager(){
		
	}
	
	/**
	 * add service to factory
	 * @param name
	 * @param service
	 */
	public void AddService(String name,IService service){
		//hasn't been add
		if(!serviceDic.containsKey(name)){
			serviceDic.put(name, service);
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
			Endpoint.publish(""+key, val);
			System.out.println("publish " + key + " ok");
			}
	}
	
	
}
