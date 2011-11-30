package org.ericminio.btrs.application.route;

import static org.ericminio.btrs.application.route.HttpServletRequestStubBuilder.aStubRequest;
import static org.junit.Assert.assertTrue;

import org.ericminio.btrs.application.route.Router;
import org.ericminio.btrs.application.route.controllers.CreationController;
import org.ericminio.btrs.application.route.controllers.DefaultController;
import org.ericminio.btrs.application.route.controllers.ExecutionController;
import org.ericminio.btrs.application.route.controllers.LabelListController;
import org.ericminio.btrs.application.route.controllers.ModifyController;
import org.ericminio.btrs.application.route.controllers.NewController;
import org.ericminio.btrs.application.route.controllers.SaveController;
import org.ericminio.btrs.application.route.controllers.ShowController;
import org.ericminio.btrs.application.route.controllers.SpecList;
import org.junit.Before;
import org.junit.Test;


public class RouterTest {

	private Router actionRouter;
	
	@Before public void
	init() {
		actionRouter = new Router();
	}
	
	@Test public void
	returnsDefaultControllerWhenHomeIsCalled() {
		assertTrue( "activate Show sample", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/").build() ) 
				instanceof DefaultController );
	}
	
	@Test public void
	returnsShowWhenShowIsCalled() {
		assertTrue( "activate Show", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/specs/show/sample").build() ) 
				instanceof ShowController );
	}
	
	@Test public void
	returnsModifyWhenModifyIsCalled() {
		assertTrue( "activate Modify", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/specs/modify/sample").build() ) 
				instanceof ModifyController );
	}
	
	@Test public void
	returnsExecuteWhenExecuteIsCalled() {
		assertTrue( "activate Execution", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/specs/execute/sample")
							  .withThisGetParameter( "repo", "git://github.com/testaddict/mastermind.git" ).build() ) 
				instanceof ExecutionController );
	}
	
	@Test public void
	returnsListWhenSpecListIsCalled() {
		assertTrue( "activate List", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/specs/list").build() ) 
				instanceof SpecList );
	}
	
	@Test public void
	returnsNewWhenNewIsCalled() {
		assertTrue( "activate New", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/specs/new").build() ) 
				instanceof NewController );
	}
	
	@Test public void
	returnsCreationWhenCreationIsCalled() {
		assertTrue( "activate Creation", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/specs/create")
							  .withThisGetParameter( "spec", "the-game-of-the-future" ).build() ) 
				instanceof CreationController );
	}
	
	@Test public void
	returnsSaveWhenSaveIsCalled() {
		assertTrue( "activate Save", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/specs/save/toto" )
							  .withThisPostParameter( "label", "game" )
							  .withThisPostParameter( "content", "tetris" ).build() ) 
				instanceof SaveController );
	}
	
	@Test public void
	returnsLabelControllerWhenLabelListIsCalled() {
		assertTrue( "activate Label list", actionRouter.chooseController( 
				aStubRequest().withThisUri( "/specs/labels" ).build() ) 
				instanceof LabelListController );
	}
	
}
