package org.ericmignot.page;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

public class PageChooserTest {

	private PageChooser pageChooser;
	
	@Before public void
	init() {
		pageChooser = new PageChooser();
	}
	
	@Test public void
	canExtractSeFromUri() {
		assertEquals( "Se", "sample", pageChooser.extractSe( "/specs/sample/execute" ) );
		assertEquals( "Se", "azerty", pageChooser.extractSe( "/specs/azerty/execute" ) );
		assertEquals( "Se", null, pageChooser.extractSe( "/" ) );
		assertEquals( "Se", null, pageChooser.extractSe( "" ) );
		assertEquals( "Se", null, pageChooser.extractSe( null ) );
	}
	
	@Test public void
	returnsHomePageWhenSiteHomeIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/");
		assertNotNull( "choosen page", pageChooser.choosePage( request ) );
		assertTrue( "serves home page", pageChooser.choosePage( request ) instanceof HomePage );
	}
	
	@Test public void
	returnsResultWhenSeExecutionIsCalled() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/specs/sample/execute");
		when(request.getParameter("repo")).thenReturn( "git://github.com/testaddict/mastermind.git" );
		
		assertTrue( "serves result page", pageChooser.choosePage( request ) instanceof ResultPage );
	}
	
	
}
