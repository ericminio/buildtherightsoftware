package org.ericmignot.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.removePrefixFromUri;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriStartsWith;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.ModifyPage;
import org.ericmignot.page.PageTemplate;

public class ModifyRoute implements PageBuilder {
	
	private static final String URI_PREFIX = "/specs/modify/";

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriStartsWith( URI_PREFIX, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new ModifyPage( removePrefixFromUri( URI_PREFIX, request ) );
	}
	
}
