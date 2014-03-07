package de.mrehberg.gluezilla.wicket.panels;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.entities.GVersion;
import de.mrehberg.gluezilla.wicket.pages.ChooseProductPage;

public class VersionSidebar extends Panel {

	private static final long serialVersionUID = 1L;
	//TODO: we must use models!
	private transient GProduct product;

	public VersionSidebar(String id, GProduct product) {
		super(id);
		this.product = product;
		
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setVisible(product!=null);
		BookmarkablePageLink<ChooseProductPage> productLink = new BookmarkablePageLink<>("chooseProductLink", ChooseProductPage.class);
		productLink.setBody(Model.of(product==null ? "none" : product.getProductName()));
		add(productLink);
		RepeatingView view = new RepeatingView("versions");
		add(view);
		List<GVersion> versions = product == null ? new ArrayList<GVersion>() : product.getVersions();
		for (GVersion version : versions) {
			view.add(new ExternalLink(view.newChildId(), "#",version.getName()));
		}
	}
	
	
}
