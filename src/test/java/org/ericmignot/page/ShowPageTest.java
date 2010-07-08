package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ShowPageTest {

	private ShowPage page;
	
	@Before public void
	init() {
		page = new ShowPage( "a-spec-x" );
	}
	
	@Test public void
	specXIsStored() {
		assertEquals( "a-spec-x", page.getSpecX() );
	}
	
	@Test public void
	defaultWorkingDirectory() {
		assertThat( "default working dir", page.getWorkingDirectory(), equalTo( ShowPage.DEFAULT_WORKING_DIRECTORY ) );
	}
	
	@Test public void
	workingDirectoryCanBeChanged() {
		page.setWorkingDirectory( "target/test-classes/test-system" );
		assertThat( "working dir", page.getWorkingDirectory(), equalTo( "target/test-classes/test-system" ) );
	}
	
	@Test public void
	contentTargetsSpecXFile() {
		page.setWorkingDirectory( "target/test-classes/test-system" );
		String expected = "target/test-classes/test-system/a-spec-x.html";
		assertThat( "column content", page.getSecondColumn().getContent(), equalTo( expected ));
	}
}
