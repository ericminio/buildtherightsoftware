package org.ericmignot.controller;

import static org.ericmignot.util.GitUtils.git;
import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.store.SpecRepository;
import org.ericmignot.adapters.ui.Renderer;
import org.ericmignot.adapters.ui.UserRequest;
import org.ericmignot.domain.Execution;
import org.ericmignot.page.ResultPage;

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
			&& containsGetParameter( REPO_URL, request );
	}

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) {
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
		resultPage.setGitRepositoryName( git( request.getParameter(REPO_URL) ).extractRepositoryName() );
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
