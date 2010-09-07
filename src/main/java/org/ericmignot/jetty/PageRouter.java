package org.ericmignot.jetty;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.ShowPage;
import org.ericmignot.page.route.CreateRoute;
import org.ericmignot.page.route.ExecuteRoute;
import org.ericmignot.page.route.ModifyRoute;
import org.ericmignot.page.route.NewRoute;
import org.ericmignot.page.route.SaveRoute;
import org.ericmignot.page.route.ShowRoute;

public class PageRouter {

	private List<PageBuilder> candidates;
	
	public PageRouter() {
		candidates = new ArrayList<PageBuilder>();
		candidates.add( new ExecuteRoute() );
		candidates.add( new ShowRoute() );
		candidates.add( new ModifyRoute() );
		candidates.add( new SaveRoute() );
		candidates.add( new NewRoute() );
		candidates.add( new CreateRoute() );
	}
	
	public Page choosePage(HttpServletRequest request) {
		for (PageBuilder candidate : candidates) {
			if ( candidate.isActivatedBy( request )) {
				return candidate.buildsPage( request );
			}
		}
		return new ShowPage( "sample" );
	}
	
}
