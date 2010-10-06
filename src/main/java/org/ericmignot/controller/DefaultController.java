package org.ericmignot.controller;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.store.SpecRepository;

public class DefaultController extends ShowController {

	public void handle(HttpServletRequest request, SpecRepository repository,
			Writer out) {
		view.setSpec( repository.getSpecByTitle( "sample" ));
		view.render( out );
	}

}
