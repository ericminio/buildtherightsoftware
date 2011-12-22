package org.ericminio.btrs.workers;

import org.ericminio.btrs.domain.Compiler;
import org.ericminio.btrs.domain.FileWorker;
import org.ericminio.btrs.domain.SourcePuller;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecRunner;

public class Execution implements FileWorker {
	
	private String directory;
	private String chrono;
	private Spec spec;
	private String sourceRepositoryUrl;
	
	private SourcePuller puller;
	private Compiler compiler;
	private SpecRunner runner;
	
	public Execution() {
		setSourcePuller( new GitPuller() );
		setCompiler( new MavenCompiler() );
		setSpecRunner( new GreenPepperRunner() );
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

	public void setSourceRepositoryUrl(String url) {
		this.sourceRepositoryUrl = url;
		if ( url.indexOf( "bitbucket.org" ) != -1) {
			setSourcePuller( new MercurialPuller() );
		}
		else {
			setSourcePuller( new GitPuller() );
		}
	}

	public void work() throws Exception {
		pullSource();
		String repositoryName = puller.getRepositoryName();
		compile(repositoryName);
		runSpec(repositoryName);
	}

	private void runSpec(String repositoryName) throws Exception {
		runner.setWorkingDirectory( directory + "/" );
		runner.setSpecFileRelativeFile( spec.getTitle() + ".html" );
		runner.setClassesRelativeDirectory( "runs/" + chrono + "/" + repositoryName + "/target/classes") ;
		runner.setOutputRelativeDirectory( "runs/" + chrono + "/" + repositoryName + "/se/out") ;
		runner.work();
	}

	private void compile(String gitRepositoryName) throws Exception {
		compiler.setWorkingDirectory( directory + "/runs/" + chrono + "/" + gitRepositoryName );
		compiler.work();
	}

	private void pullSource() throws Exception {
		puller.setWorkingDirectory( directory + "/runs/" + chrono );
		puller.setUrl( sourceRepositoryUrl );
		puller.work();
	}
	
	public void setSourcePuller(SourcePuller puller) {
		this.puller = puller;
	}

	public SourcePuller getSourcePuller() {
		return this.puller;
	}

	public void setCompiler(Compiler compiler) {
		this.compiler = compiler;
	}

	public Compiler getCompiler() {
		return this.compiler;
	}

	public void setSpecRunner(SpecRunner runner) {
		this.runner = runner;
	}

	public SpecRunner getSpecRunner() {
		return this.runner;
	}
}
