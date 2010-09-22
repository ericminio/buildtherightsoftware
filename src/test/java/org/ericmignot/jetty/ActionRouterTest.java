package org.ericmignot.jetty;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.junit.Assert.assertTrue;

import org.ericmignot.action.Modify;
import org.ericmignot.action.Show;
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
		assertTrue( "activate Show", actionRouter.chooseAction( aMockRequest().withThisUri( "/specs/show/sample").build() ) instanceof Show );
	}
	
	@Test public void
	returnsModifyWhenModifyIsCalled() {
		assertTrue( "activate Modify", actionRouter.chooseAction( aMockRequest().withThisUri( "/specs/modify/sample").build() ) instanceof Modify );
	}
	
}
