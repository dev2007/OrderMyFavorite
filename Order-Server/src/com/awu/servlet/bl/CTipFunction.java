package com.awu.servlet.bl;

/***
 * Request return message format function.
 * @author Awu
 *
 */
public class CTipFunction {
	/**
	 * Form response json string format for success.
	 */
	private static final String SUCCESSFORMAT_STR = "{success:true,msg:'%s'}";
	
	/**
	 * Form response json string format for fail.
	 */
	private static final String FAILFORMAT_STR = "{success:false,msg:'%s'}";
	
	public CTipFunction(){
		
	}
	
	/**
	 * Get success response string.
	 * @param msg
	 * @return
	 */
	public static String success(String msg){
		return String.format(SUCCESSFORMAT_STR, msg);
	}
	
	/**
	 * Get fail response string.
	 * @param msg
	 * @return
	 */
	public static String fail(String msg){
		return String.format(FAILFORMAT_STR,msg);
	}
}
