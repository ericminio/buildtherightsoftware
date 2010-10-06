package org.ericmignot.application;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.controller.CreationController;
import org.ericmignot.controller.DefaultController;
import org.ericmignot.controller.ExecutionController;
import org.ericmignot.controller.ListController;
import org.ericmignot.controller.ModifyController;
import org.ericmignot.controller.NewController;
import org.ericmignot.controller.SaveController;
import org.ericmignot.controller.ShowController;


public class Router {

	private List<UserRequest> candidates;
	private ExecutionController executionController;
	
	public Router() {
		candidates = new ArrayList<UserRequest>();
		candidates.add( new ShowController() );
		candidates.add( new ModifyController() );
		executionController = new ExecutionController();
		candidates.add( executionController );
		candidates.add( new ListController() );
		candidates.add( new NewController() );
		candidates.add( new CreationController() );
		candidates.add( new SaveController() );
	}
	
	public UserRequest chooseController(HttpServletRequest request) {
		for (UserRequest candidate : candidates) {
			if ( candidate.isActivatedBy( request )) {
				return candidate;
			}
		}
		return new DefaultController();
	}

	public void setWorkingDirectory(String directory) {
		executionController.setWorkingDirectory(directory);
	}
	
}
