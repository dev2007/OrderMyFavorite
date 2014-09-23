package com.awu.servlet.bl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.awu.db.CLoadMenuDB;
import com.awu.db.utils.EDBMSG;

public class CLoadMenuBL implements ILoadMenuBL {
	private CLoadMenuDB loadMenuDB;
	private final String FILE_EXTEND = ".json";
	
	public CLoadMenuBL(){
		this.loadMenuDB = CLoadMenuDB._instance();
	}
	
	/**
	 * Load user's menu.
	 * Save menu data in a file in the folder "menudata".
	 * If save success,return OK,else return ERROR.
	 */
	@Override
	public EDBMSG loadMenu(HttpServletRequest request,String userName) {
		String jsonString = loadMenuDB.getUserMenu(userName).toJson();
		String realPath = request.getRealPath("menudata");
		String path = realPath +"/" +userName + FILE_EXTEND;
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(jsonString);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			return EDBMSG.ERROR;
		}
		return EDBMSG.OK;
	}

}
