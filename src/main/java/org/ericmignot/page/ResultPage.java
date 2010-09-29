package org.ericmignot.page;

import static org.ericmignot.util.FileUtils.readFile;
import static org.ericmignot.util.HtmlManipulator.extractBodyContent;
import static org.ericmignot.util.HtmlManipulator.removeAllScriptSections;

import java.io.IOException;
import java.io.Writer;

import org.ericmignot.adapters.domain.Spec;
import org.ericmignot.adapters.ui.Renderer;

public class ResultPage implements Renderer {

	private String directory;
	private String chrono;
	private Spec spec;
	private String gitRepositoryName;
	
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
			String template = readFile( "target/html/template.html" );
			String page = template.replaceAll( "page-content", pageContent() );
			out.write( page );
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
}
