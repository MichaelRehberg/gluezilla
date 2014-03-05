/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import de.mrehberg.gluezilla.wicket.pages.WelcomePage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 *
 * @author joe
 */
public class GluezillaApplication extends WebApplication {

    @Override
    public Class<? extends Page> getHomePage() {
        return WelcomePage.class;
    }
    
    
    
}
