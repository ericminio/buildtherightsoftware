package org.ericmignot;

import java.io.IOException;

public class TryThisCode {

	private String se;
	private String repository;
	private String chrono;
	
	private String runnerDirectory;
	
	public TryThisCode() {
		setRunnerDirectory( "specs/" );
	}
	
	public void setSe(String se) {
		this.se = se;
	}

	public void setGitRepository(String repository) {
		this.repository = repository;
	}

	public void setChrono(String chrono) {
		this.chrono = chrono;
	}

	public void go() throws IOException, InterruptedException {
		GitDownload fetcher = new GitDownload();
		fetcher.setDirectory( getFetcherDirectory() );
		fetcher.fetch( repository );
		
		MavenCompiler compiler = new MavenCompiler();
		compiler.setDirectory( getCompilerDirectory() );
		compiler.mavenCleanAndCompile();
		
		SeRunner seRunner = new SeRunner();
		seRunner.setRunnerDirectory( getRunnerDirectory() );
		seRunner.setSeRelativeFile( se + ".html" );
		seRunner.setClassesRelativeDirectory( getClassesRelativeDirectory() );
		seRunner.setOutputRelativeDirectory( getExecutionOutputDirectory() );
		seRunner.executeSpecification();
	}

	public void setRunnerDirectory(String path) {
		this.runnerDirectory = path;
	}

	public String getRunnerDirectory() {
		return runnerDirectory;
	}
	
	public String getFetcherDirectory() {
		return getRunnerDirectory() + "runs/" + chrono;
	}
	
	public String getCompilerDirectory() {
		return getFetcherDirectory() + "/" + extractProjectName( repository );
	}
	
	public String extractProjectName(String gitUrl) {
		if ( gitUrl.length() < "git://github.com/*/*.git".length() ) {
			return null;
		} else {
			return gitUrl.substring( gitUrl.lastIndexOf("/") + 1, gitUrl.length()-4 );
		}
	}

	public String getClassesRelativeDirectory() {
		return "runs/" + chrono + "/" + extractProjectName( repository ) + "/target/classes";
	}

	public String getExecutionOutputDirectory() {
		return "runs/" + chrono + "/" + extractProjectName( repository ) + "/se/out";
	}

	public String getSe() {
		return se;
	}

	public String getGitRepository() {
		return repository;
	}

	public String getChrono() {
		return chrono;
	}

	

}
