package org.ericmignot.page;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


public class LegacyListPage extends LegacyPageTemplate {

	public LegacyListPage() {
		super( );
	}

	public String content() throws IOException {
		String content = readFile( "target/html/template.html" );
		content = content.replaceAll( "page-content", pageContent() );
		return content;
	}

	protected String pageContent() throws IOException {
		String header = "Spec list:";
		String list = renderListAsHtml( getSpecFileList() );
		return header + list;
	}

	protected String renderListAsHtml(String[] names) {
		String list = "<ul>";
		for (int i=0; i<names.length; i++) {
			String nameWithoutExtension = names[i].substring(0, names[i].indexOf( ".html" ));
			list += "<li><a class=\"list\" href=\"/specs/show/" + nameWithoutExtension + "\" >" + nameWithoutExtension + "</a></li>";
		}
		list += "</ul>";
		return list;
	}

	protected String[] getSpecFileList() {
		File dir = new File( getSpecXDirectory() );
		String[] names = dir.list( new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith( ".html" );
			}
		});
		return names;
	}

}
