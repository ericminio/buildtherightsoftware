package org.ericmignot.router;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.CreatePage;
import org.ericmignot.page.Page;

public class CreatePageChooser implements PageChooser {

	public String extractSpecX(String queryString) {
		return queryString.substring("specXName=".length());
	}

	public Page getPage(HttpServletRequest request) {
		return new CreatePage( extractSpecX( request.getQueryString() ) );
	}

	public boolean isConcernedBy(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (uri == null) return false;
		if (! uri.equalsIgnoreCase( "/specs/create" )) return false;

		String queryString = request.getQueryString();
		if (queryString == null) return false;
		if (! queryString.startsWith( "specXName=" )) return false;
		if (! (queryString.length() > "specXName=".length()) ) return false;
		
		return true;
	}

}
