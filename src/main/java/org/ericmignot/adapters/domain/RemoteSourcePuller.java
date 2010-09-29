package org.ericmignot.adapters.domain;

public interface RemoteSourcePuller extends FileWorker {

	public void setUrl(String url);
}
