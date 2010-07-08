package org.ericmignot.router;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.Page;
import org.ericmignot.page.ShowPage;

public class ShowPageChooser implements PageChooser {

	public boolean isConcernedBy(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		if (uri == null) return false;
		if (uri.length() < 13 ) return false;
		if (! uri.startsWith( "/specs/show/" ) ) return false;
		return true;
	}

	public String extractSpecX(String uri) {
		return uri.substring( "/specs/show/".length() );
	}

	public Page getPage(HttpServletRequest request) {
		return new ShowPage( extractSpecX( request.getRequestURI() ) );
	}

}
