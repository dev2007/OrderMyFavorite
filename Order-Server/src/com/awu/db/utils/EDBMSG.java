package com.awu.db.utils;

/***
 * Database operator's message enumator string.
 * @author Awu
 *
 */
public enum EDBMSG {
	OK("ok",0),
	ERROR("error",1),
	FAIL("fail",2),
	USERNAME_ERROR("nameerror",3),
	PASSWORD_ERROR("pwderror",4),
	USERNAME_REPEAT("usernamerepeat",5);
	
	private String name;
	private int index;
	
	private EDBMSG(String name,int index){
		this.name = name;
		this.index = index;
	}
	
	/***
	 * Get enum object's index
	 * @return
	 */
	public int getIndex(){
		return index;
	}
	
	/***
	 * Get enum object's name
	 * @param index
	 * @return
	 */
	public static String getName(int index) {
        for (EDBMSG c : EDBMSG.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

	
	@Override
	public String toString(){
		return this.name;
	}
}
