package com.g2forge.habitat.metadata.predicate;

import com.g2forge.alexandria.java.type.ref.ITypeRef;

public interface IPredicateType<T> {
	public ITypeRef<T> getObjectType();

	public Class<?> getPredicateID();
}
