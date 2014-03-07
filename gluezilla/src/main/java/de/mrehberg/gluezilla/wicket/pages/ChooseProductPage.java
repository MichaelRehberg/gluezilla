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

import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.wicket.GluezillaSession;
import java.util.Arrays;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.protocol.http.WebApplication;

/**
 *
 * @author mt
 */
public class ChooseProductPage extends BrandedPage {

    public ChooseProductPage() {
        List<GProduct> products = Arrays.asList(new GProduct(), new GProduct(), new GProduct(), new GProduct(), new GProduct());
        add(new ListView<GProduct>("products", products) {
            @Override
            protected void populateItem(final ListItem<GProduct> item) {
                item.add(new Link("button") {
                    @Override
                    public void onClick() {
                        GluezillaSession.get().setSelectedProduct(item.getModelObject());
                        continueToOriginalDestination();
                        setResponsePage(WebApplication.get().getHomePage());
                    }
                }.add(new Label("name", "Product")));
            }
        });
    }

    @Override
    protected Component createSidebar(String id) {
        return new WebMarkupContainer(id).setVisible(false);
    }

}
