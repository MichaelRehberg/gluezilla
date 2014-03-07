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
package de.mrehberg.gluezilla.wicket;

import de.mrehberg.gluezilla.wicket.pages.ChooseProductPage;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.application.IComponentInstantiationListener;

/**
 * Intercepts requests for pages annotated with <code>@RequireProduct</code>. If
 * a product is currently selected, the request continues - otherwise the
 * request is redirected to the product selection page.
 *
 * @author mt
 */
public class RequireProductListener implements IComponentInstantiationListener {

    /**
     * Redirect to this page if no product is currently selected. This page must
     * not be annotated with <code>@RequireProduct</code>!
     */
    private final Class<? extends Page> productSelectionPage = ChooseProductPage.class;

    @Override
    public void onInstantiation(Component component) {
        if (component instanceof Page) {
            final RequireProduct annotation = component.getClass().getAnnotation(RequireProduct.class);
            if (annotation != null && !isProductSelected()) {
                throw new RestartResponseAtInterceptPageException(productSelectionPage);
            }
        }
    }

    protected boolean isProductSelected() {
        return GluezillaSession.get().getSelectedProduct() != null;
    }
}
