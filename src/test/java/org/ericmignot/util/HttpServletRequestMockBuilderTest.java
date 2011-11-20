package org.ericmignot.util;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class HttpServletRequestMockBuilderTest {

	@Test public void
	withALabelParamInTheQueryString() {
		HttpServletRequest request = aMockRequest().withThisLabelParam( "toto" ).build();
		assertThat( request.getQueryString(), is( "label=toto" ) );
	}
	
	@Test public void
	withALabelParamAsAPostParameter() {
		HttpServletRequest request = aMockRequest().withThisLabel( "toto" ).build();
		assertThat( request.getParameter( "label" ), is( "toto" ) );
	}
}
