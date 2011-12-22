package org.ericminio.btrs.deployment;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class ExternalToolsAvailabilityTest {

	@Test
	public void gitIsPresent() throws Exception {
		launchAndExpect("git --version", "git version 1.");
	}
	
	@Test
	public void mercurialIsPresent() throws Exception {
		launchAndExpect("hg --version", "Mercurial Distributed");
	}

	@Test
	public void mavenIsPresent() throws Exception {
		launchAndExpect("mvn --version", "Apache Maven");
	}

	protected void launchAndExpect(String command, String expectedOutput) throws Exception{
		Process process;
		process = Runtime.getRuntime().exec(command, null, new File("."));
		process.waitFor();

		InputStream stdin = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(stdin);
		BufferedReader br = new BufferedReader(isr);
		String output = "";
		String line = null;
		while ((line = br.readLine()) != null)
			output += line + "\n";
		assertThat(output, containsString(expectedOutput));
	}

}
