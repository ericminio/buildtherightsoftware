package org.ericmignot.route;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.getQueryStringValueOf;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.PageTemplate;
import org.ericmignot.page.SavePage;
import org.ericmignot.util.FileReader;

public class CreateRoute implements PageBuilder {
	
	private static final String URI_PREFIX = "/specs/create";
	private static final String QUERY_STRING_PARAMETER = "specXName";

	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( URI_PREFIX, request ) 
			&& containsGetParameter( QUERY_STRING_PARAMETER, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new SavePage( 
				getQueryStringValueOf( QUERY_STRING_PARAMETER, request ),
				new FileReader().readFile( "target/html/newSpecTemplate.html" ),
				"" );
	}
	
}
