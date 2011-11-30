package org.ericminio.btrs.application.route.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.getQueryStringValueOf;
import static org.junit.Assert.assertTrue;

public class HttpRequestInformationExtractorTest {

	@Test public void
	canAssessWhereQueryStringContainsAGivenParameter() {
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( null )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x=" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x=2" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "y=" )) );
		
		assertTrue( containsGetParameter( "y", aRequestWithTheQueryString( "y=2" )) );
	}

	private HttpServletRequest aRequestWithTheQueryString(String queryString) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getQueryString()).thenReturn( queryString );
		return request;
	}
	
	@Test public void
	returnsEmptyStringWhenParameterIsNotFound() {
		assertEquals( "", getQueryStringValueOf( "param", aRequestWithoutQueryString() ) );
	}
	
	@Test public void
	returnsEmptyStringWhenRequestIsNull() {
		assertEquals( "", getQueryStringValueOf( "param", null ) );
	}
	
	private HttpServletRequest aRequestWithoutQueryString() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getQueryString()).thenReturn( null );
		return request;
	}
}
