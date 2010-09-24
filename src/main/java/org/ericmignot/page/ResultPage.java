package org.ericmignot.page;

import static org.ericmignot.util.HtmlManipulator.extractBodyContent;
import static org.ericmignot.util.HtmlManipulator.removeAllScriptSections;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.core.Spec;
import org.ericmignot.jetty.View;
import org.ericmignot.util.FileReader;

public class ResultPage implements View {

	private FileReader fileReader;
	private String directory;
	private String chrono;
	private Spec spec;
	private String gitRepositoryName;
	
	public ResultPage() {
		fileReader = new FileReader();
	}
	
	public void setWorkingDirectory(String directory) {
		this.directory = directory;
	}
	
	public void setChrono(String chrono) {
		this.chrono = chrono;
	}
	
	public void setSpec(Spec spec) {
		this.spec = spec;
	}

	public void setGitRepositoryName(String gitRepositoryName) {
		this.gitRepositoryName = gitRepositoryName;
	}


	public String getResultPagePath() {
		return directory + "/runs/" + chrono + "/" + gitRepositoryName + "/se/out/" + spec.getTitle() + ".html";
	}
	
	public String getCoberturaReportPath() {
		return directory + "/runs/" + chrono + "/" + gitRepositoryName + "/target/site/cobertura/frame-summary.html";
	}

	public void render(Writer out) {
		try {
			out.write( content() );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String content() throws IOException {
		String content = readFile( "target/html/template.html" );
		content = content.replaceAll( "page-content", pageContent() );
		return content;
	}

	protected String pageContent() throws IOException {
		String content = specLabel() + specContent() + coberturaReport();
		return content;
	}
	
	protected String specLabel() {
		return "<span class=\"label\">Labels: " + spec.getLabel() + "</span>";
	}

	protected String specContent() {
		return readFile( getResultPagePath() );
	}

	protected String coberturaReport() {
		String fullContent = readFile( getCoberturaReportPath() );
		String body = extractBodyContent( fullContent );
		return removeAllScriptSections( body );
	}
	
	public String readFile(String fileName) {
		return fileReader.readFile( fileName );
	}

	
	
	
}
