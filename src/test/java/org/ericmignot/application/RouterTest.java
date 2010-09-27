package org.ericmignot.application;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.junit.Assert.assertTrue;

import org.ericmignot.application.Router;
import org.ericmignot.controller.ExecutionController;
import org.ericmignot.controller.ListController;
import org.ericmignot.controller.ModifyController;
import org.ericmignot.controller.ShowController;
import org.junit.Before;
import org.junit.Test;


public class RouterTest {

	private Router actionRouter;
	
	@Before public void
	init() {
		actionRouter = new Router();
	}
	
	@Test public void
	returnsShowWhenShowIsCalled() {
		assertTrue( "activate Show", actionRouter.chooseController( 
				aMockRequest().withThisUri( "/specs/show/sample").build() ) 
				instanceof ShowController );
	}
	
	@Test public void
	returnsModifyWhenModifyIsCalled() {
		assertTrue( "activate Modify", actionRouter.chooseController( 
				aMockRequest().withThisUri( "/specs/modify/sample").build() ) 
				instanceof ModifyController );
	}
	
	@Test public void
	returnsExecuteWhenExecuteIsCalled() {
		assertTrue( "activate Execution", actionRouter.chooseController( 
				aMockRequest().withThisUri( "/specs/execute/sample")
							  .withThisGitRepoParam( "git://github.com/testaddict/mastermind.git" ).build() ) 
				instanceof ExecutionController );
	}
	
	@Test public void
	returnsListWhenSpecListIsCalled() {
		assertTrue( "activate Lis", actionRouter.chooseController( 
				aMockRequest().withThisUri( "/specs/list").build() ) 
				instanceof ListController );
	}
	
}
