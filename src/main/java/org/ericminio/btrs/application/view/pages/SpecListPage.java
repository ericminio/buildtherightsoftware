package org.ericminio.btrs.application.view.pages;

import static org.ericminio.btrs.store.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.ericminio.btrs.application.view.ListRenderer;
import org.ericminio.btrs.domain.Spec;

public class SpecListPage implements ListRenderer {

	private List<Spec> specs;

	public void setSpecs(List<Spec> specs) {
		this.specs = specs;
	}

	public void render(Writer out) throws IOException {
		String template = readFile("target/html/template.html");
		String page = template.replaceAll("page-content", pageContent());
		out.write(page);
	}

	protected String pageContent() throws IOException {
		String list = "<ul>";
		for (Spec spec : specs) {
			list += "<li><a class=\"list\" href=\"/specs/show/"
					+ spec.getTitle() + "\" >" + spec.getTitle() + "</a></li>";
		}
		list += "</ul>";
		return list;
	}

}