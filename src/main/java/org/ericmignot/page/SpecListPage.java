package org.ericmignot.page;

import static org.ericmignot.util.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.ui.ListRenderer;

public class SpecListPage implements ListRenderer {

	private List<Spec> specs;
	
	public void setSpecs(List<Spec> specs) {
		this.specs = specs;
	}

	public void render(Writer out) {
		try {
			String template = readFile( "target/html/template.html" );
			String page = template.replaceAll( "page-content", pageContent() );
			out.write( page );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String pageContent() throws IOException {
		String list = "<ul>";
		for (Spec spec : specs) {
			list += "<li><a class=\"list\" href=\"/specs/show/" + spec.getTitle() + "\" >" + spec.getTitle() + "</a></li>";
		}
		list += "</ul>";
		return list;
	}

}