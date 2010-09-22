package org.ericmignot.action;

import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.core.Spec;
import org.ericmignot.jetty.Action;
import org.ericmignot.jetty.View;
import org.ericmignot.page.ModifyPage;
import org.ericmignot.store.Repository;

public class Modify implements Action {
	
	private static final String URI_PREFIX = "/specs/modify/";
	private View view;
	
	public Modify() {
		setView( new ModifyPage() );
	}
	
	protected void setView(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}

	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( URI_PREFIX, request );
	}

	public void work(HttpServletRequest request, Repository repository, Writer out) {
		Spec spec = repository.getSpecByTitle( uriWithoutThePrefix( URI_PREFIX,  request) );
		view.render( spec, out );
	}
	
}
