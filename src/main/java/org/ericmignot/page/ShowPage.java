package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.page.section.ModifyLink;

public class ShowPage extends Page {

	public ShowPage(String specX) {
		super( specX );
	}

	public String html() throws IOException {
		String template = super.html();
		String page = template.replaceAll( "page-content", pageContent() );
		return page;
	}

	private String pageContent() throws IOException {
		String content = "";
		
		content += getModifySection();
		content += readFile( getSpecXDirectory() + getSpecX() + ".html" );
		content += readFile( "target/html/invitation.html" );
		
		return content;
	}

	private String getModifySection() {
		ModifyLink section = new ModifyLink();
		section.setSpecX( getSpecX() );
		return section.html();
	}

}
