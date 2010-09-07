package org.ericmignot.jetty;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.ShowPage;
import org.ericmignot.page.activation.CreatePageActivator;
import org.ericmignot.page.activation.ExecutePageActivator;
import org.ericmignot.page.activation.ModifyPageActivator;
import org.ericmignot.page.activation.NewPageActivator;
import org.ericmignot.page.activation.SavePageActivator;
import org.ericmignot.page.activation.ShowPageActivator;

public class PageRouter {

	private List<PageBuilder> candidates;
	
	public PageRouter() {
		candidates = new ArrayList<PageBuilder>();
		candidates.add( new ExecutePageActivator() );
		candidates.add( new ShowPageActivator() );
		candidates.add( new ModifyPageActivator() );
		candidates.add( new SavePageActivator() );
		candidates.add( new NewPageActivator() );
		candidates.add( new CreatePageActivator() );
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
