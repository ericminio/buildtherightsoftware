package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.getQueryStringValueOf;
import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.uriIs;
import static org.ericminio.btrs.store.FileUtils.readFile;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.application.view.SpecRenderer;
import org.ericminio.btrs.application.view.pages.ShowPage;
import org.ericminio.btrs.domain.SpecRepository;
import org.ericminio.btrs.store.PlainTextSpec;

public class CreationController implements UserRequest {

	private SpecRenderer renderer;
	
	public CreationController() {
		setRenderer( new ShowPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/create", request ) 
			&& containsGetParameter( "spec", request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
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
