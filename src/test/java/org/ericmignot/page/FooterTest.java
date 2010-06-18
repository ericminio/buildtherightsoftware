package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class FooterTest {

	private Footer footer;
	
	@Before public void
	init() {
		footer = new Footer();
	}
	
	@Test public void
	closesBodyAndHtmlTags() {
		assertThat( footer.html() , equalTo( "</tr></table></body></html>" ) );
	}
}
