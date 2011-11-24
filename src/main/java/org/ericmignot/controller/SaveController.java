package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsPostParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.Renderer;
import org.ericmignot.adapters.ui.SpecRenderer;
import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.page.ShowPage;

public class SaveController implements UserRequest {

	private SpecRenderer renderer;
	
	public SaveController() {
		setRenderer( new ShowPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( "/specs/save/", request ) 
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
