package org.ericmignot.page.section;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.Renderer;

public class LabelMention implements Renderer {

	private Spec spec;
	
	public void render(Writer out) {
		String labelMention = "<span class=\"label\">Labels: " + spec.getLabel() + "</span>";
		try {
			out.write( labelMention );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

}
