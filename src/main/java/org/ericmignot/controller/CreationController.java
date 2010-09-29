package org.ericmignot.controller;

import static org.ericmignot.util.FileUtils.readFile;
import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.getQueryStringValueOf;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.Renderer;
import org.ericmignot.adapters.ui.SpecRenderer;
import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.domain.PlainTextSpec;
import org.ericmignot.page.ShowPage;

public class CreationController implements UserRequest {

	private SpecRenderer renderer;
	
	public CreationController() {
		setRenderer( new ShowPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/create", request ) 
			&& containsGetParameter( "spec", request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) {
		PlainTextSpec spec = new PlainTextSpec( getQueryStringValueOf( "spec", request ) );
		spec.setContent( readFile( "target/html/newSpecContent.html" ) );
		
		repository.saveSpec( spec );
		renderer.setSpec( spec );
		renderer.render( out );
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = (SpecRenderer) renderer;
	}

}
