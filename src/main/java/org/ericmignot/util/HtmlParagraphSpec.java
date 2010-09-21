package org.ericmignot.util;

import org.ericmignot.core.Spec;
import org.ericmignot.store.Repository;

public class HtmlParagraphSpec implements Spec {

	private String title;
	private String content;
	
	public HtmlParagraphSpec() {
		
	}
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

	public void saveIn(Repository repository) {
		repository.saveSpec( this );
	}

}
