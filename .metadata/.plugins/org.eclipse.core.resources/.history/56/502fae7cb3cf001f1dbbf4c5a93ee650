package org.ericmignot.util;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.junit.Assert.assertTrue;

public class HttpRequestInformationExtractorTest {

	@Test public void
	canAssessWhereQueryStringContainsAGivenParameter() {
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( null )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x=" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x=2" )) );
		
		assertTrue( containsGetParameter( "y", aRequestWithTheQueryString( "y=2" )) );
	}

	private HttpServletRequest aRequestWithTheQueryString(String queryString) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getQueryString()).thenReturn( queryString );
		return request;
	}
}
