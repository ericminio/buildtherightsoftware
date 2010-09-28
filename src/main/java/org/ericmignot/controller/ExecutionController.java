package org.ericmignot.controller;

import static org.ericmignot.util.GitUtils.extractGitRepositoryName;
import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.adapters.Action;
import org.ericmignot.adapters.ActionController;
import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.SpecRepository;
import org.ericmignot.adapters.Renderer;
import org.ericmignot.domain.Execution;
import org.ericmignot.page.ResultPage;

public class ExecutionController implements ActionController {
	
	private static final String URI_PREFIX = "/specs/execute/";
	public static final String REPO_URL = "repo";
	
	private String directory;
	private Execution execute;
	private ResultPage resultPage;
	
	public ExecutionController() {
		setAction( new Execution() );
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
		resultPage.setGitRepositoryName( extractGitRepositoryName( request.getParameter(REPO_URL) ) );
		resultPage.render( out);
	}
	
	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}

	public void setAction(Action action) {
		this.execute = (Execution) action;
	}

	public void setRenderer(Renderer view) {
		this.resultPage = (ResultPage) view;
	}

}
