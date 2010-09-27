package org.ericmignot.adapters;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

public interface Controller {

	public boolean isActivatedBy(HttpServletRequest request);
	
	public void setWorkingDirectory(String directory);
	
	public void handle(HttpServletRequest request, SpecRepository repository, Writer out);
	
	public void setAction(Action action);
	
	public void setRenderer(Renderer renderer);
	
}
