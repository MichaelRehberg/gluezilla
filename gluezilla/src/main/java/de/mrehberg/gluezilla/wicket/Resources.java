/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket;

import org.apache.wicket.request.resource.ResourceReference;

/**
 *
 * @author joe
 */
public class Resources {
    public static final ResourceReference MAIN_CSS = new ContextRelativeResourceReference("main.css");
    public static final ResourceReference BOOTSTRAP_CSS = new ContextRelativeResourceReference("bootstrap/css/bootstrap.min.css");
    
    public static final ResourceReference IMAGE_LOGO = new ContextRelativeResourceReference("logo.svg");
    
}
