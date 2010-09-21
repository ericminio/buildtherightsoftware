package org.ericmignot.jetty;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.action.Show;
import org.ericmignot.jetty.ActionRouter;
import org.junit.Before;
import org.junit.Test;

public class ActionRouterTest {

	private ActionRouter actionRouter;
	
	@Before public void
	init() {
		actionRouter = new ActionRouter();
	}
	
	
	@Test public void
	returnsShowWhenShowIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/show/sample");
		
		assertTrue( "activate Show", actionRouter.chooseAction( request ) instanceof Show );
	}
	
}
