package org.ericmignot.page;

import java.io.IOException;


public class NewPage extends PageTemplate {

	public String content() throws IOException {
		String template = super.content();
		String page = template.replaceAll( "page-content", pageContent() );
		return page;
	}

	protected String pageContent() throws IOException {
		String content = "";
		
		String modifyLink = readFile( "target/html/new.html" );
		content += modifyLink.replaceAll( "spec-x", getSpecX() );
		
		return content;
	}
}
