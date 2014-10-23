package com.awu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

/**
 * File operator.
 * @author Awu
 *
 */
public class CAwuFile {
	
	/**
	 * Read specify folder file content.
	 * @param request
	 * @param filePath
	 * @return
	 */
	public static String readFile(HttpServletRequest request,String filePath){
		@SuppressWarnings("deprecation")
		String path = request.getRealPath(filePath);
		
		File file=new File(path);
        String content = "";
        if(file.isFile() && file.exists()){ //charge file is exists.
			try {
	            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf8");
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;

	            while((lineTxt = bufferedReader.readLine()) != null){
	                content += lineTxt;
	            }
	            read.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        if(content.equals("")){
        	content = ScriptAlertStr.NOCONTENT;
        }
		return content;
	}
}
