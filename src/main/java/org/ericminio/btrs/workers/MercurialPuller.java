package org.ericminio.btrs.workers;


public class MercurialPuller extends ComandLinePuller {

	public MercurialPuller() {
		this.runtime = Runtime.getRuntime();
	}

	public String getRepositoryName() {
		if ( url.length() < "https://bitbucket.org/a/b".length() ) {
			return null;
		} else {
			return url.substring( url.lastIndexOf("/") + 1, url.length() );
		}
	}

	public String getPullCommand() {
		return "hg clone " + url;
	}

}
