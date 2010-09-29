package org.ericmignot.adapters.ui;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.store.SpecRepository;

public interface UserRequest {

	public boolean isActivatedBy(HttpServletRequest request);
	
	public void handle(HttpServletRequest request, SpecRepository repository, Writer out);
	
	public void setRenderer(Renderer renderer);

}
