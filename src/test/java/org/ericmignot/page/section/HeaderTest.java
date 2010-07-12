package org.ericmignot.page.section;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.ericmignot.page.section.Header;
import org.junit.Before;
import org.junit.Test;

public class HeaderTest {

	private Header header;
	
	@Before public void
	init() {
		header = new Header();
	}
	
	@Test public void
	containsStyleLink() {
		String expectedHeader = "<html>" +
			"<head>" + Header.STYLE + "</head>" +
			"<body>" +
			"<table><tr>";
		assertThat( header.html() , equalTo( expectedHeader ) );
	}
}
