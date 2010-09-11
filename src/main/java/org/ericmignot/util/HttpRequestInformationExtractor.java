package org.ericmignot.util;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestInformationExtractor {

	public static boolean uriStartsWith(String prefix, HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		if (uri == null) return false;
		if (uri.length() < (prefix.length() + 1) ) return false;
		if (! uri.startsWith( prefix ) ) return false;
		
		return true;
	}
	
	public static boolean uriIs(String prefix, HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		if (uri == null) return false;
		if (! uri.equalsIgnoreCase( prefix ) ) return false;
		
		return true;
	}
	
	public static boolean containsPostParameter(String param, HttpServletRequest request) {
		return request.getParameter( param ) != null;
	}
	
	public static boolean containsGetParameter(String param, HttpServletRequest request) {
		String queryString = request.getQueryString();
		if (queryString == null) return false;
		if (! queryString.startsWith( param+"=" )) return false;
		if (! (queryString.length() > (param + "=").length()) ) return false;
		
		return true;
	}
	
	public static String removePrefixFromUri(String prefix, HttpServletRequest request) {
		return request.getRequestURI().substring( prefix.length() );
	}
	
	public static String getQueryStringValueOf(String param, HttpServletRequest request) {
		return request.getQueryString().substring( ( param + "=" ).length() );
	}
}
