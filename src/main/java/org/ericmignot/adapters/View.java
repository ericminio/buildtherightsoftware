package org.ericmignot.adapters;

import java.io.Writer;


public interface View {

	public void setSpec(Spec spec);
	
	public void render(Writer out);
}
