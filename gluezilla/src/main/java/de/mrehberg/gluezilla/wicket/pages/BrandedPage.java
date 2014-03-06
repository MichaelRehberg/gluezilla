package de.mrehberg.gluezilla.wicket.pages;

import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.Position;

public class BrandedPage extends BasicPage {
	
	private static final long serialVersionUID = 8057157977690204059L;

	public BrandedPage() {
		Navbar navbar = new Navbar("navbar");
		navbar.setInverted(true);
		navbar.setPosition(Position.TOP);
		navbar.brandName(Model.of("Gluezilla"));
//		navbar.setBrandImage(Resources.IMAGE_LOGO, Model.of("Gluezilla"));
		add(navbar);
		createNavbarContents(navbar);
	}

	protected void createNavbarContents(Navbar navbar) {
		// subclass may override
		
	}

}
