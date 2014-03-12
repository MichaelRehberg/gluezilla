package de.mrehberg.gluezilla.wicket.panels;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.entities.GVersion;
import de.mrehberg.gluezilla.wicket.pages.ChooseProductPage;

public class VersionSidebar extends Panel {

	private static final long serialVersionUID = 1L;
	private transient IModel<GProduct> product;

	public VersionSidebar(String id, IModel<GProduct> product) {
		super(id);
		this.product = product;
		
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setVisible(product!=null);
		BookmarkablePageLink<ChooseProductPage> productLink = new BookmarkablePageLink<>("chooseProductLink", ChooseProductPage.class);
		productLink.setBody(Model.of(product==null ? "none" : product.getObject().getProductName()));
		add(productLink);
		RepeatingView view = new RepeatingView("versions");
		add(view);
		List<GVersion> versions = product == null ? new ArrayList<GVersion>() : product.getObject().getVersions();
		for (GVersion version : versions) {
			view.add(new ExternalLink(view.newChildId(), "#",version.getName()));
		}
	}
	
	
}
