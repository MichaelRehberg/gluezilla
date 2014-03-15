package de.mrehberg.gluezilla.wicket.panels;

import de.mrehberg.gluezilla.business.GluezillaFacade;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.entities.GVersion;
import de.mrehberg.gluezilla.wicket.pages.ChooseProductPage;
import javax.inject.Inject;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

public class VersionSidebar extends Panel {

    private static final long serialVersionUID = 1L;
    
    private IModel<GProduct> product;

    @Inject
    GluezillaFacade facade;
            
    public VersionSidebar(String id, IModel<GProduct> product) {
        super(id);
        this.product = product;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setVisible(product != null);
        
        BookmarkablePageLink<ChooseProductPage> productLink = new BookmarkablePageLink<>("chooseProductLink", ChooseProductPage.class);
        productLink.setBody(Model.of(product == null ? "none" : product.getObject().getProductName()));
        add(productLink);
        
        add(new ListView<GVersion>("versions", facade.getVersions(product.getObject())) {
            @Override
            protected void populateItem(ListItem<GVersion> item) {
                item.add(new ExternalLink("link", "#", item.getModelObject().getName()));
            }
        });
    }

}
