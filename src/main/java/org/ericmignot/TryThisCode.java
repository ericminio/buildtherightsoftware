package org.ericmignot;

import java.io.IOException;

public class TryThisCode {

	private String se;
	private String repository;
	private String chrono;
	
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
		fetcher.setDirectory( "specs/" + chrono );
		fetcher.fetch( repository );
		
		MavenCompiler compiler = new MavenCompiler();
		String projectName = extractProjectName( repository );
		compiler.setDirectory( "specs/" + chrono + "/" + projectName );
		compiler.mavenCleanAndCompile();
		
		SeRunner seRunner = new SeRunner();
		seRunner.setRunnerDirectory( "specs/" );
		seRunner.setSeRelativeFile( se + ".html" );
		seRunner.setClassesRelativeDirectory( chrono + "/" + projectName + "/target/classes" );
		seRunner.setOutputRelativeDirectory( chrono + "/" + projectName + "/se/out" );
		seRunner.executeSpecification();
	}

	public String extractProjectName(String gitUrl) {
		return gitUrl.substring( gitUrl.lastIndexOf("/") + 1, gitUrl.length()-4 );
	}
	
}
