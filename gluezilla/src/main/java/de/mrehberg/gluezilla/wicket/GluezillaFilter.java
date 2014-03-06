/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import java.util.Set;

import javax.enterprise.inject.Produces;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;

import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;

import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Service;

/**
 *
 * @author joe
 */

@WebFilter(value="/*")
public class GluezillaFilter extends WicketFilter implements IWebApplicationFactory {
    
    @Override
    protected IWebApplicationFactory getApplicationFactory() {
        return this;
    }
    
    @Override
    public WebApplication createApplication(WicketFilter filter) {
        return new GluezillaApplication();
    }

    @Override
    public void init(boolean isServlet, FilterConfig filterConfig) throws ServletException {
        setFilterPath("");
        super.init(isServlet, filterConfig); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void destroy(WicketFilter filter) {
        
    }
    
    @Produces Set<Service> dummyServices() {
    	//this is riddiculous: https://code.google.com/p/guava-libraries/issues/detail?id=1433
    	 return ImmutableSet.of();
    }

    
}
