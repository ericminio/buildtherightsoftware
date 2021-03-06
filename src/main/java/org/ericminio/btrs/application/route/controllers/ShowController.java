package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.uriStartsWith;
import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.application.view.SpecRenderer;
import org.ericminio.btrs.application.view.pages.ShowPage;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecRepository;

public class ShowController implements UserRequest {

	private static final String URI_PREFIX = "/specs/show/";
	protected SpecRenderer view;

	public ShowController() {
		setRenderer( new ShowPage() );
	}
	
	public void setRenderer(Renderer view) {
		this.view = (SpecRenderer) view;
	}

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriStartsWith( URI_PREFIX, request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		Spec spec = repository.getSpecByTitle( uriWithoutThePrefix( URI_PREFIX,  request) );
		view.setSpec( spec );
		view.render( out );
	}

}
