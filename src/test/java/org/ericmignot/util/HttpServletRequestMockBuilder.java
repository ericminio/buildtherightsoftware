package org.ericmignot.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestMockBuilder {

	private String uri;
	
	public static HttpServletRequestMockBuilder aMockRequest() {
		return new HttpServletRequestMockBuilder();
	}
	
	public HttpServletRequestMockBuilder withThisUri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public HttpServletRequest build() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		return request;
	}
}
