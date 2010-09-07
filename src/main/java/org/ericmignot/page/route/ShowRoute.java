package org.ericmignot.page.route;

import javax.servlet.http.HttpServletRequest;
import static org.ericmignot.util.HttpRequestInformationExtractor.removePrefixFromUri;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriStartsWith;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.PageTemplate;
import org.ericmignot.page.ShowPage;

public class ShowRoute implements PageBuilder {
	
	private static final String URI_PREFIX = "/specs/show/";

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriStartsWith( URI_PREFIX, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new ShowPage( removePrefixFromUri( URI_PREFIX, request ) );
	}

}
