package org.ericmignot.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.ericmignot.core.Spec;
import org.junit.Test;

public class InMemoryRepositoryTest {

	private InMemoryRepository repository = new InMemoryRepository();
	
	@Test public void
	storesTheSpecInAnInternalMapUsingTheTitleAsTheKeyWhenSavingTheSpec() {
		Map<String, Spec> mapMock = mock( Map.class );
		repository.setStoringMap( mapMock );
		
		Spec spec = aSpecWithTitle( "a title") ;

		repository.saveSpec( spec );
		verify( mapMock ).put( "a title" , spec ); 
	}
	
	protected Spec aSpecWithTitle(String title) {
		Spec specMock = mock( Spec.class );
		when( specMock.getTitle() ).thenReturn( title );
		return specMock;
	}
	
	@Test public void
	canRetrieveSpecsByName() {
		Spec first = aSpecWithTitle( "first" );
		Spec second = aSpecWithTitle( "second" );
		repository.saveSpec( first );
		repository.saveSpec( second );
		assertThat( repository.getSpecByTitle( "first" ), equalTo( first ) );
		assertThat( repository.getSpecByTitle( "second" ), equalTo( second ) );
	}
	
}
