package com.awu.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.awu.utils.CAwuFile;
import com.awu.utils.CommonStr;

/**
 * Application init class. 1.Bind menu id with page name.
 * 
 * @author Awu
 * 
 */
public class CApp {
	private static Map<String, String> menuWithPage;

	public CApp() {
		menuWithPage = new HashMap<String, String>();
		initMenuPage();
	}

	private void initMenuPage() {
		menuWithPage.put("1", "");
		menuWithPage.put("2", "Operator.html");
		menuWithPage.put("4", "");
		menuWithPage.put("5", "");
	}

	/**
	 * Get menu page name by its id.
	 * 
	 * @param menuId
	 * @return
	 */
	public static ArrayList<String> traversalMenuPage(String menuId) {
		ArrayList<String> result = new ArrayList<>();
		Iterator iter = menuWithPage.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			result.add((String) entry.getValue());
		}
		return result;
	}
	
	/***
	 * Get page content by menu id.
	 * @param menuId
	 * @return
	 */
	public static String getMenuPageContent(HttpServletRequest request,String menuId){
		String fileName = menuWithPage.get(menuId);
		
		if(null == fileName)
			return "";
		String filePath = CommonStr.MENUHTML_FOLDER + "/" + fileName;
		return CAwuFile.readFile(request,filePath);
	}
}
