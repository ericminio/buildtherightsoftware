package org.ericminio.btrs.application.route.controllers;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestInformationExtractor {
	
	public static boolean trueIfUriStartsWith(String prefix, HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri != null && (uri.length() >= (prefix.length() + 1) ) 
						   && uri.startsWith( prefix );
	}
	
	public static boolean uriIs(String expected, HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri != null && uri.equalsIgnoreCase( expected );
	}
	
	public static boolean containsPostParameter(String param, HttpServletRequest request) {
		return request.getParameter( param ) != null;
	}
	
	public static boolean containsNotEmptyGetParameter(String param, HttpServletRequest request) {
		return containsGetParameter(param, request) && isNotEmpty(param, request);
	}

	protected static boolean isNotEmpty(String param, HttpServletRequest request) {
		String queryString = request.getQueryString();
		return queryString.length() > (param + "=").length();
	}

	public static boolean containsGetParameter(String param, HttpServletRequest request) {
		String queryString = request.getQueryString();
		return queryString != null && queryString.startsWith( param+"=" );
	}

	public static String uriWithoutThePrefix(String prefix, HttpServletRequest request) {
		return request.getRequestURI().substring( prefix.length() );
	}
	
	public static String getQueryStringValueOf(String param, HttpServletRequest request) {
		if ( request != null 
				&& request.getQueryString() != null ) {
			return request.getQueryString().substring( ( param + "=" ).length() );
		}
		else {
			return "";
		}
	}
}
