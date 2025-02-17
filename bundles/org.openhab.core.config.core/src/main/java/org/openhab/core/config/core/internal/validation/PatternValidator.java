/**
 * Copyright (c) 2010-2023 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.core.config.core.internal.validation;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.config.core.ConfigDescriptionParameter;
import org.openhab.core.config.core.ConfigDescriptionParameter.Type;
import org.openhab.core.config.core.validation.ConfigValidationMessage;

/**
 * The {@link ConfigDescriptionParameterValidator} for the pattern attribute of a
 * {@link ConfigDescriptionParameter}.
 *
 * @author Thomas Höfer - Initial contribution
 */
@NonNullByDefault
final class PatternValidator implements ConfigDescriptionParameterValidator {

    @Override
    public @Nullable ConfigValidationMessage validate(ConfigDescriptionParameter parameter, @Nullable Object value) {
        if (value == null || parameter.getType() != Type.TEXT || parameter.getPattern() == null) {
            return null;
        }

        if (!((String) value).matches(parameter.getPattern())) {
            MessageKey messageKey = MessageKey.PATTERN_VIOLATED;
            return new ConfigValidationMessage(parameter.getName(), messageKey.defaultMessage, messageKey.key,
                    String.valueOf(value), parameter.getPattern());
        }
        return null;
    }
}
