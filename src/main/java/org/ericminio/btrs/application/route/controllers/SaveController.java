package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.containsPostParameter;
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

public class SaveController implements UserRequest {

	private SpecRenderer renderer;
	
	public SaveController() {
		setRenderer( new ShowPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriStartsWith( "/specs/save/", request ) 
				&& containsPostParameter( "label", request )
				&& containsPostParameter( "content", request);
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		Spec spec = repository.getSpecByTitle( uriWithoutThePrefix( "/specs/save/",  request) );
		spec.setLabel( request.getParameter( "label" ) );
		spec.setContent( request.getParameter( "content") );
		repository.saveSpec( spec );
		renderer.setSpec( spec );
		renderer.render( out );
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = (SpecRenderer) renderer;
	}

}
