package org.ericmignot.jetty;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.route.CreateRoute;
import org.ericmignot.route.ListRoute;
import org.ericmignot.route.NewRoute;
import org.ericmignot.route.SaveRoute;

public class PageRouter {

	private List<PageBuilder> candidates;
	
	public PageRouter() {
		candidates = new ArrayList<PageBuilder>();
		candidates.add( new SaveRoute() );
		candidates.add( new NewRoute() );
		candidates.add( new CreateRoute() );
		candidates.add( new ListRoute() );
	}
	
	public Page choosePage(HttpServletRequest request) {
		for (PageBuilder candidate : candidates) {
			if ( candidate.isActivatedBy( request )) {
				return candidate.buildsPage( request );
			}
		}
		return null;
	}
	
}
