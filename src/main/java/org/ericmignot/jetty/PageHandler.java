package org.ericmignot.jetty;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.ericmignot.action.ShowController;
import org.ericmignot.store.SpecFileStore;
import org.ericmignot.store.SpecRepository;

public class PageHandler extends AbstractHandler {
	
	private ActionRouter actionRouter;
	private PageRouter pageRouter;
	private SpecRepository repository;

	public PageHandler() {
		setActionRouter(new ActionRouter());
		setPageRouter(new PageRouter());
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

		System.out.println( request.getRequestURI() );
		
		try {
		Controller controller = actionRouter.chooseAction(request);
		if (controller != null) {
			controller.handle(request, repository, response.getWriter());
		}
		else {
			Page choosen = pageRouter.choosePage(request);
			if (choosen != null) {
				response.getWriter().println(choosen.content());
			}
			else {
				displaySampleSpecByDefault(response);
			}
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

	public PageRouter getPageRouter() {
		return pageRouter;
	}

	public void setPageRouter(PageRouter router) {
		this.pageRouter = router;
	}

	public SpecRepository getRepository() {
		return repository;
	}

	public void setActionRouter(ActionRouter actionRouter) {
		this.actionRouter = actionRouter;
	}

	public void setWorkingDirectory(String directory) {
		actionRouter.setWorkingDirectory( directory );
		setRepository(new SpecFileStore( directory ) );
		
		LabelMigration migration = new LabelMigration();
		migration.setWorkingDirectory( directory );
		migration.work();
		
	}

}