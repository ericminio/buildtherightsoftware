package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.Controller;
import org.ericmignot.adapters.Renderer;
import org.ericmignot.adapters.SpecRepository;
import org.ericmignot.page.NewSpecFormPage;

public class NewController implements Controller {

	private Renderer renderer;
	
	public NewController() {
		setRenderer( new NewSpecFormPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/new", request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) {
		renderer.render( out );
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

}
