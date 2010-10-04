package org.ericmignot.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestMockBuilder {

	private String uri;
	private String repo;
	private String queryString;
	private String content;
	private String label;
	
	public static HttpServletRequestMockBuilder aMockRequest() {
		HttpServletRequestMockBuilder builder = new HttpServletRequestMockBuilder();
		builder.uri = null;
		builder.queryString = null;
		return builder;
	}
	
	public HttpServletRequestMockBuilder withThisUri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public HttpServletRequestMockBuilder withThisLabel(String label) {
		this.label = label;
		return this;
	}
	
	public HttpServletRequestMockBuilder withThisContent(String content) {
		this.content = content;
		return this;
	}
	
	public HttpServletRequestMockBuilder withThisGitRepoParam(String repo) {
		this.repo = repo;
		this.queryString = "repo=" + repo;
		return this;
	} 
	
	public HttpServletRequestMockBuilder withThisSpecTitleParam(String title) {
		this.queryString = "spec=" + title;
		return this;
	}

	public HttpServletRequest build() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn( uri );
		when(request.getQueryString()).thenReturn( queryString );
		when(request.getParameter("repo")).thenReturn( repo );
		when(request.getParameter("label")).thenReturn( label );
		when(request.getParameter("content")).thenReturn( content );
		return request;
	}

}
