package org.ericminio.btrs.workers;


public class GitPuller extends ComandLinePuller {

	public GitPuller() {
		this.runtime = Runtime.getRuntime();
	}

	public String getRepositoryName() {
		if ( url.length() < "git://github.com/*/*.git".length() ) {
			return null;
		} else {
			return url.substring( url.lastIndexOf("/") + 1, url.length()-4 );
		}
	}

	public String getPullCommand() {
		return "git clone " + url;
	}

}
