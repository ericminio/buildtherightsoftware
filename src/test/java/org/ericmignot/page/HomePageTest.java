package org.ericmignot.page;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class HomePageTest {

	private HomePage homePage;
	
	@Before public void
	init() {
		homePage = new HomePage();
		assertNotNull(homePage.getHeader());
		assertNotNull(homePage.getFirstColumn());
		assertNotNull(homePage.getSecondColumn());
		assertNotNull(homePage.getFooter());
	}
	
	@Test public void
	displayHeader() {
		Header headerMock = mock(Header.class);
		homePage.setHeader(headerMock);
		
		homePage.html();
		verify(headerMock).html();
	}
	
	@Test public void
	displayFirstColumn() {
		FirstColumn firstColumnMock = mock(FirstColumn.class);
		homePage.setFirstColumn( firstColumnMock );
		
		homePage.html();
		verify(firstColumnMock).html();
	}
	
	@Test public void
	displayPageContent() {
		SecondColumn secondColumnMock = mock(SecondColumn.class);
		homePage.setSecondColumn(secondColumnMock);
		
		homePage.html();
		verify(secondColumnMock).html();
	}
	
	@Test public void
	displayFooter() {
		Footer footerMock = mock(Footer.class);
		homePage.setFooter(footerMock);
		
		homePage.html();
		verify(footerMock).html();
	}
	
	
}
