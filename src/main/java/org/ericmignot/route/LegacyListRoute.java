package org.ericmignot.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.LegacyListPage;
import org.ericmignot.page.LegacyPageTemplate;

public class LegacyListRoute implements LegacyPageBuilder {
	
	private static final String URI_PREFIX = "/specs/list";

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( URI_PREFIX, request );
	}

	public LegacyPageTemplate buildsPage(HttpServletRequest request) {
		return new LegacyListPage( );
	}

}
