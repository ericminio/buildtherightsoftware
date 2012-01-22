package org.ericminio.btrs.deployment;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class ExternalToolsAvailabilityTest {

	@Test
	public void gitIsPresentInPath() throws Exception {
		launchAndExpect("git --version", "git version 1.");
	}
	
	@Test
	public void mercurialIsPresentInPath() throws Exception {
		launchAndExpect("hg --version", "Mercurial Distributed");
	}

	@Test
	public void mavenIsPresentInPath() throws Exception {
		launchAndExpect("mvn --version", "Apache Maven");
	}

	protected void launchAndExpect(String command, String expectedOutput) throws Exception{
		Process process = Runtime.getRuntime().exec(command);
		process.waitFor();
		String output = processOutput( process );
		assertThat(output, containsString(expectedOutput));
	}

	protected String processOutput(Process process) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String output = "";
		String line = null;
		while ((line = br.readLine()) != null)
			output += line + "\n";
		return output;
	}

}
