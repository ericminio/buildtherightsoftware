package org.ericmignot.page;

import java.io.IOException;
import static org.ericmignot.util.FileUtils.readFile;

public class LegacyNewPage extends LegacyPageTemplate {

	public String content() throws IOException {
		String content = readFile( "target/html/template.html" );
		content = content.replaceAll( "page-content", pageContent() );
		return content;
	}

	protected String pageContent() throws IOException {
		String content = "";
		
		String modifyLink = readFile( "target/html/new.html" );
		content += modifyLink.replaceAll( "spec-x", getSpecX() );
		
		return content;
	}
}
