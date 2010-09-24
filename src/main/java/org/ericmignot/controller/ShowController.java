package org.ericmignot.controller;

import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.Action;
import org.ericmignot.adapters.Controller;
import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.SpecRepository;
import org.ericmignot.adapters.View;
import org.ericmignot.page.ShowPage;

public class ShowController implements Controller {

	private static final String URI_PREFIX = "/specs/show/";
	private View view;

	public ShowController() {
		setView( new ShowPage() );
	}
	
	public void setView(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( URI_PREFIX, request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) {
		Spec spec = repository.getSpecByTitle( uriWithoutThePrefix( URI_PREFIX,  request) );
		view.setSpec( spec );
		view.render( out );
	}

	public void setAction(Action action) {}

	public void setWorkingDirectory(String directory) {}
	
	

}
