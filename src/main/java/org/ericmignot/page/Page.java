package org.ericmignot.page;

import java.io.IOException;

import org.ericmignot.util.FileReader;

public class Page {
	
	protected FileReader fileReader;
	
	public Page() {
		fileReader = new FileReader();
	}

	public String html() throws IOException {
		
		String content = "";
		
		content += "<html><head>" 
			+ "<link rel='stylesheet' type='text/css' href='/style.css' />"
			+ "</head><body><table><tr>";
		
		content += "<td class=\"firstcolumn\" >";
		content += "<a href='/' ><img src='/logo.png' border='0' /></a>";
		content += "</td>";
		
		content += "<td class=\"secondcolumn\" >";
		content += pageContent();
		content += "</td>";
		
		content += "</tr></table></body></html>";
		
		return content;
	}

	public String pageContent() throws IOException {
		return null;
	}

}
