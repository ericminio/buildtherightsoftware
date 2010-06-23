package org.ericmignot.page;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class HomePageDisplayTest {

	private HomePage homePage;
	
	@Before public void
	init() {
		homePage = new HomePage();
	}
	
	@Test public void
	displaySeSampleInSecondColumnByDefault() {
		assertEquals( HomePage.SE_SAMPLE, homePage.getSecondColumn().getContent() );
	}
	
	@Test public void
	displayPageContent() {
		SecondColumn secondColumnMock = mock(SecondColumn.class);
		homePage.setSecondColumn(secondColumnMock);
		
		homePage.html();
		verify(secondColumnMock).html();
	}
	
}
