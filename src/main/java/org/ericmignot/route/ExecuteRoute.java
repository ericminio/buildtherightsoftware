package org.ericmignot.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsPostParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;
import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.PageTemplate;
import org.ericmignot.page.ExecutePage;

public class ExecuteRoute implements PageBuilder {
	
	private static final String URI_PREFIX = "/specs/execute/";
	private static final String REPO_URL = "repo";

	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( URI_PREFIX, request ) 
			&& containsPostParameter( REPO_URL, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new ExecutePage( uriWithoutThePrefix( URI_PREFIX, request ), request.getParameter( REPO_URL ) );
	}

}
