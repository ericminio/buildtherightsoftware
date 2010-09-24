package org.ericmignot.route;

import static org.ericmignot.util.FileUtils.readFile;
import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.getQueryStringValueOf;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.page.LegacyPageTemplate;
import org.ericmignot.page.LegacySavePage;

public class LegacyCreateRoute implements LegacyPageBuilder {
	
	private static final String URI_PREFIX = "/specs/create";
	private static final String QUERY_STRING_PARAMETER = "specXName";

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( URI_PREFIX, request ) 
			&& containsGetParameter( QUERY_STRING_PARAMETER, request );
	}

	public LegacyPageTemplate buildsPage(HttpServletRequest request) {
		return new LegacySavePage( 
				getQueryStringValueOf( QUERY_STRING_PARAMETER, request ),
				readFile( "target/html/newSpecTemplate.html" ),
				"" );
	}
	
}
