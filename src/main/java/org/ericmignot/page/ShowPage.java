package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.page.section.ModifyLink;

public class ShowPage extends Page {

	public static final String DEFAULT_WORKING_DIRECTORY = "specs/";
	private String specX;
	private String specXDirectory;
	
	public ShowPage(String specX) {
		this.specX = specX;
		this.specXDirectory = DEFAULT_WORKING_DIRECTORY;
	}

	public String getSpecX() {
		return specX;
	}

	public String getSpecXDirectory() {
		return specXDirectory;
	}

	public void setSpecXDirectory(String dir) {
		this.specXDirectory = dir;
	}
	
	public String html() throws IOException {
		String template = super.html();
		String page = template.replaceAll( "page-content", pageContent() );
		return page;
	}

	private String pageContent() throws IOException {
		String content = "";
		
		content += getModifySection();
		content += readFile( specXDirectory + specX + ".html" );
		content += readFile( "target/html/invitation.html" );
		
		return content;
	}

	private String getModifySection() {
		ModifyLink section = new ModifyLink();
		section.setSpecX( specX );
		return section.html();
	}

}
