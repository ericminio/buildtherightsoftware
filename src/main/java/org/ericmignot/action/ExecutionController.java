package org.ericmignot.action;

import static org.ericmignot.util.HttpRequestInformationExtractor.containsGetParameter;
import static org.ericmignot.util.HttpRequestInformationExtractor.trueIfUriStartsWith;
import static org.ericmignot.util.HttpRequestInformationExtractor.uriWithoutThePrefix;

import java.io.Writer;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.ericmignot.core.Spec;
import org.ericmignot.jetty.Action;
import org.ericmignot.jetty.Controller;
import org.ericmignot.jetty.View;
import org.ericmignot.page.ResultPage;
import org.ericmignot.store.SpecRepository;

public class ExecutionController implements Controller {
	
	private static final String URI_PREFIX = "/specs/execute/";
	public static final String REPO_URL = "repo";
	
	private String directory;
	private Execute execute;
	private ResultPage resultPage;
	
	public ExecutionController() {
		setAction( new Execute() );
		setView( new ResultPage() );
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
	
	protected String extractGitRepositoryName(String gitUrl) {
		if ( gitUrl.length() < "git://github.com/*/*.git".length() ) {
			return null;
		} else {
			return gitUrl.substring( gitUrl.lastIndexOf("/") + 1, gitUrl.length()-4 );
		}
	}

	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}

	public void setAction(Action action) {
		this.execute = (Execute) action;
	}

	public void setView(View view) {
		this.resultPage = (ResultPage) view;
	}

}
