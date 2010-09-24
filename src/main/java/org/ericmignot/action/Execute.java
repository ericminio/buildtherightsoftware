package org.ericmignot.action;

import org.ericmignot.core.GitPuller;
import org.ericmignot.core.MavenCompiler;
import org.ericmignot.core.Spec;
import org.ericmignot.core.SpecRunner;
import org.ericmignot.jetty.Action;

public class Execute implements Action {
	
	private String directory;
	private String chrono;
	private Spec spec;
	private String gitUrl;
	
	private GitPuller puller;
	private MavenCompiler compiler;
	private SpecRunner runner;
	
	public Execute() {
		setGitPuller( new GitPuller() );
		setCompiler( new MavenCompiler() );
		setRunner( new SpecRunner() );
	}
	
	public void setWorkingDirectory(String dir) {
		this.directory = dir;
	}

	public void setChrono(String chrono) {
		this.chrono = chrono;
	}

	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	public void work() {
		String gitRepositoryName = extractGitRepositoryName( gitUrl );
		
		puller.setWorkingDirectory( directory + "/runs/" + chrono );
		puller.setGitUrl( gitUrl );
		puller.work();
		compiler.setWorkingDirectory( directory + "/runs/" + chrono + "/" + gitRepositoryName );
		compiler.work();
		runner.setWorkingDirectory( directory + "/" );
		runner.setSpecFileRelativeFile( spec.getTitle() + ".html" );
		runner.setClassesRelativeDirectory( "runs/" + chrono + "/" + gitRepositoryName + "/target/classes") ;
		runner.setOutputRelativeDirectory( "runs/" + chrono + "/" + gitRepositoryName + "/se/out") ;
		runner.work();
	}
	
	protected String extractGitRepositoryName(String gitUrl) {
		if ( gitUrl.length() < "git://github.com/*/*.git".length() ) {
			return null;
		} else {
			return gitUrl.substring( gitUrl.lastIndexOf("/") + 1, gitUrl.length()-4 );
		}
	}

	public void setGitPuller(GitPuller gitDownload) {
		this.puller = gitDownload;
	}

	public void setCompiler(MavenCompiler compiler) {
		this.compiler = compiler;
	}

	public void setRunner(SpecRunner runner) {
		this.runner = runner;
	}

	
}
