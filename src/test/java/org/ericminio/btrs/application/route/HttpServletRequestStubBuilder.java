package org.ericminio.btrs.application.route;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestStubBuilder {

	private String uri;
	private String queryString;
	private Map<String, String> postParameters;
	
	public HttpServletRequestStubBuilder() {
		postParameters = new HashMap<String, String>();
	}
	
	public static HttpServletRequestStubBuilder aStubRequest() {
		HttpServletRequestStubBuilder builder = new HttpServletRequestStubBuilder();
		builder.uri = null;
		builder.queryString = null;
		return builder;
	}
	
	public HttpServletRequestStubBuilder withThisUri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public HttpServletRequestStubBuilder withThisGetParameter(String param, String value) {
		if (queryString == null) {
			this.queryString = param + "=" + value;
		}
		else {
			this.queryString += "&" + param + "=" + value;
		}
		return this;
	}
	
	public HttpServletRequestStubBuilder withThisPostParameter(String param, String value) {
		this.postParameters.put( param, value );
		return this;
	}

	public HttpServletRequest build() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		when(request.getQueryString()).thenReturn( queryString );
		for (String key : this.postParameters.keySet()) {
			when(request.getParameter( key ) ).thenReturn( this.postParameters.get( key ) );
		}
		return request;
	}


}
