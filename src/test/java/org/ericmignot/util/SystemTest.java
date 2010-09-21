package org.ericmignot.util;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public abstract class SystemTest {

	private static Server server;
	private static Thread thread;
	protected WebDriver driver;
	
	@BeforeClass public static void
	setUp() throws Exception {
		server = new Server(8080);
		
		PageHandler testPageHandler = new PageHandler();
		testPageHandler.setPageRouter( new FakePageRouter() );
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { new FileHandler(), testPageHandler });
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
	
	protected void modifySpecLabel(String label) {
		findFieldAndEnterTheValue( "label", label );
	}
	
	protected void pageShouldContainCoberturaSummaryReport() {
		pageShouldContainTheText( "displays Cobertura report", "Coverage Report - All Packages" );
	}
	
	protected void specShouldPass() {
		pageShouldContainTheText( "spec passes", "background-color: #AAFFAA;" );
	}

	protected void createNewSpec(String name) {
		findFieldAndEnterTheValue( "specXName", name );
		WebElement saveLink = driver.findElement(By.name( "createSpecXLink" ));
        saveLink.click();
	}
	

	protected void accessSpecList() {
		WebElement specListLink = driver.findElement(By.name("specListLink"));
		specListLink.click();
	}

	protected void pageShouldContainTryThisCodeLink() {
		assertNotNull("contains try code form", driver.findElement(By.name("tryCodeLink")));
	}
	
	protected void executeSpecWithDefaultCode() {
		WebElement link = driver.findElement(By.name("tryCodeLink"));
        link.click();
	}

	protected void showSpec(String specName) {
		driver.get( "http://localhost:8080/specs/show/" + specName );
	}
	
	
	
	protected void findFieldAndEnterTheValue(String fieldName, String label) {
		WebElement labelField = driver.findElement( By.name( fieldName ) );
		typeInto( labelField, label );
	}

	protected void activateNewSpecCreation() {
		WebElement createLink = driver.findElement(By.name("newLink"));
		createLink.click();
	}

	protected void accessHomePage() {
		driver.get("http://localhost:8080");
	}


	protected void pageShouldContainTheText(String message, String expected) {
        assertThat( message, driver.getPageSource(), containsString( expected ) );
	}

	protected void pageShouldContainModifyLink() {
        assertNotNull("modify link present", driver.findElement(By.name("modifyLink")));
	}

	protected void uriShouldBe(String message, String expectedUri) {
		String url = driver.getCurrentUrl();
		String uri = url;
		if ( url.indexOf("?") != -1 ) {
			uri = url.substring( 0, url.indexOf("?"));
		}
		assertEquals( message, "http://localhost:8080" + expectedUri, uri );
	}
	
	protected void queryStringShouldBe(String message, String expectedQueryString) {
		String url = driver.getCurrentUrl();
		String queryString = "";
		if ( url.indexOf("?") != -1 ) {
			queryString = url.substring( url.indexOf("?") + 1 );
		}
		assertEquals( message, expectedQueryString, queryString );
	}

	protected void saveSpec() {
		WebElement saveLink = driver.findElement(By.name("saveSpecXLink"));
        saveLink.click();
	}

	protected void updateSpecContent(String newContent) {
		WebElement textarea = driver.findElement(By.name("specX"));
		textarea.clear();
		typeInto( textarea, newContent );
	}

	protected void accessSpecForModification(String specName) {
		driver.get("http://localhost:8080/specs/modify/" + specName);
	}

	protected void typeInto(WebElement field, final String aString) {
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
		field.clear();
		field.sendKeys(seq);
	}
	
	protected void labelFieldShouldContainTheValue(String value) {
		WebElement element = driver.findElement(By.name("label"));
		assertEquals( "label field", value, element.getValue() );
		
	}
	
	private static class FakePageRouter extends PageRouter {
		
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
