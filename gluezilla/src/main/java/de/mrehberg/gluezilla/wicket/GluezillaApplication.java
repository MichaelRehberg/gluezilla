/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import java.text.MessageFormat;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.cdi.ExtendedCdiConfiguration;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.mrehberg.gluezilla.wicket.pages.BrowsePage;
import de.mrehberg.gluezilla.wicket.pages.ChooseProductPage;
import de.mrehberg.gluezilla.wicket.pages.EditProductPage;
import de.mrehberg.gluezilla.wicket.pages.EditVersionPage;
import de.mrehberg.gluezilla.wicket.pages.NotFinishedPage;
import de.mrehberg.gluezilla.wicket.util.EntityResolver;

/**
 *
 * @author joe
 */
public class GluezillaApplication extends WebApplication {

    @Override
    protected void init() {
        super.init();
        new ExtendedCdiConfiguration().configure(this);

        BootstrapSettings bootstrapSettings = new BootstrapSettings();
    	bootstrapSettings.setCssResourceReference(Resources.BOOTSTRAP_CSS);
    	Bootstrap.install(this, bootstrapSettings);
        
        mountPage(MessageFormat.format("/product/$'{'{0}'}'",EntityResolver.PRODUCT), BrowsePage.class);
        mountPage("/review", NotFinishedPage.class);
    	mountPage(MessageFormat.format("/edit/#'{'{0}'}'",EntityResolver.PRODUCT), EditProductPage.class);
    	mountPage(MessageFormat.format("/edit/#'{'{0}'}'/version/#'{'{1}'}'",EntityResolver.PRODUCT, EntityResolver.VERSION), EditVersionPage.class);
    }
    
    @Override
    public Class<? extends Page> getHomePage() {
        return ChooseProductPage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new GluezillaSession(request);
    }
}
