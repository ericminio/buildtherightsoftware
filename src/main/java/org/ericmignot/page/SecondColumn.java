package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.util.FileReader;


public class SecondColumn {

	public static final String SE_SAMPLE = "target/html/seSample.html";
	public static final String INVITATION = "target/html/invitation.html";
	
	private FileReader fileReader;
	
	public SecondColumn() {
		setFileReader( new FileReader() );
	}
	
	public String html() {
		try {
			String content = fileReader.readFile( SE_SAMPLE );
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

}
