package org.ericmignot;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.ericmignot.jetty.FileHandler;
import org.ericmignot.jetty.Page;
import org.ericmignot.jetty.PageHandler;
import org.ericmignot.jetty.PageRouter;
import org.ericmignot.page.ExecutePage;
import org.ericmignot.page.ListPage;
import org.ericmignot.page.ModifyPage;
import org.ericmignot.page.SavePage;
import org.ericmignot.page.ShowPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class SystemTest {

	private static Server server;
	private static Thread thread;
	private WebDriver driver;
	
	@BeforeClass public static void
	setUp() throws Exception {
		server = new Server(8080);
		
		PageHandler testPageHandler = new PageHandler();
		testPageHandler.setPageRouter( new TestPageChooser() );
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { 
        		new FileHandler(), 
        		testPageHandler });
        server.setHandler(handlers);
 
        thread = new Thread(new Runnable() {
			public void run() {
		        try {
					server.start();
			        server.join();				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        thread.start();
	}
	
	@AfterClass public static void
	tearDown() throws Exception {
		server.stop();
		thread.stop();
	}
	
	@Before public void
	initDriver() {
		driver = new HtmlUnitDriver(true);
	}
	
	@Test public void
	canShowASpec() {
		showSpec( "calculator-sample" );
		pageShouldContain( "rule for calculator", "<td>calculator</td>" );
	}
	
	@Test public void
	canExecuteASpecWithARemoteCode() throws InterruptedException {
        showSpec( "execution-sample" );
        findTryCodeLinkAndClickIt();
        uriShouldBe( "execution uri", "/specs/execute/execution-sample" );
        queryStringShouldBe( "execution query string", "repo=git%3A%2F%2Fgithub.com%2Ftestaddict%2Fmastermind.git" );
        pageShouldContain( "spec passes", "background-color: #AAFFAA;" );
	}

	@Test public void
	canModifyASpec() {
		accessSpecForModification("save-sample");
		updateSpecContent( "toto" );
		saveSpec();
		uriShouldBe ( "save uri", "/specs/save/save-sample" );
        pageShouldContainModifyLink();
        pageShouldContain( "modification saved", "toto" );
	}
	
	@Test public void
	canCreateANewSpec() {
		accessHomePage();
		findNewLinkAndClickIt();
		uriShouldBe( "uri after click on new link", "/specs/new" );
		findSpecNameFieldAndEnterTheValue( "anewspec" );
		createSpec();
		
		uriShouldBe( "create uri", "/specs/create" );
		queryStringShouldBe( "creation query string", "specXName=anewspec" );
		
		pageShouldContainModifyLink();
		pageShouldContain( "new spec template", "put your service name here" );
		pageShouldContainTryThisCodeLink();
	}
	
	@Test public void
	canAccessSpecList() {
		accessHomePage();
		findSpecListLinkAndClickIt();
		uriShouldBe( "uri after click on spec list link", "/specs/list" );
		pageShouldContain( "list header", "Spec list:" );
		pageShouldContain( "test-system resource file calculator-sample.html", "<li><a href=\"/specs/show/calculator-sample\" >calculator-sample</a></li>" );
		pageShouldContain( "test-system resource file sample.html", "<li><a href=\"/specs/show/sample\" >sample</a></li>" );
	}
	
	

	private void findSpecListLinkAndClickIt() {
		WebElement specListLink = driver.findElement(By.name("specListLink"));
		specListLink.click();
	}

	private void pageShouldContainTryThisCodeLink() {
		assertNotNull("contains try code form", driver.findElement(By.name("tryCodeLink")));
	}
	
	private void findTryCodeLinkAndClickIt() {
		WebElement link = driver.findElement(By.name("tryCodeLink"));
        link.click();
	}

	private void showSpec(String specName) {
		driver.get( "http://localhost:8080/specs/show/" + specName );
	}
	
	private void createSpec() {
		WebElement saveLink = driver.findElement(By.name( "createSpecXLink" ));
        saveLink.click();
	}

	private void findSpecNameFieldAndEnterTheValue(String nameOfTheNewSpec) {
		WebElement newSpecNameField = driver.findElement( By.name( "specXName" ) );
		typeInto( newSpecNameField, nameOfTheNewSpec );
	}

	private void findNewLinkAndClickIt() {
		WebElement createLink = driver.findElement(By.name("newLink"));
		createLink.click();
	}

	private void accessHomePage() {
		driver.get("http://localhost:8080");
	}


	private void pageShouldContain(String message, String expected) {
        assertThat( message, driver.getPageSource(), containsString( expected ) );
	}

	private void pageShouldContainModifyLink() {
        assertNotNull("modify link present", driver.findElement(By.name("modifyLink")));
	}

	private void uriShouldBe(String message, String expectedUri) {
		String url = driver.getCurrentUrl();
		String uri = url;
		if ( url.indexOf("?") != -1 ) {
			uri = url.substring( 0, url.indexOf("?"));
		}
		assertEquals( message, "http://localhost:8080" + expectedUri, uri );
	}
	
	private void queryStringShouldBe(String message, String expectedQueryString) {
		String url = driver.getCurrentUrl();
		String queryString = "";
		if ( url.indexOf("?") != -1 ) {
			queryString = url.substring( url.indexOf("?") + 1 );
		}
		assertEquals( message, expectedQueryString, queryString );
	}

	private void saveSpec() {
		WebElement saveLink = driver.findElement(By.name("saveSpecXLink"));
        saveLink.click();
	}

	private void updateSpecContent(String newContent) {
		WebElement textarea = driver.findElement(By.name("specX"));
		textarea.clear();
		typeInto( textarea, newContent );
	}

	private void accessSpecForModification(String specName) {
		driver.get("http://localhost:8080/specs/modify/" + specName);
	}

	private void typeInto(WebElement field, final String aString) {
		CharSequence seq = new CharSequence() {
			public CharSequence subSequence(int start, int end) {
				return null;
			}
			public int length() {
				return aString.length();
			}
			public char charAt(int index) {
				return aString.charAt(index);
			}
		};
		field.sendKeys(seq);
	}
	
	private static class TestPageChooser extends PageRouter {
		
		public Page choosePage(HttpServletRequest request) {
			Page choosen = super.choosePage( request );
			if ( choosen instanceof ExecutePage ) {
				ExecutePage resultPage = (ExecutePage) choosen;
				resultPage.setRunnerDirectory( "target/test-classes/test-system/" );
				return resultPage;
			} 
			if ( choosen instanceof ShowPage ) {
				ShowPage showPage = (ShowPage) choosen;
				showPage.setSpecXDirectory( "target/test-classes/test-system/" );
				return showPage;
			}	
			if ( choosen instanceof ModifyPage ) {
				ModifyPage modifyPage = (ModifyPage) choosen;
				modifyPage.setSpecXDirectory( "target/test-classes/test-system/" );
				return modifyPage;
			}
			if ( choosen instanceof SavePage ) {
				SavePage savePage = (SavePage) choosen;
				savePage.setSpecXDirectory( "target/test-classes/test-system/" );
				return savePage;
			}
			if ( choosen instanceof ListPage ) {
				ListPage listPage = (ListPage) choosen;
				listPage.setSpecXDirectory( "target/test-classes/test-system/" );
				return listPage;
			}
			
			return choosen;
		}
	}
	
}
