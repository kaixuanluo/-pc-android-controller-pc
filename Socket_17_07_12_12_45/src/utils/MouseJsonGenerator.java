package utils;

import bean.MouseBean;

public class MouseJsonGenerator {

	public static String generateMouse(String type, float xDp, float yDp) {
		return "{\"" + MouseBean.TYPE0 + "\":"+ MouseBean.MOUSE + "," 
				+ "\"" + MouseBean.TYPE + "\":" + type + "," 
				+ "\"" + MouseBean.X_DP + "\":" + xDp + ","
				+ "\"" + MouseBean.Y_DP + "\":" + yDp + "}";
	}

	public static String generateMouse(String type, float xDp, float yDp, float xDp2, float yDp2) {
		return "{\"" + MouseBean.TYPE0 + "\":" + MouseBean.MOUSE + ","
				+ "\"" + MouseBean.TYPE + "\":" + type + "," 
				+ "\"" + MouseBean.X_DP + "\":" + xDp + "," 
				+ "\"" + MouseBean.Y_DP + "\":" + yDp + ","
				+ "\"" + MouseBean.X_DP2 + "\":" + xDp2 + ","
				+ "\"" + MouseBean.Y_DP2 + "\":" + yDp2 + "}";
	}

}
