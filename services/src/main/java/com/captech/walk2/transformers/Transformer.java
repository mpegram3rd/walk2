package com.captech.walk2.transformers;

public interface Transformer<Source, Target> {
	Target transform (Source original);
}
