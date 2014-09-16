package com.awu.db;

/***
 * Database operator's message enumator string.
 * @author Awu
 *
 */
public enum EDBMSG {
	OK("ok",1),
	ERROR("error",2),
	FAIL("fail",3),
	USERNAME_ERROR("nameerror",4),
	PASSWORD_ERROR("pwderror",5);
	
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
