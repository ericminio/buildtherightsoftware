package org.ericmignot.application;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class ExternalToolsAvailabilityTest { 

	@Test public void
	gitIsPresent() {
		launchAndExpect( "git --version", "git version 1." );
	}
	
	@Test public void
	mavenIsPresent() {
		launchAndExpect( "mvn --version", "Apache Maven 2." );
	}
	
	protected void launchAndExpect(String command, String expectedOutput) {
		try {
			Process process;
			process = Runtime.getRuntime().exec( command, null, null );
			process.waitFor();
			
			InputStream stdin = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);
            String output = "";
            String line = null;
            while ( (line = br.readLine()) != null)
                output += line + "\n";  
			assertThat( output, containsString( expectedOutput ) );
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
