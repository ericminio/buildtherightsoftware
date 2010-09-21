package org.ericmignot.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsPostParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;
import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.PageTemplate;
import org.ericmignot.page.SavePage;

public class SaveRoute implements PageBuilder {
	
	private static final String URI_PREFIX = "/specs/save/";
	private static final String SPEC_CONTENT = "specX";
	private static final String SPEC_LABEL = "label";

	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( URI_PREFIX, request ) 
			&& containsPostParameter( SPEC_CONTENT, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new SavePage( uriWithoutThePrefix( URI_PREFIX, request ), 
				request.getParameter( SPEC_CONTENT ), 
				request.getParameter( SPEC_LABEL ));
	}
	
}
