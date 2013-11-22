package com.captech.walk2.transformers;

public interface InPlaceTransformer<Source, Target> {
	
	public void transform (Source source, Target target);
}
