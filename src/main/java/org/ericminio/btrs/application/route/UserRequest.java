package org.ericminio.btrs.application.route;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.domain.SpecRepository;

public interface UserRequest {

	public boolean isActivatedBy(HttpServletRequest request);
	
	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception;
	
	public void setRenderer(Renderer renderer);

}
