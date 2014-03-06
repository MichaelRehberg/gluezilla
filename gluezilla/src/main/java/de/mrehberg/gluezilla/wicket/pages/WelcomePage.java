/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket.pages;

import java.util.List;

import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.INavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.ComponentPosition;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;

/**
 *
 * @author joe
 */
public class WelcomePage extends BrandedPage {
    
	private static final long serialVersionUID = -764383223918505470L;

	public WelcomePage(){
		
    }
	
	@Override
	protected void createNavbarContents(Navbar navbar) {
		super.createNavbarContents(navbar);
		
		NavbarButton<WelcomePage> browse = new NavbarButton<WelcomePage>(WelcomePage.class, Model.of("Browse"));
		NavbarButton<BrandedPage> create = new NavbarButton<BrandedPage>(BrandedPage.class, Model.of("Create"));
		NavbarButton<BrandedPage> review = new NavbarButton<BrandedPage>(BrandedPage.class, Model.of("Review"));
		
		List<INavbarComponent> list = NavbarComponents.transform(ComponentPosition.LEFT, browse, create, review);
		navbar.addComponents(list);
	}
}
