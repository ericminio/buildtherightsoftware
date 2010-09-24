package org.ericmignot.application;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.Controller;
import org.ericmignot.controller.ExecutionController;
import org.ericmignot.controller.ModifyController;
import org.ericmignot.controller.ShowController;


public class Router {

	private List<Controller> candidates;
	
	public Router() {
		candidates = new ArrayList<Controller>();
		candidates.add( new ShowController() );
		candidates.add( new ModifyController() );
		candidates.add( new ExecutionController() );
	}
	
	public Controller chooseController(HttpServletRequest request) {
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
