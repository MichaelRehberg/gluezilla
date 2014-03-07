/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import de.mrehberg.gluezilla.wicket.pages.BrowsePage;
import de.mrehberg.gluezilla.wicket.pages.CreatePage;
import de.mrehberg.gluezilla.wicket.pages.ReviewPage;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.mrehberg.gluezilla.wicket.pages.EditProductPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

/**
 *
 * @author joe
 */
public class GluezillaApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();
    	BootstrapSettings bootstrapSettings = new BootstrapSettings();
    	bootstrapSettings.setCssResourceReference(Resources.BOOTSTRAP_CSS);
    	Bootstrap.install(this, bootstrapSettings);
        
        getComponentInstantiationListeners().add(new RequireProductListener());
        
        mountPage("/browse", BrowsePage.class);
        mountPage("/create", CreatePage.class);
        mountPage("/review", ReviewPage.class);
    	mountPage("/product", EditProductPage.class);
    }
    
    @Override
    public Class<? extends Page> getHomePage() {
        return BrowsePage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new GluezillaSession(request);
    }
    

}
