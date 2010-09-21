package org.ericmignot.util;

import org.ericmignot.core.Spec;

public class HtmlParagraphSpec implements Spec {

	private String title;
	private String content;
	private String label;
	
	public HtmlParagraphSpec(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public void setContent(String newContent) {
		content = newContent;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String newLabel) {
		label = newLabel;
	}

}
