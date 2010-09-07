package org.ericmignot.page.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsPostParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.removePrefixFromUri;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriStartsWith;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.PageTemplate;
import org.ericmignot.page.SavePage;

public class SaveRoute implements PageBuilder {
	
	private static final String URI_PREFIX = "/specs/save/";
	private static final String SPEC_CONTENT = "specX";

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriStartsWith( URI_PREFIX, request ) 
			&& containsPostParameter( SPEC_CONTENT, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new SavePage( removePrefixFromUri( URI_PREFIX, request ), request.getParameter( SPEC_CONTENT ) );
	}
	
}
