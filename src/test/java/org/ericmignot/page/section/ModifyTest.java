package org.ericmignot.page.section;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ModifyTest {

	private Modify section;
	
	@Before public void
	init() {
		section = new Modify();
	}
	
	@Test public void
	isDoorToModifySpec() {
		section.setSpecX( "toto" );
		String html = section.html();
		assertThat( "link tag", html, containsString( "<a name=modifyLink href=/specs/modify/toto class=action >Modify</a>" ) );
	}
}
