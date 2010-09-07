package org.ericmignot.page.activation;

import static org.ericmignot.util.HttpRequestInformationExtractor.uriIs;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.jetty.PageBuilder;
import org.ericmignot.page.NewPage;
import org.ericmignot.page.PageTemplate;

public class NewPageActivator implements PageBuilder {

	private static final String URI_PREFIX = "/specs/new";
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return uriIs( URI_PREFIX, request );
	}

	public PageTemplate buildsPage(HttpServletRequest request) {
		return new NewPage();
	}
	
}
