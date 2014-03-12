/*
 * Copyright 2014 mt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Workaround for WELD-000070 - skips injection on Java classes which are not
 * valid CDI beans.
 */
public class ExtendedComponentInjector extends ComponentInjector {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected <T> void inject(T instance) {
        final Class<? extends Object> clazz = instance.getClass();
        final String className = clazz.getName();
        if (className.startsWith("de.agilecoders.wicket.core.markup.html.bootstrap.")) {
            logger.info("BLACKLISTED: " + className);
        } else {
            try {
                super.inject(instance);
            } catch (IllegalArgumentException e) {
                logger.info("UNSUPPORTED: " + className + " (WELD-000070 non-static inner class)",e);
            }
        }
    }

}
