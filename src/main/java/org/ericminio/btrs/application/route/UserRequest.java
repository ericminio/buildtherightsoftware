package org.ericminio.btrs.application.route;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.domain.SpecRepository;

public interface UserRequest {

	boolean isActivatedBy(HttpServletRequest request);
	
	void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception;
	
	void setRenderer(Renderer renderer);

}
