package com.g2forge.habitat.metadata.access.computed.mixin;

import java.lang.reflect.AnnotatedElement;

import com.g2forge.alexandria.java.core.error.NotYetImplementedError;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
class CopyModifier implements ICopyModifier {
	protected final MixinMetadataRegistry.MixinMetadataRegistryBuilder builder;

	@Override
	public MixinMetadataRegistry.MixinMetadataRegistryBuilder done() {
		return getBuilder();
	}

	@Override
	public MixinMetadataRegistry.MixinMetadataRegistryBuilder of(AnnotatedElement element, Object value) {
		throw new NotYetImplementedError();
	}
}
