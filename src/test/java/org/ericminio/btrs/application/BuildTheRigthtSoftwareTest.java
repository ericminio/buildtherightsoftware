package org.ericminio.btrs.application;
import static org.ericminio.btrs.store.SpecBuilder.aSpec;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuildTheRigthtSoftwareTest extends SystemTest {

	@Before public void
	setAndCleanWorkingDirectory() {
		setWorkingDirectory( "target/test-classes/test-system" );
		repository.deleteAllSpecs();
	}
	
	@After public void
	deleteTestData() {
		repository.deleteAllSpecs();
	}
	
	@Test public void
	homPageDisplaysAWelcomeMessage() {
		accessHomePage();
		pageShouldContainTheText( "Welcome :)", "home page displays welcome message" );
	}
	
	@Test public void
	canShowASpec() throws IOException {
		having( aSpec().withTitle( "tetris" ).withContent( "tetris game" ) );
		showSpec( "tetris" );
		pageShouldContainTheText( "tetris game", "can show a known spec" );
	}

	@Test public void
	specModificationUri() throws IOException {
		having( aSpec().withTitle( "tetris" ).withContent( "tetris game" ) );
		accessSpecForModification("tetris");
		updateSpecContent( "toto" );
		saveSpec();
		uriShouldBe ( "/specs/save/tetris", "save uri" );
	}

	@Test public void
	canModifyASpec() throws IOException {
		having( aSpec().withTitle( "tetris" ).withContent( "tetris game" ) );
		accessSpecForModification("tetris");
		updateSpecContent( "toto" );
		saveSpec();
		pageShouldContainModifyLink();
		pageShouldContainTheText( "toto", "modification saved" );
	}
	
	@Test public void
	specCreationScenarioUris() {
		accessHomePage();
		activateNewSpecCreation();
		uriShouldBe( "/specs/new", "uri after click on new link" );
		
		createNewSpec( "anewspec" );
		uriShouldBe( "/specs/create", "creation uri" );
		queryStringShouldBe( "spec=anewspec", "creation query string" );
	}

	@Test public void
	canCreateANewSpec() {
		accessHomePage();
		activateNewSpecCreation();
		createNewSpec( "anewspec" );
		pageShouldContainModifyLink();
		pageShouldContainTheText( "put your service name here", "display new spec template" );
		pageShouldContainTryThisCodeLink();
	}
	
	@Test public void
	canAccessSpecList() throws IOException {
		having( aSpec().withTitle( "tetris" ) );
		having( aSpec().withTitle( "lotery" ) );
		accessHomePage();
		accessSpecList();
		uriShouldBe( "/specs/list", "uri after click on spec list link" );
		pageShouldContainTheText( "<li><a class=\"list\" href=\"/specs/show/tetris\" >tetris</a></li>", "tetris spec" );
		pageShouldContainTheText( "<li><a class=\"list\" href=\"/specs/show/lotery\" >lotery</a></li>", "lotery spec" );
	}
	
	@Test public void
	canSetALabelToASpec() throws IOException {
		having( aSpec().withTitle( "tetris" ) );
		accessSpecForModification( "tetris" );
		pageShouldContainTheText( "Label:", "label field label" );
		modifySpecLabel( "game" );
		saveSpec();
		pageShouldContainTheText( "<span class=\"label\">Label: game</span>", "label mention" );
		accessSpecForModification( "tetris" );
		labelFieldShouldContainTheValue( "game" );
	}
	
	@Test public void
	canAccessLabelList() throws IOException {
		having( aSpec().withLabel( "game" ).withTitle( "tetris" ) );
		having( aSpec().withLabel( "pyxis" ).withTitle( "lotery" ) );
		having( aSpec().withLabel( "pyxis" ).withTitle( "rotating dinner" ) );
		accessHomePage();
		accessLabelList();
		pageShouldContainTheText( "game (1)", "label filter game" );
		pageShouldContainTheText( "pyxis (2)", "label filter pyxis" );
	}
	
	@Test public void
	canAccessSpecListFilteresByALabel() throws IOException {
		having( aSpec().withLabel( "game" ).withTitle( "tetris" ) );
		having( aSpec().withLabel( "pyxis" ).withTitle( "lotery" ) );
		having( aSpec().withLabel( "pyxis" ).withTitle( "rotating dinner" ) );
		accessHomePage();
		accessLabelList();
		navigateByLabel( "pyxis" );
		urlShouldContain( "/specs/list?label=pyxis", "uri after click on a label" );
		pageShouldContainTheText( "lotery", "spec list filtered by label" );
		pageShouldContainTheText( "rotating dinner", "spec list filtered by label" );
		pageShouldNotContainTheText( "tetris", "spec list filtered by label" );
	}
	

}
