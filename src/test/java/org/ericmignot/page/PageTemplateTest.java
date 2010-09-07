package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasUniqueSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static com.pyxis.matchers.dom.WithContentText.withContent;
import static org.ericmignot.util.DocumentBuilder.doc;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class PageTemplateTest {

	private Element doc;
	
	@Before public void
	init() throws IOException  {
		doc = doc( new PageTemplate() );
	}
	
	@Test public void
	templateExists() {
		assertTrue( "template available", new File( "target/html/template.html").exists() );
	}
	
	@Test public void
	headerIncludesStylesReference() throws IOException, SAXException, ParserConfigurationException, FactoryConfigurationError {
		assertThat(doc, hasUniqueSelector("link", withAttribute("href", "/style.css")));
	}
	
	@Test public void
	pageIsABigTableWithOneSingleRow() {
		assertThat( doc, hasUniqueSelector( "body table tr" ));
	}
	
	@Test public void
	firstColumnContainsTheLogo() {
		assertThat( doc, hasUniqueSelector( "body table tr td.firstcolumn", 
							hasUniqueSelector( "img", withAttribute("src", "/logo.png"))) );
	}
	
	@Test public void
	secondColumnContainsContentParameter() {
		assertThat( doc, hasUniqueSelector( "body table tr td.secondcolumn", 
							withContent("page-content")) );
	}
}
