package org.ericminio.btrs.application.route.controllers;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.application.view.pages.WelcomePage;
import org.ericminio.btrs.domain.SpecRepository;

public class DefaultController implements UserRequest {

	private Renderer view;
	
	public DefaultController() {
		setRenderer( new WelcomePage() );
	}
	
	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		view.render( out );
	}

	public Renderer getRenderer() {
		return view;
	}

	public boolean isActivatedBy(HttpServletRequest request) {
		return true;
	}

	public void setRenderer(Renderer renderer) {
		this.view = renderer;
	}

}
