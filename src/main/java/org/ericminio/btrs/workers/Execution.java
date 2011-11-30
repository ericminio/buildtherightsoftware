package org.ericminio.btrs.workers;

import static org.ericminio.btrs.workers.GitUtils.git;

import org.ericminio.btrs.domain.FileWorker;
import org.ericminio.btrs.domain.SourcePuller;
import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.Compiler;
import org.ericminio.btrs.domain.SpecRunner;

public class Execution implements FileWorker {
	
	private String directory;
	private String chrono;
	private Spec spec;
	private String gitUrl;
	
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

	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	public void work() throws Exception {
		String gitRepositoryName = git( gitUrl ).extractRepositoryName();
		
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
	
	public void setSourcePuller(SourcePuller puller) {
		this.puller = puller;
	}

	public void setCompiler(Compiler compiler) {
		this.compiler = compiler;
	}

	public void setSpecRunner(SpecRunner runner) {
		this.runner = runner;
	}

	
}
