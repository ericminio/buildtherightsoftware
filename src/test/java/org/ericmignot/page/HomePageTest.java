package org.ericmignot.page;

import static org.ericmignot.test.HasImageHtmlTag.hasImageHtmlTag;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class HomePageTest {

	private String homePageContent;
	
	@Before public void
	init() {
		homePageContent = new HomePage().html();
	}
	
	@Test public void
	containsLogo() {
		assertThat( homePageContent, hasImageHtmlTag( "logo.png" ));
	}
	
}
