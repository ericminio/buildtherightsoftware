package org.ericminio.btrs.application.route.controllers;


import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.application.view.ListRenderer;
import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.application.view.pages.LabelListPage;
import org.ericminio.btrs.domain.SpecRepository;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.uriIs;
import static org.ericminio.btrs.domain.matchers.CoreMatchers.all;

public class LabelListController implements UserRequest {

	private ListRenderer renderer;
	
	public LabelListController() {
		setRenderer( new LabelListPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/labels", request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		renderer.setSpecs( repository.getSpecs( all() ) );
		renderer.render( out );
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = (ListRenderer) renderer;
	}

}
