package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.adapters.LegacyPage;
import static org.ericmignot.util.FileUtils.readFile;

public class LegacyPageTemplate implements LegacyPage {
	
	public static final String DEFAULT_WORKING_DIRECTORY = "specs/";
	
	private String specX;
	private String specXDirectory;
	
	public LegacyPageTemplate() {
		this(null);
	}
	
	public LegacyPageTemplate(String specX) {
		this.specX = specX;
		this.specXDirectory = DEFAULT_WORKING_DIRECTORY;
	}
	
	public String content() throws IOException {
		String content = readFile( "target/html/template.html" );
		return content;
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
	
	protected String modifyLink() {
		String modifyLink = readFile( "target/html/modifyLink.html" );
		return modifyLink.replaceAll( "spec-x", getSpecX() );
	}
	
	protected String specLabel() {
		String label = readFile( getSpecXDirectory() + getSpecX() + ".label" );
		return "<span class=\"label\">Labels: "+ label + "</span>";
	}

	protected String invitationToTryACode() {
		String invitation = readFile( "target/html/invitation.html" );
		return invitation.replaceAll( "spec-x", getSpecX() );
	}

}
