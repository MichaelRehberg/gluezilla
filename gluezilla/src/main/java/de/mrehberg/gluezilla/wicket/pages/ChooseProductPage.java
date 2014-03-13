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

import javax.inject.Inject;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ByteArrayResource;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.mrehberg.gluezilla.business.GluezillaFacade;
import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.wicket.Resources;

/**
 *
 * @author mt
 */
public class ChooseProductPage extends BrandedPage {

    private static final long serialVersionUID = 4760553396916413691L;

    @Inject
    GluezillaFacade ejb;
    
    public ChooseProductPage(PageParameters params) {
        clearOriginalDestination();
        final Class<? extends Page> target = getTarget(params);
        
        final List<GProduct> products = ejb.getAllProducts();
        add(new ListView<GProduct>("products", products) {

            private static final long serialVersionUID = -3993516077236429657L;

            @Override
            protected void populateItem(final ListItem<GProduct> item) {
                BookmarkablePageLink<?> link = new BookmarkablePageLink<>("button", target, getResolver().expand(item.getModelObject()));
                GProduct product = item.getModelObject();
                byte[] image = product.getImage();
                Image productImage = new Image("image", Resources.IMAGE_LOGO);
                if(image!=null)
                {
                	productImage = new Image("image", new ByteArrayResource("image/png", image));
                }
                link.add(productImage);
                link.add(new Label("label",item.getModelObject().getProductName()));
                item.add(link);
            }
        });
    }

    private Class<? extends Page> getTarget(PageParameters params) {
    	return BrowsePage.class;
    }
    
	@Override
	protected void createNavbarContents(Navbar navbar) {
		super.createNavbarContents(navbar);
		NavbarButton<Page> browse = new NavbarButton<Page>(ChooseProductPage.class, Model.of("Browse"));
		browse.setIconType(GlyphIconType.folderopen);
		NavbarButton<Page> create = new NavbarButton<Page>(EditProductPage.class, Model.of("Create"));
		create.setIconType(GlyphIconType.plus);
		NavbarButton<Page> review = new NavbarButton<Page>(ReviewPage.class, Model.of("Review"));
		review.setIconType(GlyphIconType.check);
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, browse, create, review));

		NavbarButton<Page> admin = new NavbarButton<Page>(EditProductPage.class, Model.of("Admin"));
		admin.setIconType(GlyphIconType.wrench);
		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.RIGHT, admin));
	}
}
