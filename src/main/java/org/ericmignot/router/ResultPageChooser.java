package org.ericmignot.router;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.Page;
import org.ericmignot.page.ResultPage;

public class ResultPageChooser implements PageChooser {

	public boolean isConcernedBy(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		if (uri == null) return false;
		if (uri.length() < 16 ) return false;
		if (! uri.startsWith( "/specs/execute/" ) ) return false;
		
		String repo = request.getParameter( "repo" );
		if (repo == null) return false;
		
		return true;
	}

	public String extractSpecX(String uri) {
		int indexOfPrefix = uri.indexOf( "/specs/execute/" );
		return uri.substring( indexOfPrefix+15 );
	}

	public Page getPage(HttpServletRequest request) {
		String specX = extractSpecX( request.getRequestURI() );
		String repo = request.getParameter( "repo" );
		return new ResultPage( specX, repo );
	}

}
