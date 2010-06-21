package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;


public class SecondColumnTest {

	private SecondColumn secondColumn;
	
	@Before public void
	init() {
		secondColumn = new SecondColumn();
	}
	
	@Test public void
	containsSeExampleAndCodeSubmisionInvitation() throws IOException {
		String content = secondColumn.html();
		
		assertThat( "rule for section", content, containsString("Rule for"));
		assertThat( "rule for section", content, containsString("mastermind"));
		
		assertThat( "rule for section", content, containsString("</textarea>"));
		assertThat( "rule for section", content, containsString("Try this code"));
	}
}
