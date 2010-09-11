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
		String content = 
					modifyLink()
					+ specLabel()
					+ specContent() 
					+ invitationToTryACode();
		
		return content;
	}

	protected String specLabel() {
		String label = readFile( getSpecXDirectory() + getSpecX() + ".label" );
		return "<span class=\"label\">Labels: "+ label + "</span>";
	}

	protected String invitationToTryACode() {
		String invitation = readFile( "target/html/invitation.html" );
		return invitation.replaceAll( "spec-x", getSpecX() );
	}

	protected String specContent() {
		return readFile( getFilePathToBeIncluded() );
	}

	protected String modifyLink() {
		String modifyLink = readFile( "target/html/modifyLink.html" );
		return modifyLink.replaceAll( "spec-x", getSpecX() );
	}

}
