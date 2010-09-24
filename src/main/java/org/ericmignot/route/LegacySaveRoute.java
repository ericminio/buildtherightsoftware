package org.ericmignot.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsPostParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;
import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.LegacyPageTemplate;
import org.ericmignot.page.LegacySavePage;

public class LegacySaveRoute implements LegacyPageBuilder {
	
	private static final String URI_PREFIX = "/specs/save/";
	private static final String SPEC_CONTENT = "specX";
	private static final String SPEC_LABEL = "label";

	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( URI_PREFIX, request ) 
			&& containsPostParameter( SPEC_CONTENT, request );
	}

	public LegacyPageTemplate buildsPage(HttpServletRequest request) {
		return new LegacySavePage( uriWithoutThePrefix( URI_PREFIX, request ), 
				request.getParameter( SPEC_CONTENT ), 
				request.getParameter( SPEC_LABEL ));
	}
	
}
