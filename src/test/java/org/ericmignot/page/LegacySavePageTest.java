package org.ericmignot.page;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.ericmignot.domain.LegacySpecSaver;
import org.junit.Before;
import org.junit.Test;

public class LegacySavePageTest {

	private LegacySavePage page;
	
	@Before public void
	init() {
		page = new LegacySavePage( "sample", "toto", "code" );
		assertNotNull( "saver", page.getSpecSaver() );
	}
	
	@Test public void
	storesSpecXAndSpecContent() {
		assertThat( "specX", page.getSpecX(), equalTo( "sample") );
		assertThat( "specX", page.getSpecXContent(), equalTo( "toto") );
		assertThat( "specX", page.getSpecXLabel(), equalTo( "code") );
	}
	
	@Test public void
	defaultWorkingDirectory() {
		assertThat( "default dir", page.getSpecXDirectory(), equalTo( LegacyPageTemplate.DEFAULT_WORKING_DIRECTORY ));
	}
	
	@Test public void
	specXDirectoryCanBeChanged() {
		page.setSpecXDirectory( "tutu" );
		assertThat( "specX dir", page.getSpecXDirectory(), equalTo( "tutu" ));
	}
	
	@Test public void
	savesTheContentWhenRenderingThePage() throws IOException {
		LegacySpecSaver saverMock = mock(LegacySpecSaver.class);
		page.setSpecSaver(saverMock);
		page.content();
		verify(saverMock).setDirectory(page.getSpecXDirectory());
		verify(saverMock).saveContent( "sample", "toto" );
	}
	
	@Test public void
	storesSpecXLabel() {
		assertThat( "specX", page.getSpecXLabel(), equalTo( "code") );
	}
	
	@Test public void
	savesTheLabelWhenRenderingThePage() throws IOException {
		LegacySpecSaver saverMock = mock(LegacySpecSaver.class);
		page.setSpecSaver(saverMock);
		page.content();
		verify(saverMock).setDirectory(page.getSpecXDirectory());
		verify(saverMock).saveLabel( "sample", "code" );
	}
	
}
