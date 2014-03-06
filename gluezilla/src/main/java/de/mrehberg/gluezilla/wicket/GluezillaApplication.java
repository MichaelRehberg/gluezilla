/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.mrehberg.gluezilla.wicket.pages.EditProductPage;
import de.mrehberg.gluezilla.wicket.pages.WelcomePage;

/**
 *
 * @author joe
 */
public class GluezillaApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return WelcomePage.class;
    }
    
    @Override
    protected void init() {
    	super.init();
    	BootstrapSettings bootstrapSettings = new BootstrapSettings();
    	bootstrapSettings.setCssResourceReference(Resources.BOOTSTRAP_CSS);
    	Bootstrap.install(this,bootstrapSettings);
    	
    	mountPage("/product", EditProductPage.class);
    }
    
    
}
