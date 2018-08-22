package utils;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	public static String getUrl(HttpServletRequest request, String url) {
		return request.getContextPath() + url;
	}
}
