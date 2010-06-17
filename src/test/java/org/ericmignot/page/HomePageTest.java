package org.ericmignot.page;

import static org.ericmignot.test.ContainsTableMatcher.containsTableAsRootElement;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class HomePageTest {

	private HomePage homePage;
	private String content;
	
	@Before public void
	init() {
		homePage = new HomePage();
		assertNotNull(homePage.getFirstColumn());
		content = homePage.html();
	}
	
	@Test public void
	isAnHtmlDocument() {
		assertThat( content, startsWith( "<html>" ) );
		assertThat( content, endsWith( "</html>" ) );
	}
	
	@Test public void
	linksStyle() {
		assertThat( content, containsString( HomePage.STYLE ));
	}
	
	@Test public void
	containsATable() {
		assertThat( content, containsTableAsRootElement() );
	}
	
	@Test public void
	displayFirstColumn() {
		FirstColumn firstColumnMock = mock(FirstColumn.class);
		homePage.setFirstColumn( firstColumnMock );
		
		homePage.html();
		verify(firstColumnMock).html();
	}
	
	
}
