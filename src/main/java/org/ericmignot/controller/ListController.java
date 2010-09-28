package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.Controller;
import org.ericmignot.adapters.ListRenderer;
import org.ericmignot.adapters.Renderer;
import org.ericmignot.adapters.SpecRepository;
import org.ericmignot.page.ListPage;

public class ListController implements Controller {

	private ListRenderer renderer;
	
	public ListController() {
		setRenderer( new ListPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/list", request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository,
			Writer out) {
		renderer.setRepository( repository );
		renderer.render( out );
	}

	public void setRenderer(Renderer view) {
		this.renderer = (ListRenderer) view;
	}

}
