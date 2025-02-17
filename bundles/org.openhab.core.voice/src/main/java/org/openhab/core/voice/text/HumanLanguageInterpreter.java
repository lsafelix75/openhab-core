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
package org.openhab.core.voice.text;

import java.util.Locale;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.voice.DialogContext;

/**
 * This is the interface that a human language text interpreter has to implement.
 *
 * @author Tilman Kamp - Initial contribution
 */
@NonNullByDefault
public interface HumanLanguageInterpreter {

    /**
     * Returns a simple string that uniquely identifies this service
     *
     * @return an id that identifies this service
     */
    String getId();

    /**
     * Returns a localized human readable label that can be used within UIs.
     *
     * @param locale the locale to provide the label for
     * @return a localized string to be used in UIs
     */
    String getLabel(@Nullable Locale locale);

    /**
     * Interprets a human language text fragment of a given {@link Locale}
     *
     * @param locale language of the text (given by a {@link Locale})
     * @param text the text to interpret
     * @return a human language response
     */
    String interpret(Locale locale, String text) throws InterpretationException;

    /**
     * Interprets a human language text fragment of a given {@link Locale} with optional access to the context of a
     * dialog execution.
     *
     * @param locale language of the text (given by a {@link Locale})
     * @param text the text to interpret
     * @return a human language response
     */
    default String interpret(Locale locale, String text, @Nullable DialogContext dialogContext)
            throws InterpretationException {
        return interpret(locale, text);
    }

    /**
     * Gets the grammar of all commands of a given {@link Locale} of the interpreter
     *
     * @param locale language of the commands (given by a {@link Locale})
     * @param format the grammar format
     * @return a grammar of the specified format
     */
    @Nullable
    String getGrammar(Locale locale, String format);

    /**
     * Gets all supported languages of the interpreter by their {@link Locale}s
     *
     * @return Set of supported languages (each given by a {@link Locale}) or null, if there is no constraint
     */
    Set<Locale> getSupportedLocales();

    /**
     * Gets all supported grammar format specifiers
     *
     * @return Set of supported grammars (each given by a short name)
     */
    Set<String> getSupportedGrammarFormats();
}
