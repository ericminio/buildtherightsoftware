package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.ericmignot.core.TryThisCode;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class ExecutePageTest {

	private ExecutePage page;
	private TryThisCode launcher;
	
	@Before public void
	init() {
		page = new ExecutePage( "sample" , "git://github.com/testaddict/mastermind.git" );
		launcher = page.getLauncher();
	}
	
	@Test public void
	launcherConfiguration() {
		assertNotNull( launcher );
		assertEquals( "se", "sample", launcher.getSpecX() );
		assertEquals( "repo", "git://github.com/testaddict/mastermind.git", launcher.getGitRepository() );
		assertEquals( "chrono", page.getChrono(), launcher.getChrono() );
	}
	
	@Test public void
	launchSpecExecutionWhenRenderIsCalled() throws IOException, InterruptedException {
		TryThisCode launcherMock = mock(TryThisCode.class);
		when(launcherMock.getDirectory()).thenReturn("target/test-classes/test-page-result");
		when(launcherMock.getExecutionOutputDirectory()).thenReturn("");
		when(launcherMock.getSpecX()).thenReturn("sample");
		page.setLauncher(launcherMock);
		
		page.content();
		verify(launcherMock).go();
	}
	
	@Test public void
	containsModifyLink() throws IOException {
		TryThisCode launcherMock = mock(TryThisCode.class);
		when(launcherMock.getDirectory()).thenReturn("target/test-classes/test-page-result");
		when(launcherMock.getExecutionOutputDirectory()).thenReturn("");
		when(launcherMock.getSpecX()).thenReturn("sample");
		page.setLauncher(launcherMock);
		
		Element doc = doc( page );
		assertThat( doc, hasSelector( "a", withAttribute("name", "modifyLink")
									     , withAttribute("href", "/specs/modify/sample") ));
	}
	
	@Test public void
	canRemoveAScriptSection() {
		String content = "before<script toto>tutu</script>after";
		assertEquals( "script removed", "beforeafter", page.removeScriptSection(content) );
	}
	
	
}
