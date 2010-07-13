package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.DomMatchers.withText;
import static com.pyxis.matchers.dom.WithContentText.withContent;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.ericmignot.util.PageFileReader;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Element;

public class ModifyPageTest {

	private ModifyPage page;
	
	@Before public void
	init() throws IOException {
		page = new ModifyPage( "sample" );
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
	containsTextareaToEditSpecX() throws IOException {
		Element doc = doc( page );
		assertThat( doc, hasSelector( "textarea", withAttribute("name", "specX")
												, withAttribute("cols", "80")
												, withAttribute("rows", "20") ));
	}
	
	@Test public void
	containsFormToSaveTheSpecX() throws IOException {
		Element doc = doc( page );
		assertThat( doc, hasSelector( "form", withAttribute("name", "saveSpecXForm")
											, withAttribute("method", "post") 
											, withAttribute("action", "/specs/save/sample") ));
		
		assertThat( doc, hasSelector( "a", withAttribute("name", "saveSpecXLink")
										 , withAttribute("href", "javascript:saveSpecXForm.submit()") 
										 , withAttribute("class", "button")
									     , withText("Save") ));
	}
	
	@Ignore
	@Test public void
	textareaContainsTheSpecCode() throws IOException {
		String specX = new PageFileReader().readFile( "specs/sample.html" );
		Element doc = doc( page );
		assertThat( doc, hasSelector( "textarea", withContent( specX ) ));
	}
	
}
