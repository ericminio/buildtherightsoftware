package org.ericminio.btrs.application.view.pages;

import static org.ericminio.btrs.store.FileUtils.readFile;

import java.io.IOException;
import java.io.Writer;

import org.ericminio.btrs.application.view.SpecRenderer;
import org.ericminio.btrs.domain.Spec;

public class ShowPage implements SpecRenderer {

	private Spec spec;

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	public void render(Writer out) throws IOException {
		String template = readFile("target/html/template.html");
		String page = template.replaceAll("page-content", pageContent());
		out.write(page);
	}

	protected String pageContent() {
		String modifyLink = readFile("target/html/modifyLink.html");
		modifyLink = modifyLink.replaceAll("spec-x", spec.getTitle());

		String invitation = readFile("target/html/invitation.html");
		invitation = invitation.replaceAll("spec-x", spec.getTitle());

		String label = "<span class=\"label\">Label: " + spec.getLabel()
				+ "</span>";

		String specContent = "<span class=\"spec\">" + spec.getContent()
				+ "</span>";

		String content = "\n" + modifyLink + "\n" + label + "\n" + specContent
				+ "\n" + invitation;
		return content;
	}
}
