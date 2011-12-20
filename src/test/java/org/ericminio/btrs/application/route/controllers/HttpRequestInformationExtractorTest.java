package org.ericminio.btrs.application.route.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.*;
import static org.junit.Assert.assertTrue;

public class HttpRequestInformationExtractorTest {

	@Test public void
	canAssessWetherARequestUriStartsWithAGivenPrefix() {
		assertFalse( trueIfUriStartsWith( "this", aRequestWithTheUri( null ) ) );
		assertFalse( trueIfUriStartsWith( "this", aRequestWithTheUri( "that" ) ) );
		assertFalse( trueIfUriStartsWith( "that", aRequestWithTheUri( "that" ) ) );
		assertTrue( trueIfUriStartsWith( "that", aRequestWithTheUri( "that-and-more" ) ) );
	}
	
	private HttpServletRequest aRequestWithTheUri(String uri) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		return request;
	}
	
	@Test public void
	canAssessWhenQueryStringContainsAGivenNotEmptyParameter() {
		assertFalse( containsNotEmptyGetParameter( "y", aRequestWithTheQueryString( null )) );
		assertFalse( containsNotEmptyGetParameter( "y", aRequestWithTheQueryString( "" )) );
		assertFalse( containsNotEmptyGetParameter( "y", aRequestWithTheQueryString( "x" )) );
		assertFalse( containsNotEmptyGetParameter( "y", aRequestWithTheQueryString( "x=" )) );
		assertFalse( containsNotEmptyGetParameter( "y", aRequestWithTheQueryString( "x=2" )) );
		assertFalse( containsNotEmptyGetParameter( "y", aRequestWithTheQueryString( "y=" )) );
		
		assertTrue( containsNotEmptyGetParameter( "y", aRequestWithTheQueryString( "y=2" )) );
	}
	
	@Test public void
	canAssessWhenQueryStringContainsAGivenParameter() {
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( null )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x=" )) );
		assertFalse( containsGetParameter( "y", aRequestWithTheQueryString( "x=2" )) );
		
		assertTrue( containsGetParameter( "y", aRequestWithTheQueryString( "y=" )) );
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
