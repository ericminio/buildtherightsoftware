package org.ericmignot.jetty;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.action.Modify;
import org.ericmignot.action.Show;


public class ActionRouter {

	private List<Action> candidates;
	
	public ActionRouter() {
		candidates = new ArrayList<Action>();
		candidates.add( new Show() );
		candidates.add( new Modify() );
	}
	
	public Action chooseAction(HttpServletRequest request) {
		for (Action candidate : candidates) {
			if ( candidate.isActivatedBy( request )) {
				return candidate;
			}
		}
		return null;
	}
	
}
