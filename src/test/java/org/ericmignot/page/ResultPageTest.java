package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.ericmignot.core.TryThisCode;
import org.ericmignot.util.PageFileReader;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class ResultPageTest {

	private ResultPage page;
	private TryThisCode launcher;
	private PageFileReader fileReaderMock;
	
	@Before public void
	init() {
		page = new ResultPage( "sample" , "git://github.com/testaddict/mastermind.git" );
		launcher = page.getLauncher();
	}
	
	@Test public void
	launcherConfiguration() {
		assertNotNull( launcher );
		assertEquals( "se", "sample", launcher.getSe() );
		assertEquals( "repo", "git://github.com/testaddict/mastermind.git", launcher.getGitRepository() );
		assertEquals( "chrono", page.getChrono(), launcher.getChrono() );
	}
	
	@Test public void
	containsModifyLink() throws IOException {
		Element doc = doc( page );
		assertThat( doc, hasSelector( "a", withAttribute("name", "modifyLink")
											   , withAttribute("href", "/specs/modify/sample") ));
	}
	
}
