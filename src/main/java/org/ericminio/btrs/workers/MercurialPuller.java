package org.ericminio.btrs.workers;


public class MercurialPuller extends ComandLinePuller {

	public MercurialPuller() {
		this.runtime = Runtime.getRuntime();
	}

	public String getRepositoryName() {
		if ( url.length() < "https://bitbucket.org/a/b".length() ) {
			return null;
		} else {
			return url.substring( startIndex(), endIndex() );
		}
	}

	protected int endIndex() {
		return url.indexOf( " " ) != -1 ? url.indexOf( " " ) : url.length();
	}

	protected int startIndex() {
		return url.lastIndexOf("/") + 1;
	}

	public String getPullCommand() {
		return "hg clone " + url;
	}

}
