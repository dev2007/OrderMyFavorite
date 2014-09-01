package com.awu.servlet.bl;

/***
 * Request return message format function.
 * @author Awu
 *
 */
public class CTipFunction {
	/***
	 * error message format head
	 */
	private final static String ERRORFLAG = "ERROR";
	
	/***
	 * true message format head
	 */
	private final static String OKFLAG = "OK";
	
	public CTipFunction(){
		
	}
	
	/***
	 * Get Error message with tip message
	 * the format is : ERROR;MSG;you get a error
	 * @param msg
	 * @return
	 */
	public static String ErrorWithMessage(String msg){
		return String.format("%s;MSG;%s", ERRORFLAG,msg);
	}
	
	/***
	 * Get Error message with url
	 * the format is : ERROR;URL;http://xxx.com
	 * @param url
	 * @return
	 */
	public static String ErrorWithUrl(String url){
		return String.format("%s;URL;%s", ERRORFLAG,url);
	}
	
	/***
	 * Get Error message
	 * the format is : ERROR
	 * @return
	 */
	public static String Error(){
		return ERRORFLAG;
	}
	
	/***
	 * Get Ok message
	 * the format is :OK
	 * @return
	 */
	public static String OK(){
		return OKFLAG;
	}
	
	/***
	 * Get OK message with tip message
	 * the format is : OK;MSG;you are ok
	 * @param msg
	 * @return
	 */
	public static String OKWithMessage(String msg){
		return String.format("%s;MSG;%s", OKFLAG,msg);
	}
	
	/***
	 * Get Ok message with url
	 * the format is : OK;URL;http:xxx.com
	 * @param url
	 * @return
	 */
	public static String OKWithUrl(String url){
		return String.format("%s;URL;%s", OKFLAG,url);
	}
}
