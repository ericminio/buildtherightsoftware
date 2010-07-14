package org.ericmignot.router;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.Page;
import org.ericmignot.page.SavePage;

public class SavePageChooser implements PageChooser {

	public String extractSpecX(String uri) {
		return uri.substring( uri.indexOf( "/specs/save/" ) + 12 );
	}

	public Page getPage(HttpServletRequest request) {
		String specX = extractSpecX( request.getRequestURI() );
		String specXContent = request.getParameter( "specX" );
		return new SavePage( specX, specXContent );
	}

	public boolean isConcernedBy(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		if (uri == null) return false;
		if (uri.length() < 13 ) return false;
		if (! uri.startsWith( "/specs/save/" ) ) return false;
		
		String specXContent = request.getParameter( "specX" );
		if (specXContent == null) return false;
		
		return true;
	}

}
