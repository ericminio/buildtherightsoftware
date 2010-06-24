package org.ericmignot.page;

import javax.servlet.http.HttpServletRequest;

public class PageChooser {

	public Page choosePage(HttpServletRequest request) {
		String seCandidate = extractSe( request.getRequestURI() );

		if (seCandidate!=null 
				&& request.getParameter( "repo" )!=null
				) {
			return new ResultPage( seCandidate, request.getParameter( "repo" ) );
		}
		else {
			return new HomePage();
		}
	}
	
	public String extractSe(String uri) {
		if (uri == null) return null;
		
		if ( uri.indexOf( "/specs")!=-1  
				&& uri.indexOf( "/execute" )!=-1
				) {
			int specsIndex = uri.indexOf( "/specs");
			int executeIndex = uri.indexOf( "/execute" );
			return uri.substring( specsIndex+7, executeIndex );
		}
		else {
			return null;
		}
	}

	

}
