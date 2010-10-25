package org.ericmignot.application;

import static org.ericmignot.util.SpecBuilder.aSpec;

import org.ericmignot.util.SpecBuilder;
import org.ericmignot.util.SystemTest;
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
	homPageDisplaysASampleSpec() {
		accessHomePage();
		
		pageShouldContainModifyLink();
		pageShouldContainTheText( "home page display a spec sample", "Rule for" );
		pageShouldContainTryThisCodeLink();
	}
	
	@Test public void
	canShowASpec() {
		having( aSpec().withTitle( "tetris" ).withContent( "tetris game" ) );
		showSpec( "tetris" );
		pageShouldContainTheText( "can show a known spec", "tetris game" );
	}

	@Test public void
	specModificationUri() {
		having( aSpec().withTitle( "tetris" ).withContent( "tetris game" ) );
		accessSpecForModification("tetris");
		updateSpecContent( "toto" );
		saveSpec();
		uriShouldBe ( "save uri", "/specs/save/tetris" );
	}

	@Test public void
	canModifyASpec() {
		having( aSpec().withTitle( "tetris" ).withContent( "tetris game" ) );
		accessSpecForModification("tetris");
		updateSpecContent( "toto" );
		saveSpec();
        pageShouldContainModifyLink();
        pageShouldContainTheText( "modification saved", "toto" );
	}
	
	@Test public void
	specCreationScenarioUris() {
		accessHomePage();
		activateNewSpecCreation();
		uriShouldBe( "uri after click on new link", "/specs/new" );
		
		createNewSpec( "anewspec" );
		uriShouldBe( "create uri", "/specs/create" );
		queryStringShouldBe( "creation query string", "spec=anewspec" );
	}

	@Test public void
	canCreateANewSpec() {
		accessHomePage();
		activateNewSpecCreation();
		createNewSpec( "anewspec" );
		pageShouldContainModifyLink();
		pageShouldContainTheText( "new spec template", "put your service name here" );
		pageShouldContainTryThisCodeLink();
	}
	
	@Test public void
	canAccessSpecList() {
		having( aSpec().withTitle( "tetris" ) );
		having( aSpec().withTitle( "lotery" ) );
		accessHomePage();
		accessSpecList();
		uriShouldBe( "uri after click on spec list link", "/specs/list" );
		pageShouldContainTheText( "tetris spec", "<li><a class=\"list\" href=\"/specs/show/tetris\" >tetris</a></li>" );
		pageShouldContainTheText( "lotery spec", "<li><a class=\"list\" href=\"/specs/show/lotery\" >lotery</a></li>" );
	}
	
	@Test public void
	canSetALabelToASpec() {
		having( aSpec().withTitle( "tetris" ) );
		accessSpecForModification( "tetris" );
		pageShouldContainTheText( "label field label", "Labels:" );
		modifySpecLabel( "game" );
		saveSpec();
		pageShouldContainTheText( "label mention", "<span class=\"label\">Labels: game</span>" );
		accessSpecForModification( "tetris" );
		labelFieldShouldContainTheValue( "game" );
	}
	
	@Test public void
	canAccessLabelList() {
		having( aSpec().withLabel( "game" ).withTitle( "tetris" ) );
		having( aSpec().withLabel( "pyxis" ).withTitle( "lotery" ) );
		having( aSpec().withLabel( "pyxis" ).withTitle( "rotating dinner" ) );
		accessHomePage();
		accessLabelList();
		pageShouldContainTheText( "label filter game", "<li><a class=\"label-filter\" href=\"/specs/list?label=game\" >game (1)</a></li>" );
		pageShouldContainTheText( "label filter pyxis", "<li><a class=\"label-filter\" href=\"/specs/list?label=pyxis\" >pyxis (2)</a></li>" );
	}
	

}
