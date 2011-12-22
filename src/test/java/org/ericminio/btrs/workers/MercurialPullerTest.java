package org.ericminio.btrs.workers;


import static org.ericminio.btrs.store.FileUtils.removeDir;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MercurialPullerTest {

	private MercurialPuller puller;
	
	@Before public void
	init() {
		puller = new MercurialPuller();
	}
	
	@Test public void
	canConfigureWorkingDirectory() {
		puller.setWorkingDirectory( "this-path" );
		assertThat( puller.getWorkingDirectory(), equalTo( "this-path" ) );
	}
	
	@Test public void
	canConfigureUrl() {
		puller.setUrl( "this-url" );
		assertThat( puller.getUrl(), equalTo( "this-url" ) );
	}
	
	@Test
	public void canExtractRepositoryNameFromABitbucketUrl() {
		puller.setUrl( "https://bitbucket.org/ericminio/bowling-kata" );
		assertThat( puller.getRepositoryName(), equalTo( "bowling-kata" ) );
	}
	
	@Test
	public void cannotExtractRepositoryIfUrlDoesNotTargetAMercurialRepository() {
		puller.setUrl( "an-url" );
		assertThat( puller.getRepositoryName(), is(nullValue()) );
	}
	
	@Test
	public void pullCommand() {
		puller.setUrl( "an-url" );
		assertThat( puller.getPullCommand(), equalTo( "hg clone an-url" ) );
	}
	
	@Test public void
	canConfigureRuntime() {
		Runtime aRuntime = mock( Runtime.class );
		puller.setRuntime( aRuntime );
		assertThat( puller.getRuntime(), equalTo( aRuntime ) );
	}
	
	@Test public void
	defaultRuntimeIsTheCurrentRuntime() {
		assertThat( puller.getRuntime(), equalTo( Runtime.getRuntime() ) );
	}

	@Test
	public void createDirectoryToWorkIn() throws Exception {
		puller.setRuntime( aRuntimeMock() );
		puller.setWorkingDirectory( "a-test-path-to-verify-mercurial-puller-dir-creation" );
		puller.work();
		assertTrue( new File( "a-test-path-to-verify-mercurial-puller-dir-creation" ).exists() );
		removeDir( "a-test-path-to-verify-mercurial-puller-dir-creation" );
	}
	
	@Test public void
	executesCommandWithCorrectParameters() throws Exception {
		Runtime runtimeMock = aRuntimeMock();
		puller.setRuntime( runtimeMock );
		puller.setWorkingDirectory( "a-test-path-to-verify-mercurial-puller-command" );
		puller.work();
		verify( runtimeMock ).exec( puller.getPullCommand(), 
									null,
									new File( "a-test-path-to-verify-mercurial-puller-command" ) );
		removeDir( "a-test-path-to-verify-mercurial-puller-command" );
	}

	private Runtime aRuntimeMock() throws IOException {
		Runtime toReturn = mock( Runtime.class );
		Process processMock = mock( Process.class );
		when( toReturn.exec( anyString(), (String[])anyObject(), (File)anyObject() ) ).thenReturn( processMock );
		return toReturn;
	}

	
}
