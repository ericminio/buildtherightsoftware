package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.containsNotEmptyGetParameter;
import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericminio.btrs.application.route.controllers.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.application.route.UserRequest;
import org.ericminio.btrs.application.view.Renderer;
import org.ericminio.btrs.application.view.pages.ResultPage;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecRepository;
import org.ericminio.btrs.workers.Execution;

public class ExecutionController implements UserRequest {
	
	private static final String URI_PREFIX = "/specs/execute/";
	public static final String REPO_URL = "repo";
	
	private String directory;
	private Execution execute;
	private ResultPage resultPage;
	
	public ExecutionController() {
		setExecution( new Execution() );
		setRenderer( new ResultPage() );
	}
	
	public boolean isActivatedBy(HttpServletRequest request) {
		return trueIfUriStartsWith( URI_PREFIX, request ) 
			&& containsNotEmptyGetParameter( REPO_URL, request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		String chrono = ""+new Date().getTime();
		Spec spec = repository.getSpecByTitle( uriWithoutThePrefix(URI_PREFIX, request) );

		execute.setWorkingDirectory( directory );
		execute.setChrono( chrono );
		execute.setSpec( spec );
		execute.setGitUrl( request.getParameter(REPO_URL) );
		execute.work();

		resultPage.setWorkingDirectory( directory );
		resultPage.setChrono( chrono );
		resultPage.setSpec(spec);
		resultPage.setGitRepositoryName( execute.getSourcePuller().getRepositoryName() );
		resultPage.render( out);
	}
	
	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}

	public void setExecution(Execution execution) {
		this.execute = execution;
	}

	public void setRenderer(Renderer view) {
		this.resultPage = (ResultPage) view;
	}

}
