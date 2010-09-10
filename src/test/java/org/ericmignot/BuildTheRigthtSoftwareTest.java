package org.ericmignot;

import org.ericmignot.util.SystemTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BuildTheRigthtSoftwareTest extends SystemTest {

	@Test public void
	canShowASpec() {
		showSpec( "calculator-sample" );
		pageShouldContainTheText( "rule for calculator", "<td>calculator</td>" );
	}
	
	@Test public void
	canExecuteASpecWithARemoteCode() throws InterruptedException {
        showSpec( "execution-sample" );
        findTryCodeLinkAndClickIt();
        uriShouldBe( "execution uri", "/specs/execute/execution-sample" );
        queryStringShouldBe( "execution query string", "repo=git%3A%2F%2Fgithub.com%2Ftestaddict%2Fmastermind.git" );
        pageShouldContainTheText( "spec passes", "background-color: #AAFFAA;" );
	}

	@Test public void
	canModifyASpec() {
		accessSpecForModification("save-sample");
		updateSpecContent( "toto" );
		saveSpec();
		uriShouldBe ( "save uri", "/specs/save/save-sample" );
        pageShouldContainModifyLink();
        pageShouldContainTheText( "modification saved", "toto" );
	}
	
	@Test public void
	canCreateANewSpec() {
		accessHomePage();
		findNewLinkAndClickIt();
		uriShouldBe( "uri after click on new link", "/specs/new" );
		findFieldAndEnterTheValue( "specXName", "anewspec" );
		createSpec();
		
		uriShouldBe( "create uri", "/specs/create" );
		queryStringShouldBe( "creation query string", "specXName=anewspec" );
		
		pageShouldContainModifyLink();
		pageShouldContainTheText( "new spec template", "put your service name here" );
		pageShouldContainTryThisCodeLink();
	}
	
	@Test public void
	canAccessSpecList() {
		accessHomePage();
		findSpecListLinkAndClickIt();
		uriShouldBe( "uri after click on spec list link", "/specs/list" );
		pageShouldContainTheText( "list header", "Spec list:" );
		pageShouldContainTheText( "test-system resource file calculator-sample.html", "<li><a class=\"list\" href=\"/specs/show/calculator-sample\" >calculator-sample</a></li>" );
		pageShouldContainTheText( "test-system resource file sample.html", "<li><a class=\"list\" href=\"/specs/show/sample\" >sample</a></li>" );
	}
	
	@Test public void
	canSetALabelToASpec() {
		accessSpecForModification( "sample" );
		pageShouldContainTheText( "label field label", "Labels:" );
		findFieldAndEnterTheValue( "label", "game" );
		saveSpec();
		pageShouldContainTheText( "label mention", "<span class=\"label\">Labels: game</span>" );
	}
	
	
	
	
}
