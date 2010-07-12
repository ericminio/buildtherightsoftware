package org.ericmignot;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.ericmignot.jetty.FileHandler;
import org.ericmignot.jetty.PageHandler;
import org.ericmignot.page.Page;
import org.ericmignot.page.ResultPage;
import org.ericmignot.page.ShowPage;
import org.ericmignot.router.PageRouter;
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
	executeSpecUrl() throws InterruptedException {
        driver.get("http://localhost:8080/");
        WebElement link = driver.findElement(By.name("tryCodeLink"));
        link.click();

      	assertEquals( "Url", "http://localhost:8080/specs/execute/sample?repo=git%3A%2F%2Fgithub.com%2Ftestaddict%2Fmastermind.git", driver.getCurrentUrl() );
	}
	
	@Test public void
	canAccessASpecificSpec() {
		driver.get("http://localhost:8080/specs/show/calculator-sample");
		String source = driver.getPageSource();
		assertThat( "rule for calculator", source, containsString( "<td>calculator</td>" ) );
	}
	
	@Test public void
	containsAModifyLink() {
		driver.get("http://localhost:8080/specs/show/calculator-sample");
		WebElement link = driver.findElement(By.name("modifyLink"));
		assertThat( "modify link href" , link.getAttribute( "href" ), equalTo( "/specs/modify/calculator-sample" ) );
        link.click();
        assertThat( "modify url", driver.getCurrentUrl(), equalTo( "http://localhost:8080/specs/modify/calculator-sample" ) );
	}
	
	
	
	
	
	private static class TestPageChooser extends PageRouter {
		
		public Page choosePage(HttpServletRequest request) {
			Page choosen = super.choosePage( request );
			if ( choosen instanceof ResultPage ) {
				ResultPage resultPage = (ResultPage) choosen;
				resultPage.setRunnerDirectory( "target/test-classes/test-system/" );
				return resultPage;
			} 
			if ( choosen instanceof ShowPage ) {
				ShowPage showPage = (ShowPage) choosen;
				showPage.setWorkingDirectory( "target/test-classes/test-system/" );
				return showPage;
			}	
			return choosen;
		}
	}
	
}
