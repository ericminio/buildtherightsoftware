package org.ericminio.btrs.application.route;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.controllers.CreationController;
import org.ericminio.btrs.application.route.controllers.DefaultController;
import org.ericminio.btrs.application.route.controllers.ExecutionController;
import org.ericminio.btrs.application.route.controllers.LabelListController;
import org.ericminio.btrs.application.route.controllers.ModifyController;
import org.ericminio.btrs.application.route.controllers.NewController;
import org.ericminio.btrs.application.route.controllers.SaveController;
import org.ericminio.btrs.application.route.controllers.ShowController;
import org.ericminio.btrs.application.route.controllers.SpecList;


public class Router {

	private List<UserRequest> candidates;
	private ExecutionController executionController;
	
	public Router() {
		candidates = new ArrayList<UserRequest>();
		candidates.add( new ShowController() );
		candidates.add( new ModifyController() );
		executionController = new ExecutionController();
		candidates.add( executionController );
		candidates.add( new SpecList() );
		candidates.add( new NewController() );
		candidates.add( new CreationController() );
		candidates.add( new SaveController() );
		candidates.add( new LabelListController() );
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
