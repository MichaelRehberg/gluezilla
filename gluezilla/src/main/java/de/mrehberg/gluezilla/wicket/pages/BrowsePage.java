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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.wicket.panels.VersionSidebar;

/**
 * 
 * @author mt
 */
public class BrowsePage extends DefaultPage<GProduct> {

	private static final long serialVersionUID = -4829807264007202804L;

	public BrowsePage(PageParameters params) {
		super(params);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		String message = "Browse {0} fixes";
		add(new Label("title", MessageFormat.format(message, getModel()
				.getObject().getProductName())));
	}

	@Override
	protected Component createSidebar(String id) {
		return new VersionSidebar(id, getModel());
	}

	@Override
	protected void createNavbarContents(Navbar navbar) {
		NavbarButton<Page> edit = new NavbarButton<Page>(EditProductPage.class, getResolver().expand(getModel().getObject()), Model.of("Edit"));
		edit.setIconType(GlyphIconType.edit);
		NavbarDropDownButton create = new NavbarDropDownButton(Model.of("Create")) {

			@Override
			protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
				List<AbstractLink> submenus = new ArrayList<>();
				BookmarkablePageLink<EditVersionPage> createHotfix = new BookmarkablePageLink<EditVersionPage>(buttonMarkupId,EditVersionPage.class,getResolver().expand(getModel().getObject()));
				createHotfix.setBody(Model.of("Hotfix"));
				submenus.add(createHotfix);
				BookmarkablePageLink<EditVersionPage> createVersion = new BookmarkablePageLink<EditVersionPage>(buttonMarkupId,EditVersionPage.class,getResolver().expand(getModel().getObject()));
				createVersion.setBody(Model.of("Version"));
				submenus.add(createVersion);
				return submenus;
			}
		};
		create.setIconType(GlyphIconType.plus);
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, edit, create));
	}
}
