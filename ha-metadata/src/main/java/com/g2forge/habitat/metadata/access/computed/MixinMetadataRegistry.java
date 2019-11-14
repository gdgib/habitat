package com.g2forge.habitat.metadata.access.computed;

import java.util.Map;

import com.g2forge.alexandria.java.function.IPredicate2;
import com.g2forge.alexandria.java.function.builder.IBuilder;
import com.g2forge.habitat.metadata.access.IMetadataAccessor;
import com.g2forge.habitat.metadata.access.IMetadataRegistry;
import com.g2forge.habitat.metadata.access.ITypedMetadataAccessor;
import com.g2forge.habitat.metadata.access.NoAccessorFoundException;
import com.g2forge.habitat.metadata.type.predicate.IPredicateType;
import com.g2forge.habitat.metadata.type.subject.ISubjectType;
import com.g2forge.habitat.metadata.value.subject.ISubject;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Builder(toBuilder = true)
public class MixinMetadataRegistry implements IMetadataRegistry {
	public static class MixinMetadataRegistryBuilder implements IBuilder<MixinMetadataRegistry> {
		public <T> MixinMetadataRegistryBuilder accessorTyped(ITypedMetadataAccessor<T, ? extends ISubject, ? extends IPredicateType<T>> accessor) {
			accessor(accessor::isApplicable, (IMetadataAccessor) accessor);
			return this;
		}
	}

	@Singular
	@Getter(AccessLevel.PROTECTED)
	protected final Map<IPredicate2<? super ISubjectType, ? super IPredicateType<?>>, IMetadataAccessor> accessors;

	@Override
	public IMetadataAccessor find(IFindContext context, ISubjectType subjectType, IPredicateType<?> predicateType) throws NoAccessorFoundException {
		for (Map.Entry<IPredicate2<? super ISubjectType, ? super IPredicateType<?>>, IMetadataAccessor> entry : getAccessors().entrySet()) {
			if (entry.getKey().test(subjectType, predicateType)) return entry.getValue();
		}
		throw new NoAccessorFoundException("No matching accessors found");
	}
}
