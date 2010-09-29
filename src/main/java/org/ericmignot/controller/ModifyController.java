package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.Renderer;
import org.ericmignot.adapters.ui.SpecRenderer;
import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.page.ModifyPage;

public class ModifyController implements UserRequest {
	
	private static final String URI_PREFIX = "/specs/modify/";
	private SpecRenderer view;
	
	public ModifyController() {
		setRenderer( new ModifyPage() );
	}
	
	public void setRenderer(Renderer view) {
		this.view = (SpecRenderer) view;
	}

	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( URI_PREFIX, request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) {
		Spec spec = repository.getSpecByTitle( uriWithoutThePrefix( URI_PREFIX,  request) );
		view.setSpec( spec );
		view.render( out );
	}

}
