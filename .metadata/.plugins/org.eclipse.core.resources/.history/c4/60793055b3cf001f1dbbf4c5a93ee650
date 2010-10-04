package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.Renderer;
import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.page.NewSpecFormPage;

public class NewController implements UserRequest {

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
