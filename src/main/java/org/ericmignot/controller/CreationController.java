package org.ericmignot.controller;

import static org.ericmignot.util.FileUtils.readFile;
import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.getQueryStringValueOf;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.Action;
import org.ericmignot.adapters.Controller;
import org.ericmignot.adapters.Renderer;
import org.ericmignot.adapters.SpecRenderer;
import org.ericmignot.adapters.SpecRepository;
import org.ericmignot.domain.HtmlParagraphSpec;
import org.ericmignot.page.ShowPage;

public class CreationController implements Controller {

	private SpecRenderer renderer;
	
	public CreationController() {
		setRenderer( new ShowPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/create", request ) 
			&& containsGetParameter( "spec", request );
	}

	public void setWorkingDirectory(String directory) {
		
	}

	public void setAction(Action action) {
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) {
		HtmlParagraphSpec spec = new HtmlParagraphSpec( getQueryStringValueOf( "spec", request ) );
		spec.setContent( readFile( "target/html/newSpecContent.html" ) );
		
		repository.saveSpec( spec );
		renderer.setSpec( spec );
		renderer.render( out );
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = (SpecRenderer) renderer;
	}

}
