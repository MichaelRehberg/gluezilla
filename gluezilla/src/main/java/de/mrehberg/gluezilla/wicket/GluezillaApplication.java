/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.entities.GVersion;
import de.mrehberg.gluezilla.wicket.pages.BrowsePage;
import de.mrehberg.gluezilla.wicket.pages.ChooseProductPage;
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
        
        mountPage("/product", BrowsePage.class);
        mountPage("/create", CreatePage.class);
        mountPage("/review", ReviewPage.class);
    	mountPage("/new", EditProductPage.class);
    }
    
    @Override
    public Class<? extends Page> getHomePage() {
        return ChooseProductPage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new GluezillaSession(request);
    }
    
   
    
    /**
	 * just for testing. Remove when DB is set up		
	 */
    public static List<GProduct> SAMPLE_PRODUCTS = createSampleProducts("Foo","Bar","BIS","Firefox");
    
    /**
	 * just for testing. Remove when DB is set up		
	 */
    private static List<GProduct> createSampleProducts(String... names) {
    	Random r = new Random();
    	
    	List<GProduct> result = new ArrayList<>(names.length);
    	for (int i = 0; i < names.length; i++) {
    		GProduct product = new GProduct();
    		product.setProductName(names[i]);
    		
    		int numVersions = r.nextInt(10)+1;
    		List<GVersion> versions = new ArrayList<>(numVersions);
    		for (int j = 0; j < numVersions; j++) {
				GVersion version = new GVersion(String.valueOf(j), product);
				versions.add(version);
			}
    		product.getVersions().addAll(versions);
    		result.add(product);
		}
    	return result;
    }

}
