package org.ericmignot.page;

import java.io.IOException;


public class ShowPage extends PageTemplate {

	public ShowPage(String specX) {
		super( specX );
	}

	public String getFilePathToBeIncluded() {
		return getSpecXDirectory() + getSpecX() + ".html";
	}

	public String content() throws IOException {
		String template = super.content();
		String page = template.replaceAll( "page-content", pageContent() );
		return page;
	}

	protected String pageContent() throws IOException {
		String content = "";
		
		String modifyLink = readFile( "target/html/modifyLink.html" );
		content += modifyLink.replaceAll( "spec-x", getSpecX() );
		
		content += readFile( getFilePathToBeIncluded() );
		
		String invitation = readFile( "target/html/invitation.html" );
		content += invitation.replaceAll( "spec-x", getSpecX() );
		
		return content;
	}

}
