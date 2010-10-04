package org.ericmignot.application;

import static org.ericmignot.util.HttpServletRequestMockBuilder.aMockRequest;
import static org.junit.Assert.assertTrue;

import org.ericmignot.application.Router;
import org.ericmignot.controller.CreationController;
import org.ericmignot.controller.ExecutionController;
import org.ericmignot.controller.ListController;
import org.ericmignot.controller.ModifyController;
import org.ericmignot.controller.NewController;
import org.ericmignot.controller.SaveController;
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
		assertTrue( "activate List", actionRouter.chooseController( 
				aMockRequest().withThisUri( "/specs/list").build() ) 
				instanceof ListController );
	}
	
	@Test public void
	returnsNewWhenNewIsCalled() {
		assertTrue( "activate New", actionRouter.chooseController( 
				aMockRequest().withThisUri( "/specs/new").build() ) 
				instanceof NewController );
	}
	
	@Test public void
	returnsCreationWhenCreationIsCalled() {
		assertTrue( "activate Creation", actionRouter.chooseController( 
				aMockRequest().withThisUri( "/specs/create")
							  .withThisSpecTitleParam( "the-game-of-the-future" ).build() ) 
				instanceof CreationController );
	}
	
	@Test public void
	returnsSaveWhenSaveIsCalled() {
		assertTrue( "activate Save", actionRouter.chooseController( 
				aMockRequest().withThisUri( "/specs/save/toto" )
							  .withThisLabel( "game" )
							  .withThisContent( "tetris" ).build() ) 
				instanceof SaveController );
	}
	
}
