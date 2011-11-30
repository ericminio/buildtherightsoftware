package org.ericminio.btrs.application.route;

import static org.ericminio.btrs.application.route.HttpServletRequestStubBuilder.aStubRequest;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class HttpServletRequestStubBuilderTest {

	@Test public void
	canConfigureUri() {
		HttpServletRequest request = aStubRequest().withThisUri( "this-uri" ).build();
		assertThat( request.getRequestURI(), equalTo( "this-uri" ));
	}
	
	@Test public void
	canConfigureAGetParameter() {
		HttpServletRequest request = aStubRequest().withThisGetParameter( "my-param", "a-value" ).build();
		assertThat( request.getQueryString(), containsString( "my-param=a-value" ) );
	}
	
	@Test public void
	canConfigureTwoGetParameters() {
		HttpServletRequest request = aStubRequest().withThisGetParameter( "my-param", "my-value" )
												   .withThisGetParameter( "your-param", "your-value" ).build();
		assertThat( request.getQueryString(), containsString( "my-param=my-value&your-param=your-value" ) );
	}
	
	@Test public void
	canConfigureAPostParameter() {
		HttpServletRequest request = aStubRequest().withThisPostParameter( "my-param", "a-value" ).build();
		assertThat( request.getParameter( "my-param" ), equalTo( "a-value" ) );
	}
	
	@Test public void
	canConfigureTwoPostParameters() {
		HttpServletRequest request = aStubRequest().withThisPostParameter( "my-param", "my-value" )
												   .withThisPostParameter( "your-param", "your-value" ).build();
		assertThat( request.getParameter( "my-param" ), equalTo( "my-value" ) );
		assertThat( request.getParameter( "your-param" ), equalTo( "your-value" ) );
	}
	
}
