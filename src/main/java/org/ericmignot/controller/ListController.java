package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.ListRenderer;
import org.ericmignot.adapters.ui.Renderer;
import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.page.ListPage;

public class ListController implements UserRequest {

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
