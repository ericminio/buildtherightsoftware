package org.ericmignot.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class GitPullerTest {

	private GitPuller puller;
	
	@Before public void
	init() {
		puller = new GitPuller();
	}
	
	@Test
	public void canConfigureWorkingDirectory() {
		puller.setWorkingDirectory( "a-path" );
		assertThat( puller.getWorkingDirectory(), equalTo( "a-path" ) );
	}
	
	@Test
	public void canConfigureUrl() {
		puller.setUrl( "an-url" );
		assertThat( puller.getUrl(), equalTo( "an-url" ) );
	}
	
	@Test
	public void pullCommand() {
		puller.setUrl( "an-url" );
		assertThat( puller.getPullCommand(), equalTo( "git clone an-url" ) );
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
		puller.setWorkingDirectory( "a-path" );
		puller.work();
		assertTrue( new File( "a-path" ).exists() );
		new File( "a-path" ).delete();
	}
	
	@Test public void
	executesCommandWithCorrectParameters() throws Exception {
		Runtime runtimeMock = aRuntimeMock();
		puller.setRuntime( runtimeMock );
		puller.setWorkingDirectory( "a-path" );
		puller.work();
		verify( runtimeMock ).exec( puller.getPullCommand(), 
									null,
									new File( "a-path" ) );
	}

	private Runtime aRuntimeMock() throws IOException {
		Runtime toReturn = mock( Runtime.class );
		Process processMock = mock( Process.class );
		when( toReturn.exec( anyString(), (String[])anyObject(), (File)anyObject() ) ).thenReturn( processMock );
		return toReturn;
	}

}
