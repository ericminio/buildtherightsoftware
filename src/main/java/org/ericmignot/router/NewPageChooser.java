package org.ericmignot.router;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.NewPage;
import org.ericmignot.page.Page;

public class NewPageChooser implements PageChooser {

	public String extractSpecX(String uri) {
		return null;
	}

	public Page getPage(HttpServletRequest request) {
		return new NewPage();
	}

	public boolean isConcernedBy(HttpServletRequest request) {
		String uri = request.getRequestURI();		
		if (uri == null) return false;
		if ( uri.equalsIgnoreCase( "/specs/new") ) return true;
		return false;
	}

}
