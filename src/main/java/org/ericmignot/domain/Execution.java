package org.ericmignot.domain;

import static org.ericmignot.util.GitUtils.extractGitRepositoryName;

import org.ericmignot.adapters.domain.FileWorker;
import org.ericmignot.adapters.domain.Spec;

public class Execution implements FileWorker {
	
	private String directory;
	private String chrono;
	private Spec spec;
	private String gitUrl;
	
	private GitPuller puller;
	private MavenCompiler compiler;
	private GreenPepperRunner runner;
	
	public Execution() {
		setGitPuller( new GitPuller() );
		setCompiler( new MavenCompiler() );
		setRunner( new GreenPepperRunner() );
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
		puller.setUrl( gitUrl );
		puller.work();
		compiler.setWorkingDirectory( directory + "/runs/" + chrono + "/" + gitRepositoryName );
		compiler.work();
		runner.setWorkingDirectory( directory + "/" );
		runner.setSpecFileRelativeFile( spec.getTitle() + ".html" );
		runner.setClassesRelativeDirectory( "runs/" + chrono + "/" + gitRepositoryName + "/target/classes") ;
		runner.setOutputRelativeDirectory( "runs/" + chrono + "/" + gitRepositoryName + "/se/out") ;
		runner.work();
	}
	
	public void setGitPuller(GitPuller gitDownload) {
		this.puller = gitDownload;
	}

	public void setCompiler(MavenCompiler compiler) {
		this.compiler = compiler;
	}

	public void setRunner(GreenPepperRunner runner) {
		this.runner = runner;
	}

	
}
