package org.ericmignot.page;

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

}
