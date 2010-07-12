package org.ericmignot.page;

import org.ericmignot.page.section.SecondColumn;

public class ShowPage extends Page {

	public static final String DEFAULT_WORKING_DIRECTORY = "specs/";
	private String specX;
	private String workingDirectory;
	
	public ShowPage(String specX) {
		this.specX = specX;
		this.workingDirectory = DEFAULT_WORKING_DIRECTORY;
		setSecondColumn(new SecondColumn());
	}

	@Override
	protected void updateSpecificContent() {
		getSecondColumn().setContent( workingDirectory + "/" + specX + ".html" );
	}

	public String getSpecX() {
		return specX;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String dir) {
		this.workingDirectory = dir;
		updateSpecificContent();
	}

}
