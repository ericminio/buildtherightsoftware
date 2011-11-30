package org.ericminio.btrs.store;

import org.ericminio.btrs.domain.Spec;

public class SpecBuilder {

	private String title;
	private String content;
	private String label;
	
	public static SpecBuilder aSpec() {
		return new SpecBuilder();
	}
	
	public SpecBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public SpecBuilder withContent(String content) {
		this.content = content;
		return this;
	}
	
	public SpecBuilder withLabel(String label) {
		this.label = label;
		return this;
	}
	
	public Spec build() {
		PlainTextSpec newSpec = new PlainTextSpec(title);
		newSpec.setContent( content);
		newSpec.setLabel(label);
		return newSpec;
	}
}
