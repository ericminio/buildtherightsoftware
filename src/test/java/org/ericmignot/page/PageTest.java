package org.ericmignot.page;

import static com.pyxis.matchers.dom.DomMatchers.hasUniqueSelector;
import static com.pyxis.matchers.dom.DomMatchers.withAttribute;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class PageTest {

	private Element page;
	
	@Before public void
	init() throws IOException, SAXException, ParserConfigurationException, FactoryConfigurationError {
		String content = new Page().html();
		InputStream is = new ByteArrayInputStream(content.getBytes("UTF-8"));
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		page = document.getDocumentElement();
	}
	
	@Test public void
	headerIncludesStylesReference() throws IOException, SAXException, ParserConfigurationException, FactoryConfigurationError {
		assertThat(page, hasUniqueSelector("link", withAttribute("href", "/style.css")));
	}
}
