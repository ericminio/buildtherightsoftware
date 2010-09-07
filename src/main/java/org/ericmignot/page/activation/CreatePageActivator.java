package org.ericmignot.page.activation;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.getQueryStringValueOf;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.CreatePage;
import org.ericmignot.page.PageTemplate;

public class CreatePageActivator implements PageBuilder {
	
	private static final String URI_PREFIX = "/specs/create";
	private static final String QUERY_STRING_PARAMETER = "specXName";

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( URI_PREFIX, request ) 
			&& containsGetParameter( QUERY_STRING_PARAMETER, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new CreatePage( getQueryStringValueOf( QUERY_STRING_PARAMETER, request ) );
	}
	
}
