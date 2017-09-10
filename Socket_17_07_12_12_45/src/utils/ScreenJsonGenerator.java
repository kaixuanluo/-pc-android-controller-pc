package utils;

import bean.MouseBean;
import bean.ScreenBean;

public class ScreenJsonGenerator {

	public ScreenJsonGenerator() {
		// TODO Auto-generated constructor stub
	}

	public static String generateScrreenJson (String operation) {
		return "{\"" + ScreenBean.TYPE0 + "\":" + ScreenBean.SCREEN + "," 
				+ "\"" + ScreenBean.OPERATION + "\":" + operation + "}";
	}
}
