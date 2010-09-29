package org.ericmignot.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.controller.ShowController;
import org.ericmignot.store.LabelMigration;
import org.ericmignot.store.SpecFileStore;

public class FeatureHandler extends AbstractHandler {
	
	private Router router;
	private SpecRepository repository;

	public FeatureHandler() {
		setRouter(new Router());
		setWorkingDirectory("specs");
	}

	public void setRepository(SpecRepository repository) {
		this.repository = repository;
	}

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);

		try {
		UserRequest controller = router.chooseController(request);
		if (controller != null) {
			controller.handle(request, repository, response.getWriter());
		}
		else {
				displaySampleSpecByDefault(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void displaySampleSpecByDefault(HttpServletResponse response)
			throws IOException {
		HttpServletRequest redirect = mock(HttpServletRequest.class);
		when(redirect.getRequestURI()).thenReturn("/specs/show/sample");
		ShowController show = new ShowController();
		show.handle(redirect, repository, response.getWriter());
	}

	public SpecRepository getRepository() {
		return repository;
	}

	public void setRouter(Router actionRouter) {
		this.router = actionRouter;
	}

	public void setWorkingDirectory(String directory) {
		router.setWorkingDirectory( directory );
		setRepository(new SpecFileStore( directory ) );
		
		LabelMigration migration = new LabelMigration();
		migration.setWorkingDirectory( directory );
		migration.work();
		
	}

}