package org.ericmignot.router;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.HomePage;
import org.ericmignot.page.Page;

public class PageRouter {

	private List<PageChooser> choosers;
	
	public PageRouter() {
		choosers = new ArrayList<PageChooser>();
		choosers.add( new ResultPageChooser() );
		choosers.add( new ShowPageChooser() );
	}
	
	public Page choosePage(HttpServletRequest request) {

		for (PageChooser chooser : choosers) {
			if ( chooser.isConcernedBy( request )) {
				return chooser.getPage( request );
			}
		}
		return new HomePage();
	}
	
}
