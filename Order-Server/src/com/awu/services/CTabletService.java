package com.awu.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

/***
 * Table service
 * provide interface for table data exchange
 * @author Awu
 *
 */
@WebService
public class CTabletService implements IService{
	@WebMethod
	public String ServiceName(){
		return "TabletService";
	}
}
