package org.ericmignot.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.ericmignot.page.Page;
import org.ericmignot.router.PageRouter;
 
public class PageHandler extends AbstractHandler
{
	private PageRouter router;
	
	public PageHandler() {
		setPageRouter( new PageRouter() );
	}
	
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        		throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        
        Page choosen = router.choosePage(request);
        response.getWriter().println(choosen.html());
    }
    
    public PageRouter getPageRouter() {
		return router;
	}
	
	public void setPageRouter(PageRouter router) {
		this.router = router;
	}
 
}