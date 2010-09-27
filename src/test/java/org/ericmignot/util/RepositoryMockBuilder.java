package org.ericmignot.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.ericmignot.adapters.Spec;
import org.ericmignot.adapters.SpecRepository;

public class RepositoryMockBuilder {

	private Map<String, Spec> specs;
	
	public RepositoryMockBuilder() {
		specs = new HashMap<String, Spec>(); 
	}
	
	public static RepositoryMockBuilder aMockRepo() {
		return new RepositoryMockBuilder();
	}
	
	public RepositoryMockBuilder withSpec(Spec spec) {
		specs.put( spec.getTitle(), spec);
		return this;
	}
	
	public SpecRepository build() {
		SpecRepository repoMock = mock( SpecRepository.class );
		for (Spec spec : specs.values()) {
			when(repoMock.getSpecByTitle( spec.getTitle() ) ).thenReturn( spec );
		}
		when(repoMock.getSpecs()).thenReturn( new ArrayList<Spec>(specs.values()) );
		return repoMock;
	}
	
}
