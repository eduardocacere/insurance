package com.insurance.config.core.domain;

import org.apache.commons.lang3.StringUtils;
import org.immutables.value.Value;

import static org.apache.commons.lang3.StringUtils.trim;
import static org.apache.commons.lang3.Validate.isTrue;

@Value.Immutable
public interface MiddleName {
    int MAX_LENGTH = 25;

    @Value.Parameter
    String value();

    @Value.Check
    default MiddleName check() {
        final String trimmed = trim(value());
        isTrue(trimmed.length() <= MAX_LENGTH);

        if (!StringUtils.equals(value(), trimmed)) {
            return ImmutableMiddleName.of(trimmed);
        }

        return this;
    }
}
