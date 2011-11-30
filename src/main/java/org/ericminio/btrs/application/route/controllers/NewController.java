package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.uriIs;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.application.view.pages.NewSpecFormPage;
import org.ericminio.btrs.domain.SpecRepository;

public class NewController implements UserRequest {

	private Renderer renderer;
	
	public NewController() {
		setRenderer( new NewSpecFormPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/new", request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		renderer.render( out );
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

}
