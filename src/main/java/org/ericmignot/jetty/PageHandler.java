package org.ericmignot.jetty;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.ericmignot.action.Show;
import org.ericmignot.core.Spec;
import org.ericmignot.store.FileRepository;
import org.ericmignot.store.Repository;
import org.ericmignot.util.HtmlParagraphSpec;

public class PageHandler extends AbstractHandler {
	private ActionRouter actionRouter;
	private PageRouter pageRouter;
	private Repository repository;

	public PageHandler() {
		setActionRouter(new ActionRouter());
		setPageRouter(new PageRouter());
		setRepository(new FileRepository("specs"));
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);

		Action action = actionRouter.chooseAction(request);
		if (action != null) {
			action.work(request, repository, response.getWriter());
			return;
		}
		Page choosen = pageRouter.choosePage(request);
		if (choosen != null) {
			response.getWriter().println(choosen.content());
			return;
		}
		
		displaySampleSpecByDefault(response);
	}

	protected void displaySampleSpecByDefault(HttpServletResponse response)
			throws IOException {
		HttpServletRequest redirect = mock(HttpServletRequest.class);
		when(redirect.getRequestURI()).thenReturn("/specs/show/sample");
		Show show = new Show();
		show.work(redirect, repository, response.getWriter());
	}

	public PageRouter getPageRouter() {
		return pageRouter;
	}

	public void setPageRouter(PageRouter router) {
		this.pageRouter = router;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setActionRouter(ActionRouter actionRouter) {
		this.actionRouter = actionRouter;
	}

}