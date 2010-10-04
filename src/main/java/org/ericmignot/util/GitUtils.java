package org.ericmignot.util;

public class GitUtils {
	
	private String gitUrl;

	public GitUtils(String gitUrl) {
		this.gitUrl = gitUrl;
	}

	public String extractRepositoryName() {
		if ( gitUrl.length() < "git://github.com/*/*.git".length() ) {
			return null;
		} else {
			return gitUrl.substring( gitUrl.lastIndexOf("/") + 1, gitUrl.length()-4 );
		}
	}
	
	public static GitUtils git(String gitUrl) {
		return new GitUtils(gitUrl);
	}

}
