package org.ericmignot.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.ericmignot.core.Spec;
import org.ericmignot.store.SpecRepository;
import org.mockito.Mockito;

public class RepositoryMockBuilder {

	private Spec spec;
	
	public static RepositoryMockBuilder aMockRepo() {
		return new RepositoryMockBuilder();
	}
	
	public RepositoryMockBuilder withOneSpec(Spec spec) {
		this.spec = spec;
		return this;
	}
	
	public SpecRepository build() {
		SpecRepository repoMock = mock( SpecRepository.class );
		when(repoMock.getSpecByTitle( Mockito.anyString() ) ).thenReturn( spec );
		return repoMock;
	}
}
