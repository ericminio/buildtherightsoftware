package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.core.SpecSaver;
import org.junit.Before;
import org.junit.Test;

public class SavePageTest {

	private SavePage page;
	
	@Before public void
	init() {
		page = new SavePage( "sample", "toto" );
	}
	
	@Test public void
	storesSpecXAndSpecContent() {
		assertThat( "specX", page.getSpecX(), equalTo( "sample") );
		assertThat( "specX", page.getSpecXContent(), equalTo( "toto") );
	}
	
	@Test public void
	defaultWorkingDirectory() {
		assertThat( "default dir", page.getSpecXDirectory(), equalTo( PageTemplate.DEFAULT_WORKING_DIRECTORY ));
	}
	
	@Test public void
	specXDirectoryCanBeChanged() {
		page.setSpecXDirectory( "tutu" );
		assertThat( "specX dir", page.getSpecXDirectory(), equalTo( "tutu" ));
	}
	
	@Test public void
	callsTheSaverWhenRenderingThePage() throws IOException {
		assertNotNull( "saver", page.getSpecSaver() );
		SpecSaver saverMock = mock(SpecSaver.class);
		page.setSpecSaver(saverMock);
		page.content();
		verify(saverMock).setDirectory(page.getSpecXDirectory());
		verify(saverMock).save( "sample", "toto" );
	}
	
	@Test public void
	finallyDisplaysTheNewSpecXContentAsShowPageWouldDoIt() {
		assertTrue( "is a ShowPage", page instanceof ShowPage );
	}
}
