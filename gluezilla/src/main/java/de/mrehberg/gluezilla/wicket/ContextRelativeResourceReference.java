/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

/**
 *
 * @author joe
 */
public class ContextRelativeResourceReference extends ResourceReference {

    public ContextRelativeResourceReference(String path) {
        super(path);
    }
        
    @Override
    public IResource getResource() {
        return new ContextRelativeResource(getName());
    }
    
}
