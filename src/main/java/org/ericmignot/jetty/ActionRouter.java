package org.ericmignot.jetty;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.action.ExecutionController;
import org.ericmignot.action.ModifyController;
import org.ericmignot.action.ShowController;


public class ActionRouter {

	private List<Controller> candidates;
	
	public ActionRouter() {
		candidates = new ArrayList<Controller>();
		candidates.add( new ShowController() );
		candidates.add( new ModifyController() );
		candidates.add( new ExecutionController() );
	}
	
	public Controller chooseAction(HttpServletRequest request) {
		for (Controller candidate : candidates) {
			if ( candidate.isActivatedBy( request )) {
				return candidate;
			}
		}
		return null;
	}

	public void setWorkingDirectory(String directory) {
		for (Controller controller : candidates) {
			controller.setWorkingDirectory(directory);
		}
	}
	
}
