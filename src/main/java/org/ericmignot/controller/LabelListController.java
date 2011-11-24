package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.ListRenderer;
import org.ericmignot.adapters.ui.Renderer;
import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.page.LabelListPage;

public class LabelListController implements UserRequest {

	private ListRenderer renderer;
	
	public LabelListController() {
		setRenderer( new LabelListPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( "/specs/labels", request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		renderer.setSpecs( repository.getSpecs() );
		renderer.render( out );
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = (ListRenderer) renderer;
	}

}
