package org.ericmignot.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.ListPage;
import org.ericmignot.page.PageTemplate;

public class ListRoute implements PageBuilder {
	
	private static final String URI_PREFIX = "/specs/list";

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( URI_PREFIX, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new ListPage( );
	}

}
