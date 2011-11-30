package org.ericminio.btrs.application.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.ericminio.btrs.application.route.Router;
import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.domain.SpecRepository;
import org.ericminio.btrs.store.SpecFileStore;

public class FeatureHandler extends AbstractHandler {
	
	private Router router;
	private SpecRepository repository;

	public FeatureHandler() {
		setRouter(new Router());
		setWorkingDirectory( "specs" );
	}

	public void setRepository(SpecRepository repository) {
		this.repository = repository;
	}

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType( "text/html;charset=utf-8" );
		response.setStatus( HttpServletResponse.SC_OK );
		baseRequest.setHandled( true );

		UserRequest controller = router.chooseController( request );
		try {
			controller.handle( request, repository, response.getWriter() );
		} catch (Exception e) {
			response.getWriter().write( "Error" );
		}
	}

	public SpecRepository getRepository() {
		return repository;
	}

	public void setRouter(Router actionRouter) {
		this.router = actionRouter;
	}

	public void setWorkingDirectory(String directory) {
		router.setWorkingDirectory( directory );
		setRepository( new SpecFileStore( directory ) );
	}

}