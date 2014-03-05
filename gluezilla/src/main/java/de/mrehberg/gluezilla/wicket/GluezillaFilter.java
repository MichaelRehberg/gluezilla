/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

/**
 *
 * @author joe
 */

@WebFilter(value="/*", initParams = {
    @WebInitParam(name = "filterMappingUrlPattern", value="/*"),})
public class GluezillaFilter extends WicketFilter implements IWebApplicationFactory {

    
    @Override
    protected IWebApplicationFactory getApplicationFactory() {
        return this;
    }

    @Override
    protected String getFilterPath(HttpServletRequest request) {
        return "/*";
     //   return super.getFilterPath(request); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public WebApplication createApplication(WicketFilter filter) {
        return new GluezillaApplication();
    }

    @Override
    public void destroy(WicketFilter filter) {
        
    }
    
}
