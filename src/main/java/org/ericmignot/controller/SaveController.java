package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsPostParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.Controller;
import org.ericmignot.adapters.Renderer;
import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.SpecRenderer;
import org.ericmignot.adapters.SpecRepository;
import org.ericmignot.page.ShowPage;

public class SaveController implements Controller {

	private SpecRenderer renderer;
	
	public SaveController() {
		setRenderer( new ShowPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( "/specs/save/", request ) 
				&& containsPostParameter( "label", request )
				&& containsPostParameter( "content", request);
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) {
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
