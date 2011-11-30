package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.getQueryStringValueOf;
import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.uriIs;
import static org.ericminio.btrs.domain.matchers.CoreMatchers.withLabel;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.application.view.ListRenderer;
import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.application.view.pages.SpecListPage;
import org.ericminio.btrs.domain.SpecRepository;

public class SpecList implements UserRequest {

	private ListRenderer renderer;
	
	public SpecList() {
		setRenderer( new SpecListPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/list", request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		renderer.setSpecs( repository.getSpecs( withLabel(getQueryStringValueOf( "label", request ) ) ) );
		renderer.render( out );
	}

	public void setRenderer(Renderer view) {
		this.renderer = (ListRenderer) view;
	}

}
