package org.ericmignot.util;

public class GitUtils {

	public static String extractGitRepositoryName(String gitUrl) {
		if ( gitUrl.length() < "git://github.com/*/*.git".length() ) {
			return null;
		} else {
			return gitUrl.substring( gitUrl.lastIndexOf("/") + 1, gitUrl.length()-4 );
		}
	}

}
