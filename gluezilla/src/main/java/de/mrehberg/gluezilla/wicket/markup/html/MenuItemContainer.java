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

package de.mrehberg.gluezilla.wicket.markup.html;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 *
 * @author mt
 */
public class MenuItemContainer extends WebMarkupContainer {

    private final BookmarkablePageLink link;

    public MenuItemContainer(String id, Class<? extends Page> pageClass) {
        super(id);
        link = new BookmarkablePageLink("link", pageClass);
        add(link);
    }

    @Override
    protected void onBeforeRender() {
        if (link.getPageClass().equals(getPage().getClass())) {
            add(new AttributeModifier("class", "active"));            
        }
        super.onBeforeRender();
    }
    
}
