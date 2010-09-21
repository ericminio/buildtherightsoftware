package org.ericmignot.core;

import org.ericmignot.store.Repository;

public interface Spec {

		public String getTitle();
		public String getContent();
		
		public void saveIn(Repository repository);
}
