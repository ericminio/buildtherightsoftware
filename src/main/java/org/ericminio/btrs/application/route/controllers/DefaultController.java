package org.ericminio.btrs.application.route.controllers;

import static org.ericminio.btrs.store.SpecBuilder.aSpec;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.ericminio.btrs.domain.Spec;
import org.ericminio.btrs.domain.SpecRepository;

public class DefaultController extends ShowController {

	public void handle(HttpServletRequest request, SpecRepository repository, Writer out) throws Exception {
		view.setSpec( sampleSpec() );
		view.render( out );
	}

	private Spec sampleSpec() {
		return aSpec().withTitle( "sample" ).withContent( sampleContent() ).build();
	}
	
	private String sampleContent() {
		return "<p>"
				+ "<table>" 
				+ "<tr>" 
				+ "	<td class=\"rulefor\">Rule for</td>" 
				+ "	<td>mastermind</td>" 
				+ "</tr>"
				+ "<tr>" 
				+ "<td class=\"ruleforheader\">given the secret</td>" 
				+ "<td class=\"ruleforheader\">when player plays</td>" 
				+ "<td class=\"ruleforheader\">does he win ?</td>" 
				+ "</tr>"
				+ "<tr> <td>green</td> <td>red</td> <td>no</td> </tr>"
				+ "<tr> <td>green</td> <td>green</td> <td>yes</td> </tr>"
				+ "</table>"
			 + "</p>";
	}

}
