package org.ericmignot.page.section;

import java.io.IOException;

import org.ericmignot.util.FileReader;


public class SecondColumn {

	public static final String INVITATION = "target/html/invitation.html";
	private FileReader fileReader;
	private String columnContent;
	
	public SecondColumn() {
		setFileReader( new FileReader() );
	}
	
	public String html() {
		try {
			String content = "";
			if (getContent() != null) {
				Modify modifySection = new Modify();
				modifySection.setSpecX( getSpecX() );
				content += modifySection.html();
				
				content += fileReader.readFile( getContent() );
			}
			
			content += fileReader.readFile( INVITATION );
			return content;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setFileReader(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public void setContent(String content) {
		this.columnContent = content;
	}

	public String getContent() {
		return columnContent;
	}

	public String getSpecX() {
		int slash = columnContent.lastIndexOf( "/" );
		if (slash != -1) {
			String specX = columnContent.substring( slash + 1 );
			if (specX.indexOf( ".html" ) != -1 ) {
				return specX.substring(0, specX.indexOf( ".html" ) );
			}
			return specX;
		}
		return columnContent;
	}

}
