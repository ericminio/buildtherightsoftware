package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class ShowPageTest {

	private ShowPage page;
	
	@Before public void
	init() throws IOException {
		page = new ShowPage( "sample" );
	}
	
	@Test public void
	specXIsStored() {
		assertThat( "spec-x stored", page.getSpecX(), equalTo( "sample" ) );
	}
	
	@Test public void
	defaultWorkingDirectory() {
		assertThat( "default working dir", page.getSpecXDirectory(), equalTo( Page.DEFAULT_WORKING_DIRECTORY ) );
	}
	
	@Test public void
	workingDirectoryCanBeChanged() {
		page.setSpecXDirectory( "target/test-classes/test-system/" );
		assertThat( "working dir", page.getSpecXDirectory(), equalTo( "target/test-classes/test-system/" ) );
	}
	
	@Test public void
	containsModifyLink() throws IOException {
		Element doc = doc( page );
		assertThat( doc, hasSelector( "a", withAttribute("name", "modifyLink")
										 , withAttribute("href", "/specs/modify/sample") ));
	}
	
	@Test public void
	containsCodeSubmissionForm() throws IOException {
		Element doc = doc( page );
		assertThat( doc, hasSelector( "form", withAttribute("name", "tryCodeForm")
											, withAttribute("action", "/specs/execute/sample") ));
	}
	
}
