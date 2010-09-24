package org.ericmignot.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.LegacyNewPage;
import org.ericmignot.page.LegacyPageTemplate;

public class LegacyNewRoute implements LegacyPageBuilder {

	private static final String URI_PREFIX = "/specs/new";
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( URI_PREFIX, request );
	}

	public LegacyPageTemplate buildsPage(HttpServletRequest request) {
		return new LegacyNewPage();
	}
	
}
