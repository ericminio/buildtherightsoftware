package org.ericmignot.jetty;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.store.SpecRepository;

public interface Controller {

	public boolean isActivatedBy(HttpServletRequest request);
	
	public void setWorkingDirectory(String directory);
	
	public void handle(HttpServletRequest request, SpecRepository repository, Writer out);
	
	public void setAction(Action action);
	
	public void setView(View view);
	
}
