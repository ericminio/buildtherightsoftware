package org.ericmignot.router;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.Page;
import org.ericmignot.page.ShowPage;

public class PageRouter {

	private List<PageChooser> choosers;
	
	public PageRouter() {
		choosers = new ArrayList<PageChooser>();
		choosers.add( new ResultPageChooser() );
		choosers.add( new ShowPageChooser() );
		choosers.add( new ModifyPageChooser() );
		choosers.add( new SavePageChooser() );
		choosers.add( new NewPageChooser() );
	}
	
	public Page choosePage(HttpServletRequest request) {
		for (PageChooser chooser : choosers) {
			if ( chooser.isConcernedBy( request )) {
				return chooser.getPage( request );
			}
		}
		return new ShowPage( "sample" );
	}
	
}
