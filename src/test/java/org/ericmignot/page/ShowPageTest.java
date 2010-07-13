package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.ericmignot.util.PageFileReader;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ShowPageTest {

	private ShowPage page;
	private PageFileReader fileReaderMock;
	
	@Before public void
	init() throws IOException, SAXException, ParserConfigurationException, FactoryConfigurationError {
		page = new ShowPage( "sample" );
	}
	
	@Test public void
	specXIsStored() {
		assertThat( "spec-x stored", page.getSpecX(), equalTo( "sample" ) );
	}
	
	@Test public void
	defaultWorkingDirectory() {
		assertThat( "default working dir", page.getSpecXDirectory(), equalTo( ShowPage.DEFAULT_WORKING_DIRECTORY ) );
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
	
}
