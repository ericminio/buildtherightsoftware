package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.page.section.Modify;
import org.ericmignot.util.FileReader;

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

	public String pageContent() throws IOException {
		String content = "";
		
		content += getModifySection();
		content += fileReader.readFile( specXDirectory + specX + ".html" );
		content += fileReader.readFile( "target/html/invitation.html" );
		
		return content;
	}

	private String getModifySection() {
		Modify section = new Modify();
		section.setSpecX( specX );
		return section.html();
	}

	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}

}
