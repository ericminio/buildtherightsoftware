package org.ericmignot.router;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.ModifyPage;
import org.ericmignot.page.Page;

public class ModifyPageChooser implements PageChooser {

	public String extractSpecX(String uri) {
		return uri.substring( "/specs/modify/".length() );
	}

	public Page getPage(HttpServletRequest request) {
		return new ModifyPage( extractSpecX( request.getRequestURI() ) );
	}

	public boolean isConcernedBy(HttpServletRequest request) {
		String uri = request.getRequestURI();
		
		if (uri == null) return false;
		if (uri.length() < 15 ) return false;
		if (! uri.startsWith( "/specs/modify/" ) ) return false;
		return true;
	}

}
