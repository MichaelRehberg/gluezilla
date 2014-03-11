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
package de.mrehberg.gluezilla.wicket.pages;

import java.util.List;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.wicket.GluezillaApplication;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.util.string.StringValue;

/**
 *
 * @author mt
 */
public class ChooseProductPage extends BrandedPage {

    private static final long serialVersionUID = 4760553396916413691L;
    private static final String TARGET_PARAM = "target";

    public static PageParameters linkTo(Class<?> targetPage) {
        return new PageParameters().add(TARGET_PARAM, targetPage.getName());
    }

    public ChooseProductPage(PageParameters params) {
        clearOriginalDestination();
        final Class<? extends Page> target = getTarget(params);
        
        final List<GProduct> products = GluezillaApplication.SAMPLE_PRODUCTS;
        add(new ListView<GProduct>("products", products) {

            private static final long serialVersionUID = -3993516077236429657L;

            @Override
            protected void populateItem(final ListItem<GProduct> item) {
                BookmarkablePageLink<?> link = new BookmarkablePageLink<>("button", target, new PageParameters().set(0, item.getModelObject().getProductName()));
                link.setBody(Model.of(item.getModelObject().getProductName()));
                item.add(link);
            }
        });
    }

    private Class<? extends Page> getTarget(PageParameters params) {
        final StringValue targetParam = params.get(TARGET_PARAM);
        if (!targetParam.isNull() && !targetParam.isEmpty()) {
            try {
                return (Class<? extends Page>) Class.forName(targetParam.toString());
            } catch (Exception e) {
                // ignore, use safe default
            }
        }
        return Application.get().getHomePage();
    }
}
